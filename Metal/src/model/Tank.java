package model;

public class Tank extends Entity {

	private String tankImage = "file:data/sprites/tank/tank_01.png";
	private int imageCounter = 1;

	public Tank(double posX, double posY) {
		super(posX, posY);

	}

	public void changeTankImages() {

		if (imageCounter < 5) {
			tankImage = "file:data/sprites/tank/tank_0" + imageCounter + ".png";
			imageCounter++;
		} else {
			tankImage = "file:data/sprites/tank/tank_01.png";
			imageCounter = 1;
		}

	}

	public String getTankImage() {
		return tankImage;
	}
}
