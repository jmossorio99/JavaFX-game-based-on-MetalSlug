package model;

public interface Damage {

	int weapon1Damage = 1;
	int weapon2Damage = 3;
	int weapon3Damage = 5;
	int robot1Damage = 1;
	int robot2Damage = 2;
	int robot3Damage = 2;
	int boosDamage = 4;
	
	public int getWeapon1Damage();
	public int getWeapon2Damage();
	public int getWeapon3Damage();
	public int getRobot1Damage();
	public int getRobot2Damage();
	public int getRobot3Damage();
	public void takeDamage(int dmg);
	
}
