package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.Player;

class PlayerTest extends TestCase {
	
	private Player player1;
	
	/**
	 * Se crea la raiz del arbol.
	 */
	public void setupScene1() {
		player1 = new Player("Player1");
		assertTrue( player1.getName().equals("Player1") );
	}
	
	/**
	 * Se crean los demás nodos del arbol.
	 */
	public void setupScene2() {
		setupScene1();
		
	}
	
	/**
	 * Prueba el método insertPlayer(Player) para insertar un jugador a la izquierda de la raiz. 
	 */
	@Test
	public void insertPlayerToLeftSideTest() {
		setupScene1();
		player1.insertPlayer( new Player("Camilo") );
		assertNotNull( player1.getRight() );
	}
	
}
