package model;

/**
 * Clase Entity, la heredarán todos los personajes del juego
 *
 */
public class Entity implements Damage, GameView {

	// atributos
	protected double posX;
	protected double posY;
	protected int health;

	/**
	 * Constructor de la clase
	 * 
	 * @param posX - posición inicial en X de la entidad
	 * @param posY - posición inicial en Y de la entidad
	 */
	public Entity(double posX, double posY) {

		this.posX = posX;
		this.posY = posY;

	}

	/**
	 * Retorna la posición en X
	 * 
	 * @return posX - la posicion en X
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * Cambia la posición en X
	 * 
	 * @param posX - la nueva posición en X Pos: se cambia la posición en X de la
	 *             entidad
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Retorna la posición en y
	 * 
	 * @return posY - la posición en Y de la entidad
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * Cambia la posición en Y
	 * 
	 * @param posY - nueva posición en Y Pos: se cambia la posición en Y
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	/**
	 * retorna la salud de la entidad
	 * 
	 * @return health - la salud actual de la entidad
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * cambia la salud de la entidad
	 * 
	 * @param health - la nueva salud de la entidad Pos: se cambia la salud de la
	 *               entidad
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void takeDamage(int dmg) {
		if ((getHealth() - dmg) >= 0) {
			setHealth(getHealth() - dmg);
		}

	}

}
