package model;

public class Block {

	private double posX;
	private double posY;
	private double width;
	private double height;
	private String imageRoute;

	public Block(double posX, double posY, double width, double height, String imageRoute) {

		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.imageRoute = imageRoute;

	}

	public boolean isOnPlatform(Hero hero) {

		double heroPosY = hero.getPosY() + hero.getHeight();
		boolean ret = false;
		if (heroPosY >= posY-1) {
			ret = true;
		}
		
		return ret;

	}

}
