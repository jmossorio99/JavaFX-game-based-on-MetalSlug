package exceptions;

/**
 * Clase excepcion NoSortCriteriaExcepcion
 *
 */
public class NoSortCriteriaException extends Exception {

	/**
	 * Constructor de la clase
	 */
	public NoSortCriteriaException() {
		super("Debe seleccionar el criterio de búsqueda primero.");
	}

}
