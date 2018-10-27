package threads;

import controller.GameViewController;
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

			if(controller.getHeroMoving() && controller.getHeroImageViewPosX() < 1099 && controller.getHeroImageViewPosX() > -50) {

				if (controller.getHeroDirection() == Hero.RIGHT) {
					posX+=8;
					controller.setHeroX(posX);
				}

			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
