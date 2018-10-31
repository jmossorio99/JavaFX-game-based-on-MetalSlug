package threads;

import controller.GameViewController;
import model.Hero;

public class HeroThread extends Thread {

	private GameViewController controller;
	private boolean running = false;
	private double posX;
	private double posY;
	private Hero hero;

	public HeroThread(GameViewController controller, Hero hero) {

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

					for (int i = 0; i < 11 && controller.getHeroMoving()
							&& controller.getHeroDirection() == Hero.RIGHT; i++) {

						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						posX += hero.getSpeed();
						controller.setHeroX(posX);
						controller.setHeroImage(controller.getRunningRightImage(i));

					}

				} else if (controller.getHeroDirection() == Hero.LEFT && controller.getHeroImageViewPosX() >= 0) {

					for (int i = 0; i < 11 && controller.getHeroMoving()
							&& controller.getHeroDirection() == Hero.LEFT; i++) {

						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						posX -= hero.getSpeed();
						controller.setHeroX(posX);
						controller.setHeroImage(controller.getRunningLeftImage(i));

					}

				}

			} else if (!controller.getHeroMoving() && !controller.getHeroCrouching()) {

				for (int i = 0; i < 6 && !controller.getHeroMoving() && !controller.getHeroCrouching(); i++) {

					try {
						Thread.sleep(80);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (hero.getDirection() == hero.RIGHT) {

						controller.setHeroImage(controller.getIddleRightImage(i));

					} else if (hero.getDirection() == hero.LEFT) {

						controller.setHeroImage(controller.getIddleLeftImage(i));

					}

				}

			} else if (!controller.getHeroMoving() && controller.getHeroCrouching()) {

				for (int i = 0; i < 5 && !controller.getHeroMoving() && controller.getHeroCrouching(); i++) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.LEFT) {

						controller.setHeroImage(controller.getCrouchingLeftImage(i));

					} else if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.RIGHT) {

						controller.setHeroImage(controller.getCrouchingRightImage(i));

					}

				}

			}

		}

	}

}
