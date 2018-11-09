package model;

import controller.GameViewController;

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

	public boolean isOnPlatform(GameViewController controller) {

		double heroPosY = controller.getHeroImageViewPosY() + Hero.HEIGHT;
		double heroPosX = controller.getHeroImageViewPosX();
		boolean ret = false;
		if (heroPosY >= posY+10) {
			ret = true;
		}
		
		return ret;

	}

}
