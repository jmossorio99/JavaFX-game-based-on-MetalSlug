package threads;

import controller.GameViewController;
import model.Hero;

public class ViewThread extends Thread {

	private GameViewController controller;
	private Hero hero;

	public ViewThread(GameViewController controller, Hero hero) {

		this.controller = controller;
		this.hero = hero;

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
			controller.setHeroImage(hero.getImage());
			controller.setHeroX(hero.getPosX());
			controller.setHeroY(hero.getPosY());

		}

	}

}
