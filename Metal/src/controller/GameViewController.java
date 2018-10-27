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

					hero.setMoving(true);
					hero.setDirection(hero.RIGHT);

				}

			}

		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				hero.setMoving(false);

			}

		});

	}

	public boolean getHeroMoving() {

		return hero.isMoving();

	}

	public void setHeroDirection(int direction) {

		hero.setDirection(direction);

	}

	public double getHeroImageViewPosX() {

		return heroImageView.getLayoutX();

	}

	public double getHeroImageViewPosY() {

		return heroImageView.getLayoutY();

	}

	public int getHeroDirection() {

		return hero.getDirection();

	}

	public void setHeroX(double x) {

		heroImageView.setLayoutX(x);

	}

	public void setHeroY(double y) {

		heroImageView.setLayoutY(y);

	}

	public void startThreads() {

		moveThread = new MoveThread(this, hero);
		moveThread.start();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
