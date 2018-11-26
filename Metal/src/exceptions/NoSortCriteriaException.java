package exceptions;

public class NoSortCriteriaException extends Exception {
	
	public NoSortCriteriaException() {
		super("Debe seleccionar el criterio de búsqueda primero.");
	}
	
}
