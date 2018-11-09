package model;

public class Hero extends Entity{

	public final static double DAMAGE = 1.0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int LEFT = 3;
	public final static int DOWN = 4;
	public final static double HEIGHT = 110.0;
	private boolean moving = false;
	private boolean crouching = false;;
	private boolean dying = false;
	private int direction;
	private boolean aimingUp = false;
	private double speed = 10;
	private double height;

	public Hero(double posX, double posY, double height) {

		super(posX, posY);
		this.posX = posX;
		this.posY = posY;
		direction = RIGHT;
		health = 5;
		this.setHeight(height); 

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
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

	public boolean isCrouching() {
		return crouching;
	}

	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	public boolean isAimingUp() {
		return aimingUp;
	}

	public void setAimingUp(boolean aimingUp) {
		this.aimingUp = aimingUp;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setDying(boolean dying) {
		this.dying=dying;
	}
	public boolean getDying(){
		return dying;
	}

}
