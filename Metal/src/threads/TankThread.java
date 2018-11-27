package threads;

import model.Tank;

/**
 * Clase TankThread, hilo que se encarga de ejecutar la animación del tanque
 *
 */
public class TankThread extends Thread {

	// atributos
	public Tank tank;

	/**
	 * Constructor de la clase
	 * 
	 * @param tank - tanque el cual será controlado por el hilo
	 */
	public TankThread(Tank tank) {
		this.tank = tank;
	}

	/**
	 * Método que se ejecuta cuando se llama el método start del hilo
	 */
	public void run() {
		while (true) {

			tank.changeTankImages();

			try {
				this.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
