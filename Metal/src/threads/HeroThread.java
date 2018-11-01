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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// moverse y animacion correr derecha
			if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.RIGHT
					&& controller.getHeroImageViewPosX() <= 1100) {

				for (int i = 0; i < 11; i++) {

					if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.RIGHT
							&& controller.getHeroImageViewPosX() <= 1100) {
						try {
							Thread.sleep(60);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						posX += hero.getSpeed();
						controller.setHeroX(posX);
						controller.setHeroImage(controller.getRunningRightImage(i));
					}

				}

				// moverse y animacion correr izquierda
			} else if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.LEFT
					&& controller.getHeroImageViewPosX() >= 0) {

				for (int i = 0; i < 11; i++) {

					if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.LEFT
							&& controller.getHeroImageViewPosX() >= 0) {
						try {
							Thread.sleep(60);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						posX -= hero.getSpeed();
						controller.setHeroX(posX);
						controller.setHeroImage(controller.getRunningLeftImage(i));
					}

				}

				// iddle derecha e izquierda
			} else if (!controller.getHeroMoving() && !controller.getHeroCrouching()) {

				for (int i = 0; i < 6; i++) {

					System.out.println(controller.getHeroMoving());
					if (controller.getHeroCrouching() || controller.getHeroMoving()) {

						System.out.println(1);
						break;
					}

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (hero.getDirection() == hero.RIGHT) {
						controller.setHeroImage(controller.getIddleRightImage(i));
					}

					else if (hero.getDirection() == hero.LEFT) {

						controller.setHeroImage(controller.getIddleLeftImage(i));

					}

				}

			}
			// agacharse
			else if (!controller.getHeroMoving() && controller.getHeroCrouching()) {

				for (int i = 0; i < 5; i++) {

					if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.LEFT
							&& !controller.getHeroMoving()) {

						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getCrouchingLeftImage(i));

					} else if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.RIGHT
							&& !controller.getHeroMoving()) {

						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getCrouchingRightImage(i));

					}

				}

			}

		}

	}

}
