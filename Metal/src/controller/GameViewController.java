package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Donkey;
import model.Game;
import model.GameView;
import model.Hero;
import model.KilledEnemie;
import model.PlayableSounds;
import model.Player;
import model.Tank;
import model.TimeList;
import model.Ufo;
import threads.DonkeyThread;
import threads.HeroThread;
import threads.RobotThread;
import threads.TankThread;
import threads.UfoThread;

public class GameViewController implements GameView, PlayableSounds {

	@FXML
	private ImageView tankImageView;
	@FXML
	private ImageView ufoImageView;
	@FXML
	private ImageView donkeyImageView;
	@FXML
	private ImageView heroImageView;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label healthBar;
	private Game game;
	private Hero hero;
	private Player player;
	private Donkey donkey;
	private Tank tank;
	private Ufo ufo;
	private HeroThread heroThread;
	private RobotThread rThread;
	private DonkeyThread donkeyThread;
	private TankThread tankThread;
	private UfoThread ufoThread;
	private Scene scene;
	private int bulletSpeed = 5;
	private int robotModifier = 120;
	private int robotCounter = robotModifier - 1;
	private int robotNum = 0;
	private int enemieBulletModifier = 100;
	private int enemieBulletCounter = enemieBulletModifier - 1;
	private int enemieBulletNum = 0;
	private int scoreModifier = 150;
	private int scoreCounter = scoreModifier - 1;
	private int robotsKilled = 0;
	private boolean alreadySerialized = false;
	private boolean donkeySpawn = false;
	private boolean moveDonkey;
	private ArrayList<Node> robots = new ArrayList<Node>();
	private ArrayList<Image> robotMoving = new ArrayList<Image>();
	private ArrayList<Node> heroBulletsRight = new ArrayList<Node>();
	private ArrayList<Node> heroBulletsLeft = new ArrayList<Node>();
	private ArrayList<Node> enemieBullets = new ArrayList<Node>();
	private ArrayList<Node> ufoBullets = new ArrayList<Node>();
	private String pathHeroGetsHit;
	private String pathHeroShoots;
	private String pathPlayerLoses;
	private String pathRobotDies;
	private Media mFileHeroHit;
	private Media mFileHeroShoots;
	private Media mFilePlayerLoses;
	private Media mFileRobotDies;
	private MediaPlayer mediaPlayer1;
	private MediaPlayer mediaPlayer2;
	private MediaPlayer mediaPlayer3;
	private MediaPlayer mediaPlayer4;
	private int playerScore = 0;

	public void setGame(Scene scene, Game game, Player player) {

		setUpSoundEffects();
		hero = new Hero(heroImageView.getLayoutX(), heroImageView.getLayoutY(), heroImageView.getFitHeight());
		donkey = new Donkey(donkeyImageView.getLayoutX(), 525);
		tank = new Tank(tankImageView.getLayoutX(), tankImageView.getLayoutY());
		ufo = new Ufo(ufoImageView.getLayoutX(), ufoImageView.getLayoutY());
		donkeyImageView.setLayoutY(525);
		this.game = game;
		this.player = player;
		System.out.println(player.getName());
		addSpriteImages();
		this.scene = scene;
		setEverything();

	}

