package model;

public class KilledEnemies {
	
	private int killedEnemies;
	private KilledEnemies next;
	private KilledEnemies prev;
	
	public KilledEnemies(int killed) {
		killedEnemies=killed;
	}

	public int getKilledEnemies() {
		return killedEnemies;
	}

	public void setKilledEnemies(int killedEnemies) {
		this.killedEnemies = killedEnemies;
	}

	public KilledEnemies getNext() {
		return next;
	}

	public void setNext(KilledEnemies next) {
		this.next = next;
	}

	public KilledEnemies getPrev() {
		return prev;
	}

	public void setPrev(KilledEnemies prev) {
		this.prev = prev;
	} 
	

}
