package model;

public class Hero {

	public final static double DAMAGE = 1.0;
	public final static double HEALTH = 5.0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int LEFT = 3;
	public final static int DOWN = 4;
	public final static double HEIGHT = 110.0; 
	
	private double posX;
	private double posY;
	private boolean moving = false;
	private boolean falling = true;
	private int direction;
	private String imgRight = "";
	private String imgLeft = "";
	private double speed = 8.0;

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
		return HEALTH;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
