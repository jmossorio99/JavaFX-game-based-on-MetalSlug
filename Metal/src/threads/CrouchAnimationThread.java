package threads;

import controller.GameViewController;
import model.Hero;

public class CrouchAnimationThread extends Thread {

	private GameViewController controller;
	private Hero hero;
	private boolean running = false;

	public CrouchAnimationThread(GameViewController controller, Hero hero) {

		this.controller = controller;
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!controller.getHeroMoving() && controller.getHeroCrouching()) {

				for (int i = 0; i < 5; i++) {

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
