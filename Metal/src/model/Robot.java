package model;

public class Robot extends Entity {

	private boolean moving;
	private boolean dead;
	private int index;
	private String img = "file:data/sprites/mini robot/mini-robot_1.png";

	public Robot(double posX, double posY, int i) {

		super(posX, posY);
		health = 2;
		setIndex(i);

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
