package model;

public interface Damage {

	static int weapon1Damage = 1;
	static int weapon2Damage = 3;
	static int weapon3Damage = 5;
	static int robot1Damage = 1;
	static int robot2Damage = 2;
	static int robot3Damage = 2;
	static int boosDamage = 4;
	
	public int getWeapon1Damage();
	public int getWeapon2Damage();
	public int getWeapon3Damage();
	public int getRobot1Damage();
	public int getRobot2Damage();
	public int getRobot3Damage();
	public void takeDamage(int dmg);
	
}
