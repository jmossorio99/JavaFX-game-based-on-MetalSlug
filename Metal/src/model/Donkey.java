package model;

/**
 * Clase Donkey, hereda de Entity y es otro personaje del juego
 *
 */
public class Donkey extends Entity {

	// atributos
	private String imgRoute = "file:data/sprites/horse/horse_1.png";
	private int movingCount = 1;

	/**
	 * Constructor de la clase
	 * 
	 * @param posX - posici�n inicial del Donkey en X
	 * @param posY - posici�n inicial del Donkey en Y
	 */
	public Donkey(double posX, double posY) {
		super(posX, posY);

	}

	/**
	 * Este m�todo se encarga de mover al Donkey y hacer la animaci�n de movimiento
	 * tambi�n Pos: cambia la posici�n en X del Donkey y su imagen
	 */
	public void move() {
		if (movingCount < 13) {
			imgRoute = "file:data/sprites/horse/horse_" + movingCount + ".png";
			movingCount++;
			setPosX(getPosX() + DONKEY_SPEED);
		} else {
			movingCount = 1;
			imgRoute = "file:data/sprites/horse/horse_1.png";
		}
	}

	/**
	 * Retorna la ruta actual de la imagen
	 * 
	 * @return la ruta de la imagen
	 */
	public String getImage() {
		return imgRoute;
	}

}
