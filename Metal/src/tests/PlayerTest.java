package tests;

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
		player1.addScore(100);
		assertTrue( player1.getName().equals("Player1") );
	}
	
	
	/**
	 * Se crean los dem�s nodos del arbol.
	 */
	public void setupScene2() {
		setupScene1();
		Player playerAdd = null;
		
		playerAdd = new Player("lol");
		playerAdd.addScore(75);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("Name");
		playerAdd.addScore(500);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("camilo");
		playerAdd.addScore(103);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("Victor");
		playerAdd.addScore(80);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("jose");
		playerAdd.addScore(96);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("david");
		playerAdd.addScore(150);
		player1.insertPlayer(playerAdd);
		
		playerAdd = new Player("Batman");
		playerAdd.addScore(475);
		player1.insertPlayer(playerAdd);
	}
	
	/**
	 * Prueba el m�todo insertPlayer(Player) para insertar un jugador a la izquierda de la raiz.
	 */
	@Test
	public void insertPlayerToLeftSideTest() {
		setupScene1();
		Player playerAdd = new Player("Camilo");
		playerAdd.addScore(62);
		player1.insertPlayer( playerAdd );
		assertNotNull( player1.getLeft() );
	}
	
	/**
	 * Prueba el m�todo insertPlayer(Player) para insertar un jugador a la derecha de la raiz.
	 */
	@Test
	public void insertPlayerToRightSideTest() {
		setupScene1();
		Player playerAdd = new Player("Buzz");
		playerAdd.addScore(154);
		player1.insertPlayer( playerAdd );
		assertNotNull( player1.getRight() );
	}
	
	/**
	 * Prueba el m�todo que devuelve el puntaje m�ximo de un jugador.
	 */
	@Test
	public void getMaxScoreTest() {
		setupScene1();
		player1.addScore(20);
		player1.addScore(150);
		player1.addScore(89);
		assertEquals("El puntage m�ximo debi� ser 150.", 150, player1.getMaxScore() );
	}
	
	
	/**
	 * Prueba que el m�todo playerExists(String) devuelve true si un jugador ya est� en el arbol o false si no lo est�.
	 */
	@Test
	public void playerExistsTest() {
		setupScene2();
		
		// Jugador que ya est� en el arbol.
		assertTrue( "Debi� devolver true.", player1.playerExists( "lol" ) );
		
		// Jugador que ya est� en el arbol.
		assertFalse( "Debi� devolver false.", player1.playerExists( "noPlayer" ) );
	}
	
	/**
	 * Prueba si un nodo es una hoja.
	 */
	@Test
	public void isLeafTest() {
		setupScene1();
		assertTrue( "Debi� devolver true ya que s� es una hoja.", player1.sheet() );
	}
	
	/**
	 * Prueba si se obtiene correctamente el jugador con el menor puntaje.
	 */
	@Test
	public void getLessTest() {
		setupScene2();
		assertTrue( "El jugador debi� ser el de nombre 'lol'.", player1.getLess().getName().equals("lol") );
	}
}
