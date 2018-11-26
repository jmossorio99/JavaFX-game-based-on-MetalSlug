package threads;

import model.Donkey;

public class DonkeyThread extends Thread {

	private Donkey donkey;
	private boolean running = false;

	public DonkeyThread(Donkey donkey) {

		this.donkey = donkey;

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
			donkey.move();
			if (donkey.getPosX() == 50) {
				running = false;
			}

		}

	}

}
