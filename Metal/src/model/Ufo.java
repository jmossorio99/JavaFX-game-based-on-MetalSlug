package model;

public class Ufo extends Entity {

	public int movementSpeed = 15;
	private String imageName = "file:data/sprites/ufo/ufo.png";
	private boolean right = true;

	/**
	 * contructor de Ufo
	 * @param d - posición en X
	 * @param e - posición en Y
	 */
	public Ufo(double d, double e) {
		super(d, e);

	}
/**
 * este metodo retorna imageName (ruta de la imagen)
 * @return imageName : ruta
 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * este metodo mueve el x del ufo sumandole la constante
	 */
	public void moveToRight() {
		posX += movementSpeed;
	}

	public void moveToLeft() {
		posX -= movementSpeed;
	}

	public void moveUfo() {
		if (posX >= 1100) {

			right = false;

		}
		if (posX <= 10) {

			right = true;

		}

		if (right) {
			moveToRight();
		} else {
			moveToLeft();
		}
	}
}
