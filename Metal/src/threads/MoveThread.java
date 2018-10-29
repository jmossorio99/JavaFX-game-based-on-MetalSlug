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

			if (controller.getHeroMoving()) {

				if (controller.getHeroDirection() == Hero.RIGHT) {

					if (controller.getHeroImageViewPosX() <= 1100) {

						posX += hero.getSpeed();
						controller.setHeroX(posX);

					}

				} else if (controller.getHeroDirection() == Hero.LEFT) {

					if (controller.getHeroImageViewPosX() >= -50) {

						posX -= hero.getSpeed();
						controller.setHeroX(posX);

					}

				}

			}else if(!controller.getHeroMoving()) {
				
				for (int i = 0; i < 6; i++) {
					
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (hero.getDirection() == hero.RIGHT) {
						
						controller.setHeroImage(controller.getIddleRightImage(i));
						
					} else if(hero.getDirection() == hero.LEFT){

						controller.setHeroImage(controller.getIddleLeftImage(i));
						
					}
					
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
