package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import model.Bullet;
import model.Game;
import model.GameView;
import model.Hero;
import model.PlayableSounds;
import model.Player;
import threads.HeroThread;
import threads.RobotThread;

public class GameViewController implements GameView, PlayableSounds {

	@FXML
	private ImageView heroImageView;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label scoreLabel;
	private Game game;
	private Hero hero;
	private Player player;
	private Bullet firstBullet = null;
	private HeroThread heroThread;
	private RobotThread rThread;
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
	private boolean alreadySerialized=false;
	private ArrayList<Node> robots = new ArrayList<Node>();
	private ArrayList<Image> robotMoving = new ArrayList<Image>();
	private ArrayList<Node> heroBulletsRight = new ArrayList<Node>();
	private ArrayList<Node> heroBulletsLeft = new ArrayList<Node>();
	private ArrayList<Node> enemieBullets = new ArrayList<Node>();
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
	private int playerScore=0;

	public void setGame(Scene scene, Game game, Player player) {

		setUpSoundEffects();
		hero = new Hero(heroImageView.getLayoutX(), heroImageView.getLayoutY(), heroImageView.getFitHeight());
		this.game = game;
		this.player = player;
		System.out.println(player.getName());
		addSpriteImages();
		this.scene = scene;
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
					hero.setPosY(585);
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
					hero.setPosY(560);
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
					
					if(!alreadySerialized) {
						try {
							player.addScore(playerScore);
							game.addPlayerToTree(player);
							game.addPlayerToArrayList(player);
							serializarGame();
							alreadySerialized=true;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
				}
				setHeroX(hero.getPosX());
				setHeroY(hero.getPosY());
				setHeroImage(hero.getImage());
				heroShootRight();
				heroShootLeft();
				robotCounter++;
				enemieBulletCounter++;
				scoreCounter++;
				if (scoreCounter % scoreModifier == 0 && !hero.isDead()) {
					playerScore+=10;
					updateScoreLabel( playerScore );
				}
				
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
				}
				if (enemieBulletCounter % enemieBulletModifier == 0 && !hero.isDead()) {
					spawnEnemieBullet();
				}
				if (enemieBulletNum == 50) {
					enemieBulletModifier = 60;
					enemieBulletCounter = enemieBulletModifier - 1;
				}
				if (enemieBulletNum == 100) {
					bulletSpeed = 8;
				}
				moveRobot();
				moveEnemieBullets();
				checkBulletHit();
				checkHeroHit();

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
		mediaPlayer3.setVolume(0.5);
		mediaPlayer4 = new MediaPlayer(mFileRobotDies);
		mediaPlayer4.setVolume(0.25);
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
				hero.takeDamage(ROBOT_DAMAGE);
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
		
		File save= new File("Save");
		FileOutputStream saved = new FileOutputStream(save);
		ObjectOutputStream wtf = new ObjectOutputStream(saved);
		wtf.writeObject(game);
    	saved.close();
    	wtf.close();
			
	}
	
	public void updateScoreLabel( int score ) {
		scoreLabel.setText( String.valueOf( score ) );
	}

}
