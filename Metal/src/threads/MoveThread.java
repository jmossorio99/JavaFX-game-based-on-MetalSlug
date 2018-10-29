package threads;

import controller.GameViewController;
import javafx.scene.image.Image;
import model.Hero;

public class MoveThread extends Thread {

	private GameViewController controller;
	private boolean running = false;
	private double posX;
	private double posY;
	private Hero hero;

	public MoveThread(GameViewController controller, Hero hero) {

		this.controller = controller;
		posX = hero.getPosX();
		posY = hero.getPosY();
		this.hero = hero;

	}

	public void init() {

		running = true;

	}

	@Override
	public void run() {

		init();
		while (running) {

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (controller.getHeroMoving()) {

				if (controller.getHeroDirection() == Hero.RIGHT && controller.getHeroImageViewPosX() <= 1100) {

					posX += hero.getSpeed();
					controller.setHeroX(posX);

				} else if (controller.getHeroDirection() == Hero.LEFT && controller.getHeroImageViewPosX() >= 0) {

					posX -= hero.getSpeed();
					controller.setHeroX(posX);

				}

			}

		}

	}

}
