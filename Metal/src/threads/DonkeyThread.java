package threads;

import controller.GameViewController;
import model.Donkey;

public class DonkeyThread extends Thread {

	//Atributos
	private Donkey donkey;
	private boolean running = false;
	private GameViewController controller;

	/**
	 * Constructor de la clase
	 * @param donkey - el objeto de tipo Donkey que va a controlar este hilo
	 * @param controller - el controlador de la interfaz
	 */
	public DonkeyThread(Donkey donkey, GameViewController controller) {

		this.donkey = donkey;
		this.controller = controller;

	}

	/*
	 * Método que inicializa el hilo
	 * Pos: running es true
	 */
	public void init() {
		running = true;
	}

	/**
	 * Metodo run del hilo que se ejecuta al llamar el método start del hilo
	 */
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
					if(!controller.getHeroIsDead()) {
						controller.addHealth(5);
					}
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
