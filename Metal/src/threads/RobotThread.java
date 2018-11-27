package threads;

import java.util.ArrayList;

import controller.GameViewController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase RobotThread, hilo que se encarga de mover a los robots en el juego y de
 * animar su movimiento
 * 
 *
 */
public class RobotThread extends Thread {

	// atributos
	private ArrayList<Image> images;
	private ArrayList<Node> robots;
	private ImageView robot;
	private boolean running = false;
	private GameViewController controller;

	/**
	 * Constructor de la clase
	 * 
	 * @param images     - arraylist de imagenes para la animacion de los robots
	 * @param robots     - arrayList de todos los robots
	 * @param controller - controlador de la interfaz
	 */
	public RobotThread(ArrayList<Image> images, ArrayList<Node> robots, GameViewController controller) {

		this.robots = robots;
		this.images = images;
		this.controller = controller;

	}

	/**
	 * Metodo que inicializa el hilo
	 */
	public void init() {

		running = true;

	}

	/**
	 * Método que se ejecuta cuando se llama al método start del hilo
	 */
	@Override
	public void run() {

		init();
		while (running && !controller.getHeroIsDead()) {
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 16; i++) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int j = 0; j < robots.size(); j++) {
					ImageView r = (ImageView) robots.get(j);
					r.setImage(images.get(i));
				}

			}
		}

	}

}
