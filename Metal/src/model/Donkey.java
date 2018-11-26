package model;

public class Donkey extends Entity {

	private String imgRoute = "file:data/sprites/horse/horse_1.png";
	private int movingCount = 1;

	public Donkey(double posX, double posY) {
		super(posX, posY);

	}

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

	public String getImage() {
		return imgRoute;
	}

}
