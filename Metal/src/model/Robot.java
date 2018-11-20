package model;

public class Robot extends Entity {

	public boolean moving;
	public boolean dead;

	public Robot(double posX, double posY) {

		super(posX, posY);
		health = 1;

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
