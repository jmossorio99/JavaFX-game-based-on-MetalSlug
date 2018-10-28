package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Hero;
import threads.MoveThread;

public class GameViewController implements Initializable {

	@FXML
	private ImageView heroImageView;
	private Hero hero;
	private MoveThread moveThread;
	private Scene scene;

	public void setGame(Scene scene) {

		hero = new Hero(heroImageView.getLayoutX(), heroImageView.getLayoutY());
		startThreads();

		this.scene = scene;
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (event.getCode() == KeyCode.RIGHT) {

					setHeroMoving(true);
					setHeroDirection(hero.RIGHT);

				} else if (event.getCode() == KeyCode.LEFT) {

					setHeroMoving(true);
					setHeroDirection(hero.LEFT);

				} else if (event.getCode() == KeyCode.UP) {

					setHeroDirection(hero.UP);

				} else if (event.getCode() == KeyCode.DOWN) {

					setHeroDirection(hero.DOWN);

				}

			}

		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				setHeroMoving(false);
				setHeroDirection(hero.RIGHT);

			}

		});

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

	public void setHeroX(double x) {

		heroImageView.setLayoutX(x);
		hero.setPosX(x);

	}

	public void setHeroY(double y) {

		heroImageView.setLayoutY(y);
		hero.setPosY(y);

	}

	public void startThreads() {

		moveThread = new MoveThread(this, hero);
		moveThread.start();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
