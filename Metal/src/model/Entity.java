package model;

public class Entity implements Damage {

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
	public int getWeapon1Damage() {
		return weapon1Damage;
	}

	@Override
	public int getWeapon2Damage() {
		return weapon2Damage;
	}

	@Override
	public int getWeapon3Damage() {
		return weapon3Damage;
	}

	@Override
	public void takeDamage(int dmg) {
		setHealth(getHealth() - dmg);
	}

	@Override
	public int getRobot1Damage() {
		return robot1Damage;
	}

	@Override
	public int getRobot2Damage() {
		return robot2Damage;
	}

	@Override
	public int getRobot3Damage() {
		return robot3Damage;
	}
	
	
	
}
