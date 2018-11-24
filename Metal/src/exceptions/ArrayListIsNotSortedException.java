package exceptions;

public class ArrayListIsNotSortedException extends Exception {
	
	public ArrayListIsNotSortedException() {
		super( "Se deben ordenar los jugadores por su nombre primero." );
	}

}
