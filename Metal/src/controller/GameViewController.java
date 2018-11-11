package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Block;
import model.Bullet;
import model.Game;
import model.Hero;
import threads.HeroThread;

public class GameViewController implements Initializable {

	public static final String ORANGE_BULLET_ROUTE = "file:data/sprites/hero/Shoot/OrangeBullet.png";
	public static final int BULLET_SPEED = 5;
	@FXML
	private ImageView heroImageView;
	@FXML
	private AnchorPane anchorPane;
	private Game game;
	private Hero hero;
	private Bullet firstBullet = null;
	private HeroThread heroThread;
	private Scene scene;
	private Parent root;
	private double width;
	private double height;
	private ArrayList<Node> heroBullets = new ArrayList<Node>();
	private ArrayList<Image> iddleLeft = new ArrayList<Image>();
	private ArrayList<Image> iddleRight = new ArrayList<Image>();
	private ArrayList<Image> runningLeft = new ArrayList<Image>();
	private ArrayList<Image> runningRight = new ArrayList<Image>();
	private ArrayList<Image> crouchingRight = new ArrayList<Image>();
	private ArrayList<Image> crouchingLeft = new ArrayList<Image>();
	private ArrayList<Image> dyingRight = new ArrayList<Image>();
	private ArrayList<Image> dyingLeft = new ArrayList<Image>();
	private ArrayList<Image> fireStandingLeft = new ArrayList<Image>();
	private ArrayList<Image> fireStandingRight = new ArrayList<Image>();
	private ArrayList<Image> fireUpLeft = new ArrayList<Image>();
	private ArrayList<Image> fireUpRight = new ArrayList<Image>();
	private double centerHeroX;
	private double centerHeroY;

	@SuppressWarnings("deprecation")
	public void setGame(Scene scene) {

		hero = new Hero(heroImageView.getLayoutX(), heroImageView.getLayoutY(), heroImageView.getFitHeight());
		game = new Game(hero);
		for (Node node : anchorPane.getChildren()) {

			try {

				ImageView node1 = (ImageView) node;
				String id = node1.getId();
				if (id.contains("terrain")) {
					game.addBlocks(new Block(node1.getLayoutX(), node1.getLayoutY(), node1.getFitWidth(),
							node1.getFitHeight(), node1.getImage().impl_getUrl()));
				}

			} catch (Exception e) {

			}

		}
		addSpriteImages();
		this.scene = scene;
		width = scene.getWidth();
		height = scene.getHeight();
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
					break;

				case A:
					if (!hero.isShooting()) {
						hero.setShooting(true);
						ImageView orangeBullet = new ImageView(new Image(ORANGE_BULLET_ROUTE));
						Node newOrangeBullet = orangeBullet;
						if (hero.getDirection() == Hero.RIGHT) {
							newOrangeBullet.relocate(
									heroImageView.getLayoutX() + heroImageView.getBoundsInLocal().getWidth(),
									heroImageView.getLayoutY());
						} else if (hero.getDirection() == Hero.LEFT) {
							newOrangeBullet.relocate(
									heroImageView.getLayoutX() - heroImageView.getBoundsInLocal().getWidth(),
									heroImageView.getLayoutY());
						}
						heroBullets.add(newOrangeBullet);
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

				heroShoot();

			}
		};
		timer.start();

	}

	public void heroShoot() {

		for (int i = 0; i < heroBullets.size(); i++) {
			if (heroBullets.get(i).getLayoutX() < 1200 && heroBullets.get(i).getLayoutX() > 0
					&& heroBullets.get(i).getLayoutY() < 700 && heroBullets.get(i).getLayoutY() > 0) {
				heroBullets.get(i).relocate(heroBullets.get(i).getLayoutX() + BULLET_SPEED,
						heroBullets.get(i).getLayoutY());
			} else {
				heroBullets.remove(i);
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
		hero.setPosX(x);

	}

	public void setHeroY(double y) {

		heroImageView.setLayoutY(y);
		hero.setPosY(y);

	}

	public void startThreads() {

		heroThread = new HeroThread(this, hero, game);
		heroThread.start();

	}

	public void setHeroImage(Image img) {

		heroImageView.setImage(img);

	}

	public void addSpriteImages() {

		for (int i = 0; i < 6; i++) {
			iddleLeft.add(new Image("file:data/sprites/hero/Iddle/left/Idle" + (i + 1) + "I.png"));
			iddleRight.add(new Image("file:data/sprites/hero/Iddle/right/Idle" + (i + 1) + "D.png"));
		}
		for (int i = 0; i < 11; i++) {
			runningLeft.add(new Image("file:data/sprites/hero/Running/Left/Run" + (i + 1) + "I.png"));
			runningRight.add(new Image("file:data/sprites/hero/Running/Right/Run" + (i + 1) + "D.png"));
		}
		for (int i = 0; i < 5; i++) {
			crouchingRight.add(new Image("file:data/sprites/hero/Crouch/right/crouch" + (i + 1) + "D.png"));
			crouchingLeft.add(new Image("file:data/sprites/hero/Crouch/left/crouch" + (i + 1) + "I.png"));
		}
		for (int i = 0; i < 4; i++) {
			dyingRight.add(new Image("file:data/sprites/hero/Dead/right/dead" + (i + 1) + "D.png"));
			dyingLeft.add(new Image("file:data/sprites/hero/Dead/left/dead" + (i + 1) + "I.png"));
		}
		for (int i = 0; i < 4; i++) {
			fireStandingRight.add(new Image("file:data/sprites/hero/Shoot/fireStandingRight/fire" + (i + 1) + "D.png"));
			fireStandingLeft.add(new Image("file:data/sprites/hero/Shoot/fireStandingLeft/fire" + (i + 1) + "I.png"));
		}

	}

	public Image getIddleLeftImage(int i) {

		return iddleLeft.get(i);

	}

	public Image getIddleRightImage(int i) {

		return iddleRight.get(i);

	}

	public Image getRunningLeftImage(int i) {

		return runningLeft.get(i);

	}

	public Image getRunningRightImage(int i) {

		return runningRight.get(i);

	}

	public Image getCrouchingRightImage(int i) {
		return crouchingRight.get(i);
	}

	public Image getCrouchingLeftImage(int i) {
		return crouchingLeft.get(i);
	}

	public Image getDyingRightImage(int i) {
		return dyingRight.get(i);
	}

	public Image getDyingLeftImage(int i) {
		return dyingLeft.get(i);
	}

	public Image getFireStandingRightImage(int i) {
		return fireStandingRight.get(i);
	}

	public Image getFireStandingLefttImage(int i) {
		return fireStandingLeft.get(i);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		centerHeroX = heroImageView.getBoundsInLocal().getWidth() / 2;
		centerHeroY = heroImageView.getBoundsInLocal().getHeight() / 2;

	}

	public void setHeroCrouching(boolean b) {

		hero.setCrouching(b);

	}

	public void setHeroAimingUp(boolean b) {

		hero.setAimingUp(b);

	}

}
