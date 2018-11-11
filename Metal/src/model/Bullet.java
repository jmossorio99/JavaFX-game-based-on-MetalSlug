package model;

public class Bullet {

	protected final static int MAX_MOVES = 20;
	protected int dmg;
	protected String imgDir = "";
	protected Bullet nextBullet = null;
	protected Bullet previousBullet = null;
	protected int moveCount = 0;

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

	public Bullet getNextBullet() {
		return nextBullet;
	}

	public void setNextBullet(Bullet nextBullet) {
		this.nextBullet = nextBullet;
	}

	public Bullet getPreviousBullet() {
		return previousBullet;
	}

	public void setPreviousBullet(Bullet previousBullet) {
		this.previousBullet = previousBullet;
	}

}
