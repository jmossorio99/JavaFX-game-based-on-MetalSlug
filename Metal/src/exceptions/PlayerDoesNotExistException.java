package exceptions;

/**
 * Clase excepción PlayerDoesNotExistException
 *
 */
public class PlayerDoesNotExistException extends Exception {

	/**
	 * Constructor de la clase
	 * 
	 * @param message - mensaje que mostrará la excepción cuando sea lanzada
	 */
	public PlayerDoesNotExistException(String message) {
		super(message);
	}

}
