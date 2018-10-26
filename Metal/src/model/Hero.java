package model;

public class Hero {

	public final static double DAMAGE = 0.0;
	private double posX;
	private double posY;
	private double health;
	private boolean moving;

	public Hero(double posX, double posY) {

		this.posX = posX;
		this.posY = posY;

	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
