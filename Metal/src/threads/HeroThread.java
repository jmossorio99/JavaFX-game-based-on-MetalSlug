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
	private double posX;
	private double posY;
	private Hero hero;
	private boolean alreadyDead = false;
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

			// System.out.println(controller.getHeroImageViewPosY());
			try {
				Thread.sleep(30);
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
			} else if (!controller.getHeroMoving() && !controller.getHeroCrouching() && !alreadyDead) {

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

			}
			// agacharse
			else if (!controller.getHeroMoving() && controller.getHeroCrouching() && !alreadyDead) {

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
