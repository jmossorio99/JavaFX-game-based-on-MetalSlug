package exceptions;

/**
 * Excepcion ArrayListIsNotSortedException, excepción cuando el ArrayList no está ordenado
 *
 */
public class ArrayListIsNotSortedException extends Exception {
	
	/**
	 * Constructor de la clase
	 * @param message - mensaje que va a mostrar el programa cuando se lance la excepción
	 */
	public ArrayListIsNotSortedException(String message) {
		super(message);
	}

}
