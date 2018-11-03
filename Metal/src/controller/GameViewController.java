package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Block;
import model.Game;
import model.Hero;
import threads.BlocksThread;
import threads.HeroThread;

public class GameViewController implements Initializable {

	@FXML
	private ImageView heroImageView;
	@FXML
	private AnchorPane anchorPane;
	private Game game;
	private Hero hero;
	private boolean arrowPressed = false;
	private HeroThread heroThread;
	private BlocksThread blocksThread;
	private Scene scene;
	private double width;
	private double height;
	private ArrayList<Image> iddleLeft = new ArrayList<Image>();
	private ArrayList<Image> iddleRight = new ArrayList<Image>();
	private ArrayList<Image> runningLeft = new ArrayList<Image>();
	private ArrayList<Image> runningRight = new ArrayList<Image>();
	private ArrayList<Image> crouchingRight = new ArrayList<Image>();
	private ArrayList<Image> crouchingLeft = new ArrayList<Image>();
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
					if (getHeroImageViewPosY() < 570) {
						setHeroY(getHeroImageViewPosY() + 28);
					}
					break;

				default:
					break;

				}

			}
		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
						|| event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT)) {

					setHeroMoving(false);

				}

				if (event.getCode() == KeyCode.DOWN) {
					setHeroY(getHeroImageViewPosY() - 28);
					setHeroCrouching(false);
				}

			}

		});
		startThreads();

	}

	public void setHeroMoving(boolean moving) {

		hero.setMoving(moving);

	}
	
	public void setHeroFalling(boolean falling) {
		
		hero.setFalling(falling);
		
	}
	
	public boolean getHeroFalling() {
		
		return hero.isFalling();
		
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

		heroThread = new HeroThread(this, hero);
		heroThread.start();
		blocksThread = new BlocksThread(this, hero, game);
		blocksThread.start();

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
