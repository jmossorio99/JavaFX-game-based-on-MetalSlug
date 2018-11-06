package threads;

import controller.GameViewController;
import javafx.scene.image.Image;
import model.Hero;

public class HeroThread extends Thread {

	private GameViewController controller;
	private boolean running = false;
	private double posX;
	private double posY;
	private Hero hero;
	private boolean alreadyDead = false;

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
		// temporal para no tener que cerrar y volver a abrir pd: no funciona xdxdxxd
		if (hero.getDying() == false) {
			alreadyDead = false;
		}
		// -------------------------------------
		if (!alreadyDead) {
			init();
			while (running) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (controller.getHeroFalling() && !controller.getHeroJumping()) {
					while (controller.getHeroFalling()) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						posY += hero.getSpeed();
						controller.setHeroY(posY);
						if (controller.getHeroDirection() == Hero.RIGHT) {
							controller.setHeroImage(new Image("file:data/sprites/hero/Jump/jump3D.png"));
						} else if (controller.getHeroDirection() == Hero.LEFT) {
							controller.setHeroImage(new Image("file:data/sprites/hero/Jump/jump3I.png"));
						}
					}

				}
				// moverse y animacion correr derecha
				if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.RIGHT
						&& controller.getHeroImageViewPosX() <= 1100 && !controller.getHeroJumping() && !alreadyDead) {

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
						&& controller.getHeroImageViewPosX() >= 0 && !controller.getHeroJumping() && !alreadyDead) {

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
				} else if (!controller.getHeroMoving() && !controller.getHeroCrouching() && !controller.getHeroJumping()
						&& !alreadyDead) {

					for (int i = 0; i < 6; i++) {

						if (controller.getHeroCrouching() || controller.getHeroMoving()) {

							// System.out.println(1);
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

				} else if (controller.getHeroJumping() && !controller.getHeroMoving() && !controller.getHeroCrouching()
						&& !alreadyDead) {

					for (int i = 0; i < 6; i++) {

					}

				}
				// agacharse
				else if (!controller.getHeroMoving() && controller.getHeroCrouching() && !controller.getHeroJumping()
						&& !alreadyDead) {

					for (int i = 0; i < 5; i++) {

						if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.LEFT
								&& !controller.getHeroMoving() && !alreadyDead) {

							try {
								Thread.sleep(80);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							controller.setHeroImage(controller.getCrouchingLeftImage(i));

						} else if (controller.getHeroCrouching() && controller.getHeroDirection() == hero.RIGHT
								&& !controller.getHeroMoving() && !alreadyDead) {

							try {
								Thread.sleep(80);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							controller.setHeroImage(controller.getCrouchingRightImage(i));

						}

					}

					// Morir -----------------------------------------
				}
				if (controller.getHeroDying() && !alreadyDead) {
					int rep = 0;
					boolean cont = true;
					for (int i = 0; i < 4 && cont; i++) {

						if (controller.getHeroDirection() == hero.RIGHT) {

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							controller.setHeroImage(controller.getDyingRightImage(i));
							rep++;
						} else if (controller.getHeroDirection() == hero.LEFT) {

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							controller.setHeroImage(controller.getDyingLeftImage(i));
							rep++;
						}

						if (rep == 4) {
							cont = false;
							alreadyDead = true;
						}

					}

				}

			}

		}
	}

}
