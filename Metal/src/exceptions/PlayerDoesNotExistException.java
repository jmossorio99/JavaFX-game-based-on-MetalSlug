package exceptions;

public class PlayerDoesNotExistException extends Exception {

	public PlayerDoesNotExistException(String name) {
		super("El jugador con el nombre \"" + name + "\" no existe.");
	}

}
