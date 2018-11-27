package exceptions;

/**
 * Excepcion ArrayListIsNotSortedException, excepci�n cuando el ArrayList no est� ordenado
 *
 */
public class ArrayListIsNotSortedException extends Exception {
	
	/**
	 * Constructor de la clase
	 * @param message - mensaje que va a mostrar el programa cuando se lance la excepci�n
	 */
	public ArrayListIsNotSortedException(String message) {
		super(message);
	}

}
