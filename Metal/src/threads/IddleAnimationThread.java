package threads;

import controller.GameViewController;
import javafx.scene.image.Image;
import model.Hero;

public class IddleAnimationThread extends Thread {

	private GameViewController controller;
	private Hero hero;
	private boolean running = false;

	public IddleAnimationThread(GameViewController controller, Hero hero) {

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
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!controller.getHeroMoving() && !controller.getHeroCrouching()) {

				for (int i = 0; !controller.getHeroMoving() && !controller.getHeroCrouching() && i < 6; i++) {

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

			}

		}

	}

}
