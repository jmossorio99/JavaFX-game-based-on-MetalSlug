package model;

/**
 * Clase Tank, hereda de Entity. Es la parte del modelo del personaje tanque, el
 * cual simula disparar las balas que caen desde el cielo
 *
 */
public class Tank extends Entity {

	// atributos
	private String tankImage = "file:data/sprites/tank/tank_01.png";
	private int imageCounter = 1;

	/**
	 * Constructor de la clase
	 * 
	 * @param posX - posicion inicial en x del tanque
	 * @param posY - posicion inicial en y del tanque
	 */
	public Tank(double posX, double posY) {
		super(posX, posY);

	}

	/**
	 * Este m�todo cambia la imagen actual del tanque dependiendo de cu�l sea la que
	 * tenga al momento de llamar el m�todo
	 */
	public void changeTankImages() {

		if (imageCounter < 5) {
			tankImage = "file:data/sprites/tank/tank_0" + imageCounter + ".png";
			imageCounter++;
		} else {
			tankImage = "file:data/sprites/tank/tank_01.png";
			imageCounter = 1;
		}

	}

	/**
	 * M�todo que retorna la imagen del tanque
	 * 
	 * @return un string con la ruta de la imagen del tanque
	 */
	public String getTankImage() {
		return tankImage;
	}
}
