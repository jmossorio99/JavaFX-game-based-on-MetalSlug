package model;

public class Tank extends Entity{
	
	public String tankImage="file:data/sprites/tank/tank_01.png";
	
	public Tank(double posX, double posY) {
		super(posX, posY);
		
	}

	public void changeTankImages() {

    		for(int i=1;i<5;i++) {
    			tankImage= "file:data/sprites/tank/tank_"+0+i+".png";
    		}
		
	}
	
	public String getTankImage() {
		return tankImage;
	}
}
