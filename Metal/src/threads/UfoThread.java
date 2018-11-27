package threads;

import model.Ufo;

/**
 * Clase UfoThread, hilo que se encarga de mover al Ufo en el juego
 *
 */
public class UfoThread extends Thread {

	// atributos
	public Ufo ufo;

	/**
	 * Constructor de la clase
	 * 
	 * @param ufo - Ufo el cual será controlado por el hilo
	 */
	public UfoThread(Ufo ufo) {
		super();
		this.ufo = ufo;
	}

	/**
	 * Método que se ejecuta cuando se llama el método start del hilo
	 */
	public void run() {

		while (true) {

			ufo.moveUfo();

			try {
				this.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
