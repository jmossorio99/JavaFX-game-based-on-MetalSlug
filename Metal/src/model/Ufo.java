package model;

public class Ufo extends Entity{

	public int movementSpeed=10;
	private String imageName = "file:data/sprites/ufo/ufo.png";
	private int xLayout;
	private int yLayout;
	private boolean right=true;
	
	
	public Ufo(int x, int y) {
		super(x,y);
		xLayout=x;
		yLayout=y;
		
	}

	public String getImageName() {
		return imageName;
	}
	
	public int getxLayout() {
		return xLayout;
	}

	public void setxLayout(int xLayout) {
		this.xLayout = xLayout;
	}

	public int getyLayout() {
		return yLayout;
	}

	public void setyLayout(int yLayout) {
		this.yLayout = yLayout;
	}
	
	public void moveToRight() {
		xLayout+=movementSpeed;
	}
	
	public void moveToLeft() {
		yLayout-=movementSpeed;
	}
	
	public void moveUfo() {
		if(xLayout>=1200) {
			
			right=false;
			
		} 
		if(xLayout<=10) {
			
			right = true;
			
		}
		
		if(right) {
			moveToRight();
		}
		else {
			moveToLeft();
		}
	}
}
