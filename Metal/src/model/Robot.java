package model;

public class Robot extends Entity {

	public static String RIGHT="Right";
	public static String LEFT="Left";
	public boolean moving;
	public boolean dead;
	private Robot left;
	private Robot right;
	private Robot next;
	private String direction;
	private int order;

	public int getOrder() {
		return order;
	}
	
	public Robot getNext() {
		return next;
	}
	
	public void setNext(Robot r) {
		next=r;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Robot getLeft() {
		return left;
	}

	public void setLeft(Robot left) {
		this.left = left;
	}

	public Robot getRight() {
		return right;
	}

	public void setRight(Robot right) {
		this.right = right;
	}

	public Robot(double posX, double posY, String direction) {

		super(posX, posY);
		health = 1;
		this.direction=direction;

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public int compareByDirection(Robot robot) {
		int result=0;
		result= this.getDirection().compareTo(robot.getDirection());
		
		if(result<0) {
			result=-1;
		}else {
			result=1;
		}
		
		return result;
	}
	

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public int getX() {
		int x=0;
	 if(direction.equals(RIGHT)) {
		 x=1100; 
	 }else {
		 x=10;
	 }
	 
	 return x;
	}
	public int getY() {
		return 580;
	}

}