	private void setEverything() {

		ufoImageView.setImage(new Image(ufo.getImageName()));
		long startTime = System.currentTimeMillis() / 1000;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				switch (event.getCode()) {

				case RIGHT:
					setHeroMoving(true);
					setHeroDirection(Hero.RIGHT);
					break;

				case LEFT:
					setHeroMoving(true);
					setHeroDirection(Hero.LEFT);
					break;

				case UP:
					setHeroAimingUp(true);
					break;

				case DOWN:
					setHeroCrouching(true);
					if (!hero.isDead()) {
						hero.setPosY(585);
					}
					break;

				case A:
					if (!hero.isShooting() && !hero.isDead()) {
						hero.setShooting(true);
						ImageView orangeBullet = new ImageView(new Image(ORANGE_BULLET_ROUTE));
						Node newOrangeBullet = orangeBullet;
						if (hero.getDirection() == Hero.RIGHT) {
							newOrangeBullet.relocate(
									heroImageView.getLayoutX() + heroImageView.getBoundsInLocal().getWidth(),
									heroImageView.getLayoutY());
							heroBulletsRight.add(newOrangeBullet);
							playHeroShoots();
						} else if (hero.getDirection() == Hero.LEFT) {
							newOrangeBullet.relocate(
									heroImageView.getLayoutX() - heroImageView.getBoundsInLocal().getWidth(),
									heroImageView.getLayoutY());
							heroBulletsLeft.add(newOrangeBullet);
							playHeroShoots();
						}

						anchorPane.getChildren().add(newOrangeBullet);
					}
					break;

				default:
					break;

				}

			}

		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {

				case RIGHT:
					hero.setMoving(false);
					break;
				case LEFT:
					hero.setMoving(false);
					break;
				case DOWN:
					hero.setCrouching(false);
					if (!hero.isDead()) {
						hero.setPosY(560);
					}
					break;
				case A:
					hero.setShooting(false);
					break;
				case D:
					hero.setDying(true);
					break;
				default:
					break;

				}

			}

		});
		startThreads();

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				if (hero.isDead()) {

					hero.die();

					if (!alreadySerialized) {
						try {
							long endTime = System.currentTimeMillis() / 1000;
							long playedTime = endTime - startTime;
							player.setTimePlayed(player.getTimePlayed() + playedTime);
							player.addTimeList(new TimeList(playedTime));
							player.addScore(playerScore);
							KilledEnemie k = new KilledEnemie(robotsKilled);
							player.addToKilledEnemiesList(k);
							if (!game.playerExists(player.getName())) {
								game.addPlayerToTree(player);
								game.addPlayerToArrayList(player);
							}
							serializarGame();
							alreadySerialized = true;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				setHeroX(hero.getPosX());
				setHeroY(hero.getPosY());
				setHeroImage(hero.getImage());
				heroShootRight();
				heroShootLeft();
				updateHealthBar();
				robotCounter++;
				enemieBulletCounter++;
				scoreCounter++;
				if (scoreCounter % scoreModifier == 0 && !hero.isDead()) {
					playerScore += 10;
					updateScoreLabel(playerScore);
					setUpSoundEffects();
				}
				// contadores para los robots
				if (robotCounter % robotModifier == 0 && !hero.isDead()) {
					spawnRobot();
				}
				if (robotNum == 20) {
					robotModifier = 80;
					robotCounter = robotModifier - 1;
				}
				if (robotNum == 50) {
					robotModifier = 60;
					robotCounter = robotModifier - 1;
				}
				if (robotNum == 80) {
					robotModifier = 50;
					robotCounter = robotModifier - 1;
					donkeySpawn = true;
				}
				// contadores para las balas enemigas
				if (enemieBulletCounter % enemieBulletModifier == 0 && !hero.isDead()) {
					spawnEnemieBullet();
					shootUfo();
				}
				if (enemieBulletNum == 50) {
					enemieBulletModifier = 60;
					enemieBulletCounter = enemieBulletModifier - 1;
				}
				if (enemieBulletNum == 100) {
					bulletSpeed = 8;
					hero.setSpeed(18);
				}
				if (donkeySpawn) {
					donkeySpawn = false;
					moveDonkey = true;
				}
				if (moveDonkey) {
					donkeyImageView.setLayoutX(donkey.getPosX());
					donkeyImageView.setImage(new Image(donkey.getImage()));
				}
				moveUfo();
				if (!hero.isDead()) {
					animTank();
				}
				moveRobot();
				moveEnemieBullets();
				moveUfoBullets();
				checkBulletHit();
				if (!hero.isTakingDamage()) {
					checkHeroHit();
				}
			}
		};
		timer.start();

	}

	private void setUpSoundEffects() {
		pathHeroGetsHit = new File(heroGetsHitRoute).getAbsolutePath();
		pathHeroShoots = new File(heroShootsRoute).getAbsolutePath();
		pathPlayerLoses = new File(playerLosesRoute).getAbsolutePath();
		pathRobotDies = new File(robotDiesRoute).getAbsolutePath();
		mFileHeroHit = new Media(new File(pathHeroGetsHit).toURI().toString());
		mFileHeroShoots = new Media(new File(pathHeroShoots).toURI().toString());
		mFilePlayerLoses = new Media(new File(pathPlayerLoses).toURI().toString());
		mFileRobotDies = new Media(new File(pathRobotDies).toURI().toString());
		mediaPlayer1 = new MediaPlayer(mFileHeroHit);
		mediaPlayer1.setVolume(0.8);
		mediaPlayer2 = new MediaPlayer(mFileHeroShoots);
		mediaPlayer2.setVolume(0.2);
		mediaPlayer3 = new MediaPlayer(mFilePlayerLoses);
		mediaPlayer3.setVolume(0.8);
		mediaPlayer4 = new MediaPlayer(mFileRobotDies);
		mediaPlayer4.setVolume(0.25);
	}

	public void moveUfo() {
		ufoImageView.setLayoutX(ufo.getPosX());
	}

	public void animTank() {
		tankImageView.setImage(new Image(tank.getTankImage()));
	}

	public void shootUfo() {
		Node bullet = new ImageView(PURPLE_BULLET_ROUTE);
		bullet.relocate(ufoImageView.getLayoutX(),
				ufoImageView.getLayoutY() + ufoImageView.getBoundsInLocal().getHeight() / 2);
		ufoBullets.add(bullet);
		anchorPane.getChildren().add(bullet);
	}

	public void spawnEnemieBullet() {
		Node bullet = new ImageView(ORANGE_BULLET_ROUTE);
		bullet.relocate(getRandomX(), 0);
		enemieBullets.add(bullet);
		anchorPane.getChildren().add(bullet);
		enemieBulletNum++;
	}

	public double getRandomX() {
		Random rand = new Random();
		return 10 + (1100 - 10) * rand.nextDouble();
	}

	public void spawnRobot() {
		Node robot = new ImageView(ROBOT_ROUTE);
		robot.relocate(1100, 590);
		robots.add(robot);
		anchorPane.getChildren().add(robot);
		robotNum++;
	}

	public void heroShootRight() {

		for (int i = 0; i < heroBulletsRight.size(); i++) {
			Node bullet = heroBulletsRight.get(i);
			if (bullet.getLayoutX() < 1200 && bullet.getLayoutX() > 0 && bullet.getLayoutY() < 700
					&& bullet.getLayoutY() > 0) {
				bullet.relocate(bullet.getLayoutX() + bulletSpeed, bullet.getLayoutY());
			} else {
				heroBulletsRight.remove(i);
				anchorPane.getChildren().remove(bullet);
			}

		}

	}

	public void heroShootLeft() {

		for (int i = 0; i < heroBulletsLeft.size(); i++) {
			Node bullet = heroBulletsLeft.get(i);
			if (bullet.getLayoutX() < 1200 && bullet.getLayoutX() > 0 && bullet.getLayoutY() < 700
					&& bullet.getLayoutY() > 0) {
				bullet.relocate(bullet.getLayoutX() - bulletSpeed, bullet.getLayoutY());
			} else {
				heroBulletsLeft.remove(i);
				anchorPane.getChildren().remove(bullet);
			}
		}

	}

	public void moveRobot() {

		for (int i = 0; i < robots.size() && !hero.isDead(); i++) {

			if (robots.get(i).getLayoutX() < 1200 && robots.get(i).getLayoutX() > -50) {

				robots.get(i).relocate(robots.get(i).getLayoutX() - ROBOT_SPEED, robots.get(i).getLayoutY());

			} else {
				anchorPane.getChildren().remove(robots.get(i));
				robots.remove(i);
			}

		}

	}

	public void moveEnemieBullets() {
		for (int i = 0; i < enemieBullets.size() && !hero.isDead(); i++) {
			if (enemieBullets.get(i).getLayoutY() < 700) {
				enemieBullets.get(i).relocate(enemieBullets.get(i).getLayoutX(),
						enemieBullets.get(i).getLayoutY() + bulletSpeed);
			} else {
				anchorPane.getChildren().remove(enemieBullets.get(i));
				enemieBullets.remove(i);
			}
		}
	}

	public void moveUfoBullets() {
		for (int i = 0; i < ufoBullets.size() && !hero.isDead(); i++) {
			if (ufoBullets.get(i).getLayoutY() < 700) {
				ufoBullets.get(i).relocate(ufoBullets.get(i).getLayoutX(),
						ufoBullets.get(i).getLayoutY() + bulletSpeed);
			} else {
				anchorPane.getChildren().remove(ufoBullets.get(i));
				ufoBullets.remove(i);
			}
		}
	}

	public void checkBulletHit() {

		try {

			for (int i = 0; i < heroBulletsRight.size(); i++) {

				for (int j = 0; j < robots.size(); j++) {

					if (heroBulletsRight.get(i).getBoundsInParent().intersects(robots.get(j).getBoundsInParent())) {
						anchorPane.getChildren().remove(robots.get(j));
						robots.remove(j);
						anchorPane.getChildren().remove(heroBulletsRight.get(i));
						heroBulletsRight.remove(i);
						playRobotDies();
						robotsKilled++;
					}

				}

			}
			for (int i = 0; i < heroBulletsLeft.size(); i++) {

				for (int j = 0; j < robots.size(); j++) {

					if (heroBulletsLeft.get(i).getBoundsInParent().intersects(robots.get(j).getBoundsInParent())) {
						anchorPane.getChildren().remove(robots.get(j));
						robots.remove(j);
						anchorPane.getChildren().remove(heroBulletsLeft.get(i));
						heroBulletsLeft.remove(i);
						playRobotDies();
						robotsKilled++;
					}

				}

			}
		} catch (Exception e) {

		}

	}

	public void checkHeroHit() {

		for (int i = 0; i < robots.size(); i++) {
			if (heroImageView.getBoundsInParent().intersects(robots.get(i).getBoundsInParent())
					&& !hero.isTakingDamage()) {
				hero.takeDamage(ROBOT_DAMAGE);
				hero.setTakingDamage(true);
				playHeroGetsHit();
			}
		}
		for (int i = 0; i < enemieBullets.size(); i++) {
			if (heroImageView.getBoundsInParent().intersects(enemieBullets.get(i).getBoundsInParent())
					&& !hero.isTakingDamage()) {
				hero.takeDamage(ENEMIE_BULLET_DAMAGE);
				hero.setTakingDamage(true);
				playHeroGetsHit();
			}
		}
		for (int i = 0; i < ufoBullets.size(); i++) {
			if (heroImageView.getBoundsInParent().intersects(ufoBullets.get(i).getBoundsInParent())
					&& !hero.isTakingDamage()) {
				hero.takeDamage(UFO_DAMAGE);
				hero.setTakingDamage(true);
				playHeroGetsHit();
			}
		}

	}

	public void setHeroDying(boolean dying) {
		hero.setDying(dying);
	}

	public boolean getHeroDying() {
		return hero.getDying();
	}

	public void setHeroMoving(boolean moving) {

		hero.setMoving(moving);

	}

	public boolean getHeroMoving() {

		return hero.isMoving();

	}

	public void setHeroDirection(int direction) {

		hero.setDirection(direction);

	}

	public int getHeroDirection() {

		return hero.getDirection();

	}

	public double getHeroImageViewPosX() {

		return heroImageView.getLayoutX();

	}

	public double getHeroImageViewPosY() {

		return heroImageView.getLayoutY();

	}

	public boolean getHeroCrouching() {

		return hero.isCrouching();

	}

	public boolean getHeroAimingUp() {

		return hero.isAimingUp();

	}

	public void setHeroX(double x) {

		heroImageView.setLayoutX(x);

	}

	public void setHeroY(double y) {

		heroImageView.setLayoutY(y);

	}

	public void startThreads() {

		heroThread = new HeroThread(this, hero, game);
		heroThread.start();
		rThread = new RobotThread(robotMoving, robots, this);
		rThread.start();
		donkeyThread = new DonkeyThread(donkey, this);
		donkeyThread.start();
		tankThread = new TankThread(tank);
		tankThread.start();
		ufoThread = new UfoThread(ufo);
		ufoThread.start();

	}

	public void setHeroImage(String img) {

		heroImageView.setImage(new Image(img));

	}

	public void addSpriteImages() {

		for (int i = 0; i < 16; i++) {
			robotMoving.add(new Image("file:data/sprites/mini robot/mini-robot_" + (i + 1) + ".png"));
		}

	}

	public boolean getHeroIsDead() {
		return hero.isDead();
	}

	public void setHeroCrouching(boolean b) {
		hero.setCrouching(b);
	}

	public void setHeroAimingUp(boolean b) {
		hero.setAimingUp(b);
	}

	public void playHeroGetsHit() {
		mediaPlayer1 = new MediaPlayer(mFileHeroHit);
		mediaPlayer1.play();
	}

	public void playHeroShoots() {
		mediaPlayer2 = new MediaPlayer(mFileHeroShoots);
		mediaPlayer2.play();
	}

	public void playPlayerLoses() {
		mediaPlayer3 = new MediaPlayer(mFilePlayerLoses);
		mediaPlayer3.play();
	}

	public void playRobotDies() {
		mediaPlayer4 = new MediaPlayer(mFileRobotDies);
		mediaPlayer4.play();
	}

	public void serializarGame() throws IOException {
		File save = new File("Save");
		FileOutputStream fos = new FileOutputStream(save);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(game);
		fos.close();
		oos.close();
	}

	public void updateScoreLabel(int score) {
		scoreLabel.setText(String.valueOf(score));
	}

	public void updateHealthBar() {
		healthBar.setText(String.valueOf(hero.getHealth()));
	}

	@FXML
	void backToMenu(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void saveGame(ActionEvent event) {

		File file = new File("gameData.txt");
		File file2 = new File("enemieBullets.txt");
		File file3 = new File("robots.txt");
		File file4 = new File("heroBulletsRight.txt");
		File file5 = new File("heroBulletsLeft.txt");
		File file6 = new File("ufoBullets.txt");
		try {
			PrintWriter pr1 = new PrintWriter(file);
			PrintWriter pr2 = new PrintWriter(file2);
			PrintWriter pr3 = new PrintWriter(file3);
			PrintWriter pr4 = new PrintWriter(file4);
			PrintWriter pr5 = new PrintWriter(file5);
			PrintWriter pr6 = new PrintWriter(file6);
			pr1.println(player.getName());
			pr1.println(scoreLabel.getText());
			pr1.println(String.valueOf(hero.isMoving()));
			pr1.println(String.valueOf(hero.isCrouching()));
			pr1.println(String.valueOf(hero.isAimingUp()));
			pr1.println(String.valueOf(hero.isShooting()));
			pr1.println(String.valueOf(hero.isDead()));
			pr1.println(String.valueOf(hero.isTakingDamage()));
			pr1.println(String.valueOf(hero.getDirection()));
			pr1.println(hero.getHealth());
			pr1.println(heroImageView.getLayoutX());
			pr1.println(enemieBulletModifier);
			pr1.println(robotModifier);
			pr1.println(scoreModifier);
			pr1.println(robotNum);
			pr1.println(enemieBulletNum);
			pr1.println(bulletSpeed);
			pr1.println(donkeySpawn);
			pr1.println(moveDonkey);
			pr1.println(donkey.getPosX());
			pr1.println(ufo.getPosX());
			for (int i = 0; i < enemieBullets.size(); i++) {
				pr2.println(enemieBullets.get(i).getLayoutX());
				pr2.println(enemieBullets.get(i).getLayoutY());
			}
			for (int i = 0; i < robots.size(); i++) {
				pr3.println(robots.get(i).getLayoutX());
			}
			for (int i = 0; i < heroBulletsRight.size(); i++) {
				pr4.println(heroBulletsRight.get(i).getLayoutX());
			}
			for (int i = 0; i < heroBulletsLeft.size(); i++) {
				pr5.println(heroBulletsLeft.get(i).getLayoutX());
			}
			for (int i = 0; i < ufoBullets.size(); i++) {
				pr6.println(ufoBullets.get(i).getLayoutX());
				pr6.println(ufoBullets.get(i).getLayoutY());
			}
			pr1.close();
			pr2.close();
			pr3.close();
			pr4.close();
			pr5.close();
			pr6.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void loadGame(Scene scene, Game game, ArrayList<String> gameData, ArrayList<Double> enemieBullets,
			ArrayList<Double> robots, ArrayList<Double> heroBR, ArrayList<Double> heroBL,
			ArrayList<Double> ufoBullets) {

		String playerName = gameData.get(0);
		int playerScore = Integer.parseInt(gameData.get(1));
		boolean moving = Boolean.parseBoolean(gameData.get(2));
		boolean crouching = Boolean.parseBoolean(gameData.get(3));
		boolean aimingUp = Boolean.parseBoolean(gameData.get(4));
		boolean shooting = Boolean.parseBoolean(gameData.get(5));
		boolean dead = Boolean.parseBoolean(gameData.get(6));
		boolean takingDamage = Boolean.parseBoolean(gameData.get(7));
		int direction = Integer.parseInt(gameData.get(8));
		int health = Integer.parseInt(gameData.get(9));
		double posX = Double.parseDouble(gameData.get(10));
		int enemieBulletModifier = Integer.parseInt(gameData.get(11));
		int robotModifier = Integer.parseInt(gameData.get(12));
		int scoreModifier = Integer.parseInt(gameData.get(13));
		int robotNum = Integer.parseInt(gameData.get(14));
		int enemieBulletNum = Integer.parseInt(gameData.get(15));
		int bulletSpeed = Integer.parseInt(gameData.get(16));
		boolean donkeySpawn = Boolean.parseBoolean(gameData.get(17));
		boolean donkeyMove = Boolean.parseBoolean(gameData.get(18));
		double posXDonkey = Double.parseDouble(gameData.get(19));
		double posXUfo = Double.parseDouble(gameData.get(20));

		this.game = game;
		this.hero = new Hero(posX, heroImageView.getLayoutY(), heroImageView.getFitHeight());
		this.donkey = new Donkey(posXDonkey, donkeyImageView.getLayoutY());
		this.ufo = new Ufo(posXUfo, ufoImageView.getLayoutY());
		this.tank = new Tank(tankImageView.getLayoutX(), tankImageView.getLayoutY());
		donkeyImageView.setLayoutX(posXDonkey);
		if (game.playerExists(playerName)) {
			game.sortPlayerNames(1);
			this.player = game.searchPlayerName(playerName);
		} else {
			this.player = new Player(playerName);
		}
		this.donkeySpawn = donkeySpawn;
		this.moveDonkey = donkeyMove;
		this.playerScore = playerScore;
		hero.setStates(moving, crouching, aimingUp, shooting, dead, takingDamage, direction, health);
		this.enemieBulletModifier = enemieBulletModifier;
		enemieBulletCounter = this.enemieBulletModifier - 1;
		this.robotModifier = robotModifier;
		robotCounter = this.robotModifier - 1;
		this.scoreModifier = scoreModifier;
		scoreCounter = this.scoreModifier - 1;
		this.robotNum = robotNum;
		this.enemieBulletNum = enemieBulletNum;
		this.bulletSpeed = bulletSpeed;

		for (int i = 0; i < enemieBullets.size() - 1; i++) {
			Node bullet = new ImageView(ORANGE_BULLET_ROUTE);
			bullet.relocate(enemieBullets.get(i), enemieBullets.get(i + 1));
			this.enemieBullets.add(bullet);
			anchorPane.getChildren().add(bullet);
		}

		for (int i = 0; i < robots.size(); i++) {
			Node robot = new ImageView(ROBOT_ROUTE);
			robot.relocate(robots.get(i), 590);
			this.robots.add(robot);
			anchorPane.getChildren().add(robot);
		}

		for (int i = 0; i < heroBR.size(); i++) {
			ImageView orangeBullet = new ImageView(new Image(ORANGE_BULLET_ROUTE));
			Node newOrangeBullet = orangeBullet;
			newOrangeBullet.relocate(heroBR.get(i), heroImageView.getLayoutY());
			heroBulletsRight.add(newOrangeBullet);
			anchorPane.getChildren().add(newOrangeBullet);
		}
		for (int i = 0; i < heroBL.size(); i++) {
			ImageView orangeBullet = new ImageView(new Image(ORANGE_BULLET_ROUTE));
			Node newOrangeBullet = orangeBullet;
			newOrangeBullet.relocate(heroBL.get(i), heroImageView.getLayoutY());
			heroBulletsLeft.add(newOrangeBullet);
			anchorPane.getChildren().add(newOrangeBullet);
		}
		for (int i = 0; i < ufoBullets.size() - 1; i++) {
			Node purpleBullet = new ImageView(new Image(PURPLE_BULLET_ROUTE));
			purpleBullet.relocate(ufoBullets.get(i), ufoBullets.get(i + 1));
			this.ufoBullets.add(purpleBullet);
			anchorPane.getChildren().add(purpleBullet);
		}

		setUpSoundEffects();
		System.out.println(player.getName());
		addSpriteImages();
		this.scene = scene;
		setEverything();

	}

	public boolean getMoveDonkey() {
		return moveDonkey;
	}

	public void setMoveDonkey(boolean b) {
		this.moveDonkey = b;
	}

	public void addHealth(int health) {
		hero.setHealth(hero.getHealth() + health);
	}

	public void setPosXDonkeyImageView(double posX) {
		donkeyImageView.setLayoutX(posX);
	}
}
