package model;

import javax.print.attribute.SetOfIntegerSyntax;

public class Hero extends Entity {

//	public final static int ORANGE_DAMAGE = 1;
//	public final static int BLUE_DAMAGE = 2;
//	public final static int PURPLE_DAMAGE = 3;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int LEFT = 3;
	public final static int DOWN = 4;
	public final static double HEIGHT = 110.0;
	private int moveCount = 1;
	private int crouchCount = 1;
	private int iddleCount = 1;
	private int shootStandingCount = 1;
	private int shootCrouchingCount = 1;
	private int dyingImages = 1;
	private boolean moving = false;
	private boolean crouching = false;;
	private boolean dying = false;
	private boolean aimingUp = false;
	private boolean shooting = false;
	private boolean dead = false;
	private boolean takingDamage = false;
	private int direction;
	private double speed = 15;
	private double height;
	private String image = "file:data/sprites/hero/Iddle/right/Idle1D.png";

	public Hero(double posX, double posY, double height) {

		super(posX, posY);
		this.posX = posX;
		this.posY = posY;
		direction = RIGHT;
		health = 10;
		this.setHeight(height);

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isCrouching() {
		return crouching;
	}

	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	public boolean isAimingUp() {
		return aimingUp;
	}

	public void setAimingUp(boolean aimingUp) {
		this.aimingUp = aimingUp;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public boolean getDying() {
		return dying;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void move() {

		if (!dying) {

			if (moveCount < 12) {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Running/Right/Run" + moveCount + "D.png";
					setPosX(getPosX() + speed);
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Running/Left/Run" + moveCount + "I.png";
					setPosX(getPosX() - speed);
				}
				moveCount++;
			} else {

				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Running/Right/Run1D.png";
					setPosX(getPosX() + speed);
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Running/Left/Run1I.png";
					setPosX(getPosX() - speed);
				}
				moveCount = 1;

			}
		}
	}

	public void crouch() {

		if (!dying) {

			if (crouchCount < 6) {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Crouch/right/crouch" + crouchCount + "D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Crouch/left/crouch" + crouchCount + "I.png";
				}
				crouchCount++;
			} else {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Crouch/right/crouch1D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Crouch/left/crouch1I.png";
				}
				crouchCount = 1;
			}
		}
	}

	public void iddle() {

		if (!dying) {

			if (iddleCount < 7) {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Iddle/right/Idle" + iddleCount + "D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Iddle/left/Idle" + iddleCount + "I.png";
				}
				iddleCount++;
			} else {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Iddle/right/Idle1D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Iddle/left/Idle1I.png";
				}
				iddleCount = 1;
			}
		}
	}

	public void shootStanding() {
		if (!dying) {

			if (shootStandingCount < 5) {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Shoot/fireStandingRight/fire" + shootStandingCount + "D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Shoot/fireStandingLeft/fire" + shootStandingCount + "I.png";
				}
				shootStandingCount++;
			} else {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Shoot/fireStandingRight/fire1D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Shoot/fireStandingLeft/fire1I.png";
				}
				shootStandingCount = 1;
			}
		}
	}

	public void shootCrouching() {

		if (!dying) {

			if (shootCrouchingCount < 4) {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Shoot/crouchFireRight/CrouchFire" + shootCrouchingCount + "D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Shoot/crouchFireLeft/crouchfire" + shootCrouchingCount + "I.png";
				}
				shootCrouchingCount++;
			} else {
				if (direction == RIGHT) {
					image = "file:data/sprites/hero/Shoot/crouchFireRight/CrouchFire1D.png";
				} else if (direction == LEFT) {
					image = "file:data/sprites/hero/Shoot/crouchFireLeft/crouchfire1I.png";
				}
				shootCrouchingCount = 1;
			}
		}
	}

	public void takeDamageAnim() {

		if (!dying) {

			if (direction == RIGHT) {
				image = "file:data/sprites/hero/Dead/right/dead2D.png";
			} else if (direction == LEFT) {
				image = "file:data/sprites/hero/Dead/left/dead2I.png";
			}
			

		}
	}

	public void die() {

		while (dyingImages < 5) {

			if (direction == RIGHT) {
				image = "file:data/sprites/hero/dead/right/dead" + dyingImages + "D.png";
			} else if (direction == LEFT) {
				image = "file:data/sprites/hero/dead/left/dead" + dyingImages + "I.png";
			}

			dyingImages++;
		}

	}

	public boolean isTakingDamage() {
		return takingDamage;
	}

	public void setTakingDamage(boolean takingDamage) {
		this.takingDamage = takingDamage;
	}

	public void setStates(boolean moving, boolean crouching, boolean aimingUp, boolean shooting, boolean dead,
			boolean isTakingDamage, int direction, int health) {
		setMoving(moving);
		setCrouching(crouching);
		setAimingUp(aimingUp);
		setShooting(shooting);
		setDead(dead);
		setTakingDamage(isTakingDamage);
		setDirection(direction);
		setHealth(health);
	}

}
