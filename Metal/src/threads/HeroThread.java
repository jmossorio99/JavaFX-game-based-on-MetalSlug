package threads;

import java.util.ArrayList;
import controller.GameViewController;
import javafx.scene.image.Image;
import model.Block;
import model.Game;
import model.Hero;

public class HeroThread extends Thread {

	private GameViewController controller;
	private boolean running = false;
	private boolean alreadyDead = false;
	private double posX;
	private double posY;
	private Hero hero;
	private Game game;
	private ArrayList<Block> blocks;

	public HeroThread(GameViewController controller, Hero hero, Game game) {

		this.controller = controller;
		posX = hero.getPosX();
		posY = hero.getPosY();
		this.hero = hero;
		this.game = game;
		blocks = game.getBlocks();

	}

	public void init() {

		running = true;

	}

	@Override
	public void run() {

		init();
		while (running) {

			if (controller.getHeroImageViewPosY() == 585 && !hero.isCrouching()) {
				controller.setHeroY(560);
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

			}
			// disparar derecha, izquierda, arriba parado
			else if (hero.isShooting() && !hero.isCrouching() && !hero.isAimingUp()) {

				hero.setMoving(false);
				if (hero.getDirection() == Hero.RIGHT) {

					for (int i = 0; i < 4; i++) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getFireStandingRightImage(i));
					}

				} else if (hero.getDirection() == Hero.LEFT) {

					for (int i = 0; i < 4; i++) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getFireStandingLeftImage(i));
					}

				}

			} else if (hero.isShooting() && hero.isCrouching() && !hero.isAimingUp()) {

				hero.setMoving(false);
				if (hero.getDirection() == hero.RIGHT) {

					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getFireCrouchingRightImage(i));
					}

				} else if (hero.getDirection() == hero.LEFT) {
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getFireCrouchingLeftImage(i));
					}
				}

			}
			// moverse y animacion correr izquierda
			else if (controller.getHeroMoving() && controller.getHeroDirection() == Hero.LEFT
					&& controller.getHeroImageViewPosX() >= 0 && !alreadyDead) {

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
			} else if (!controller.getHeroMoving() && !controller.getHeroCrouching() && !alreadyDead
					&& !hero.isShooting()) {

				for (int i = 0; i < 6; i++) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (hero.getDirection() == hero.RIGHT) {
						controller.setHeroImage(controller.getIddleRightImage(i));
					}
					if (hero.isCrouching() || hero.isMoving() || hero.isShooting()) {

						// System.out.println(1);
						break;
					}

					else if (hero.getDirection() == hero.LEFT) {

						controller.setHeroImage(controller.getIddleLeftImage(i));

					}

				}

			}
			// agacharse
			else if (!controller.getHeroMoving() && controller.getHeroCrouching() && !alreadyDead
					&& !hero.isShooting()) {

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
					if (controller.getHeroImageViewPosY() == 560) {
						controller.setHeroY(585);
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
