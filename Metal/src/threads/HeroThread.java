package threads;

import java.io.IOException;
import java.util.ArrayList;
import controller.GameViewController;
import model.Game;
import model.Hero;

public class HeroThread extends Thread {

	//Atributos
	private GameViewController controller;
	private boolean alreadyDead = false;
	private Hero hero;
	private Game game;

	/**
	 * M�todo constructor de la clase
	 * @param controller - controlador de la interfaz
	 * @param hero - heroe que va a controlar este hilo
	 * @param game - objeto de tipo Game que contiene al h�roe
	 */
	public HeroThread(GameViewController controller, Hero hero, Game game) {

		this.controller = controller;
		this.hero = hero;
		this.game = game;

	}

	/**
	 * M�todo run del hilo. Se encarga de controlar todos los movimientos del h�roe
	 */
	@Override
	public void run() {

		super.run();
		while (!hero.isDead()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (hero.getHealth() <= 0) {
				hero.setDead(true);				
			}
			if (hero.isTakingDamage()) {
				hero.takeDamageAnim();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				hero.setTakingDamage(false);
			}
			if (hero.isMoving() && !hero.isTakingDamage()) {
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
			if (hero.isCrouching() && !hero.isTakingDamage()) {

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
			if (hero.isShooting() && !hero.isTakingDamage()) {

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

			} else if (!hero.isMoving() && !hero.isCrouching() && !hero.isTakingDamage()) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				hero.iddle();
			}

		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controller.playPlayerLoses();

	}
}
