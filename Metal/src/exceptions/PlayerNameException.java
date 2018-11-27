package exceptions;

/**
 * Clase excepción PlayerNameExcepcion
 *
 */
public class PlayerNameException extends Exception {

	/**
	 * Constructor de la clase
	 * 
	 * @param message - mensaje que mostrará la excepción cuando sea lanzada
	 */
	public PlayerNameException(String message) {
		super(message);
	}

}
