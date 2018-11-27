package model;

public class Ufo extends Entity {

	public int movementSpeed = 15;
	private String imageName = "file:data/sprites/ufo/ufo.png";
	private boolean right = true;

	public Ufo(double d, double e) {
		super(d, e);

	}

	public String getImageName() {
		return imageName;
	}

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
