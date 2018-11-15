package threads;

import java.util.ArrayList;
import controller.GameViewController;
import model.Block;
import model.Game;
import model.Hero;

public class HeroThread extends Thread {

	private GameViewController controller;
	private boolean alreadyDead = false;
	private Hero hero;
	private Game game;
	private ArrayList<Block> blocks;

	public HeroThread(GameViewController controller, Hero hero, Game game) {

		this.controller = controller;
		this.hero = hero;
		this.game = game;
		blocks = game.getBlocks();

	}

	@Override
	public void run() {

		super.run();
		while (!hero.isDead()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (hero.isMoving()) {
				if (!hero.isCrouching()) {
					if (hero.getDirection() == hero.RIGHT && hero.getPosX() <= 1100) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						hero.move();
					}
					if (hero.getDirection() == hero.LEFT && hero.getPosX() >= 0) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						hero.move();
					}
				}

			}
			if (hero.isCrouching()) {

				if (hero.isMoving()) {
					hero.setMoving(false);
				}
				if (!hero.isShooting()) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hero.crouch();
				}
				if (hero.isShooting()) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hero.shootCrouching();
				}

			}
			if (hero.isShooting()) {

				if (hero.isMoving()) {
					hero.setMoving(false);
				}
				if (!hero.isCrouching()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hero.shootStanding();
				}

			} else if (!hero.isMoving() && !hero.isCrouching()) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				hero.iddle();
			}

		}

	}
}
