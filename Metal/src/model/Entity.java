package model;

public class Entity implements Damage, GameView {

	protected double posX;
	protected double posY;
	protected int health;
	
	public Entity(double posX, double posY) {
		
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void takeDamage(int dmg) {
		setHealth(getHealth() - dmg);
	}

}
