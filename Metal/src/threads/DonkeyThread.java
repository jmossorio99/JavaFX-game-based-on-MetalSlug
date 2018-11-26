package threads;

import controller.GameViewController;
import model.Donkey;

public class DonkeyThread extends Thread {

	private Donkey donkey;
	private boolean running = false;
	private GameViewController controller;

	public DonkeyThread(Donkey donkey, GameViewController controller) {

		this.donkey = donkey;
		this.controller = controller;

	}

	public void init() {
		running = true;
	}

	@Override
	public void run() {

		super.run();
		init();
		while (running) {

			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (controller.getMoveDonkey()) {
				donkey.move();
				if (donkey.getPosX() == 30) {
					controller.setMoveDonkey(false);
					controller.addHealth(5);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					controller.setPosXDonkeyImageView(-200);
					running = false;
				}
			}

		}

	}

}
