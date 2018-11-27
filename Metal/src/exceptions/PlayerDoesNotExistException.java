package exceptions;

/**
 * Clase excepci�n PlayerDoesNotExistException
 *
 */
public class PlayerDoesNotExistException extends Exception {

	/**
	 * Constructor de la clase
	 * 
	 * @param message - mensaje que mostrar� la excepci�n cuando sea lanzada
	 */
	public PlayerDoesNotExistException(String message) {
		super(message);
	}

}
