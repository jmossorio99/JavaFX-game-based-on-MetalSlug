package model;

import javax.print.attribute.SetOfIntegerSyntax;

public class Hero extends Entity {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int LEFT = 3;
	public final static int DOWN = 4;
	public final static double HEIGHT = 110.0;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

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

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * M�todo constructor. Crea el personaje principal del juego.
	 * 
	 * @param posX: Posici�n inicial en el eje X del personaje.
	 * @param posY: Posici�n inicial en el eje Y del personaje.
	 * @param height: Altura del personaje.
	 */
	public Hero(double posX, double posY, double height) {

		super(posX, posY);
		this.posX = posX;
		this.posY = posY;
		direction = RIGHT;
		health = 10;
		this.setHeight(height);

	}

	/**
	 * Indica si el personaje se est� moviendo.
	 * 
	 * @return true si se est� moviendo. false si no lo est�.
	 */
	public boolean isMoving() {
		return moving;
	}

	/**
	 * Cambia el valor de moving.
	 * 
	 * @param moving nuevo valor para la variable.
	 */
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	/**
	 * Devuelve la direccion a la que apunta el personaje.
	 * 
	 * @return direccion: Direccion en donde apunta.
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Cambia la direccion del personaje.
	 * 
	 * @param direction: nuevo valor.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Devuelve la valocidad del personaje.
	 * 
	 * @return speed: Velocidad.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Cambia la velocidad
	 * 
	 * @param speed: nuevo valor.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Indica si el personaje se esta agachando.
	 * 
	 * @return crouching.
	 */
	public boolean isCrouching() {
		return crouching;
	}

	/**
	 * Cambia entre agacharse o pararse.
	 * 
	 * @param crouching: nuevo valor.
	 */
	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	/**
	 * Indica si est� apuntando hacia arriba.
	 * 
	 * @return aimingUp.
	 */
	public boolean isAimingUp() {
		return aimingUp;
	}

	/**
	 * Cambia el valor de aimingUp.
	 * 
	 * @param aimingUp: nuevo valor.
	 */
	public void setAimingUp(boolean aimingUp) {
		this.aimingUp = aimingUp;
	}

	/**
	 * Devuelve la altura.
	 * 
	 * @return height: altura del personaje.
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Cambia la altura del personaje.
	 * 
	 * @param height: nuevo valor.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Cambia entre si se est� muriendo o no.
	 * 
	 * @param dying: nuevo valor, true si se est� muriendp, false si no lo esta.
	 */
	public void setDying(boolean dying) {
		this.dying = dying;
	}

	/**
	 * Indica si se est� muriendo.
	 * 
	 * @return true si est� agonizando, false si no lo esta.
	 */
	public boolean getDying() {
		return dying;
	}

	/**
	 * Indica si esta disparando.
	 * 
	 * @return shooting: true si est� disparando, false si no lo est�
	 */
	public boolean isShooting() {
		return shooting;
	}

	/**
	 * Cambia el valor de shooting.
	 * 
	 * @param shooting: nuevo valor
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	/**
	 * Indica si est� muerto.
	 * 
	 * @return dead: true si ya se muri�, false si sigue vivo.
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Cambia el valor de dead..
	 * 
	 * @param dead: nuevo valor.
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * Devuelve el directorio de la imagen del personaje.
	 * 
	 * @return image: Directorio de la imagen.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Cambia la imagen del personaje.
	 * 
	 * @param image: nuevo directorio.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Cambia la imagen del personage y tambi�n su posici�n en el eje X seg�n su
	 * velocidad y direccion a la que apunta.
	 */
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

	/**
	 * Hace que el personaje se agache cambiando su imagen.
	 */
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

	/**
	 * Hace la animaci�n de quedarse quieto mientras no se est� muriendo.
	 */
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

	/**
	 * Animaci�n de disparar estando de pie.
	 */
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

	/**
	 * Animaci�n de disparar estando agachado.
	 */
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

	/**
	 * Hace la animaci�n correspondiente a cuando recibe un da�o.
	 */
	public void takeDamageAnim() {

		if (!dying) {

			if (direction == RIGHT) {
				image = "file:data/sprites/hero/Dead/right/dead2D.png";
			} else if (direction == LEFT) {
				image = "file:data/sprites/hero/Dead/left/dead2I.png";
			}

		}
	}

	/**
	 * Animaci�n de morir.
	 */
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

	/**
	 * Indica si el heroe est� recibiendo da�o en el momento en que se llame este
	 * m�todo momento.
	 * 
	 * @return - retorna un boolean que indica si el h�roe est� recibiendo da�o
	 */
	public boolean isTakingDamage() {
		return takingDamage;
	}

	/**
	 * Cambia el valor de la variable cuando el heroe est� recibiendo da�o-
	 * 
	 * @param takingDamage: nuevo valor
	 */
	public void setTakingDamage(boolean takingDamage) {
		this.takingDamage = takingDamage;
	}

	/**
	 * Establece el estado actual del heroe con los datos del estado anterior.
	 * 
	 * @param moving: indica si se est� moviendo.
	 * @param crouching: indica si se est� agachadno.
	 * @param aimingUp: indica si est� apuntado hacia arriba.
	 * @param shooting: indica si est� disparando.
	 * @param dead: indica si est� muerto.
	 * @param isTakingDamage: indica si est� recibiendo da�o.
	 * @param direction: direcci�n a la que apunta.
	 * @param health: salud del heroe.
	 */
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
