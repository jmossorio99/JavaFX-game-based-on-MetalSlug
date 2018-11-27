package model;

/**
 * Clase Entity, la heredar�n todos los personajes del juego
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
	 * @param posX - posici�n inicial en X de la entidad
	 * @param posY - posici�n inicial en Y de la entidad
	 */
	public Entity(double posX, double posY) {

		this.posX = posX;
		this.posY = posY;

	}

	/**
	 * Retorna la posici�n en X
	 * 
	 * @return posX - la posicion en X
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * Cambia la posici�n en X
	 * 
	 * @param posX - la nueva posici�n en X Pos: se cambia la posici�n en X de la
	 *             entidad
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Retorna la posici�n en y
	 * 
	 * @return posY - la posici�n en Y de la entidad
	 */
	public double getPosY() {
		return posY;
	}

	/**
	 * Cambia la posici�n en Y
	 * 
	 * @param posY - nueva posici�n en Y Pos: se cambia la posici�n en Y
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
