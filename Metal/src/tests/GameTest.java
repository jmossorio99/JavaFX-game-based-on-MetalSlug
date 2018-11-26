package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Game;
import model.Player;

class GameTest {

	private Game game;
	
	/**
	 * ArrayList de un solo elemento.
	 */
	public void setupScene1() {
		game = new Game();
		game.addPlayerToArrayList( new Player( "Player" ) );
		game.getPlayersList().get(0).addScore(500); // Puntaje más alto 500
	}
	
	/**
	 * Once elementos.
	 */
	public void setupScene2() {
		setupScene1();
		game.addPlayerToArrayList( new Player( "L" ) );
		game.getPlayersList().get(1).addScore(480); // Puntaje repetido 480
		
		game.addPlayerToArrayList( new Player( "Victor" ) );
		game.getPlayersList().get(2).addScore(250); // Puntaje repetido 250
		
		game.addPlayerToArrayList( new Player( "555" ) );
		game.getPlayersList().get(3).addScore(250); // Puntaje repetido 250
		
		game.addPlayerToArrayList( new Player( "camilo" ) );
		game.getPlayersList().get(4).addScore(350);
		
		game.addPlayerToArrayList( new Player( "Jose" ) );
		game.getPlayersList().get(5).addScore(300);
		
		game.addPlayerToArrayList( new Player( "goku" ) );
		game.getPlayersList().get(6).addScore(480); // Puntaje repetido 480
		
		game.addPlayerToArrayList( new Player( "Zzz" ) );
		game.getPlayersList().get(7).addScore(230);
		
		game.addPlayerToArrayList( new Player( "Freddie" ) );
		game.getPlayersList().get(8).addScore(480); // Puntaje repetido 480
		
		game.addPlayerToArrayList( new Player( "123" ) );
		game.getPlayersList().get(9).addScore(100); // Puntaje más bajo 100
		
		game.addPlayerToArrayList( new Player( "david" ) );
		game.getPlayersList().get(10).addScore(360);
	}
	
	/**
	 * Prueba que el método para ordenar por nombre funciona si solo hay un jugador.
	 */
	@Test
	public void sortOneNameTest() {
		setupScene1();
		
		// Ascendente
		game.sortPlayerNames( 1 );
		assertTrue( game.getPlayersList().get(0).getName().equals( "Player" ), "El nombre no es el mismo" );
		
		// Descendente
		game.sortPlayerNames( -1 );
		assertTrue( game.getPlayersList().get(0).getName().equals( "Player" ), "El nombre no es el mismo" );
		
	}
	
	/**
	 * Prueba que el método para ordenar por nombre funciona si hay varios jugadores.
	 */
	@Test
	public void sortSeveralNamesTest() {
		setupScene2();
		
		// Ascendente
		game.sortPlayerNames( 1 );
		for( int i = 1; i < game.getPlayersList().size() - 1; i++ ) {
			if( game.getPlayersList().get(i - 1).compareTo( game.getPlayersList().get(i) ) > 0 ) {
				fail( "Hay un elemento que no está ordenado." );
			}
		}
		assertTrue( game.getPlayersList().get(0).getName().equals("123") ); // Primer elemento después de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("goku") ); // Último elemento después de ordenar
		
		// Descendente
		game.sortPlayerNames( -1 );
		for( int i = 1; i < game.getPlayersList().size() - 1; i++ ) {
			if( game.getPlayersList().get(i - 1).compareTo( game.getPlayersList().get(i) ) < 0 ) {
				fail( "Hay un elemento que no está ordenado." );
			}
		}
		assertTrue( game.getPlayersList().get(0).getName().equals("goku") ); // Primer elemento después de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("123") ); // Último elemento después de ordenar
	}
	
	/**
	 * Prueba el método de búsqueda binaria para un jugador en el medio de la lista.
	 */
	@Test
	public void searchMiddlePlayerTest() {
		setupScene2();
		Player found = null;
		
		// Orden ascendente
		game.sortPlayerNames( 1 );
		found = game.searchPlayerName( "Jose" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Jose" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		found = game.searchPlayerName( "Victor" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Victor" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayerName( "Jose" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Jose" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		found = game.searchPlayerName( "Victor" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Victor" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
	}
	
	/**
	 * Prueba el método de búsqueda binaria para el primer jugador de la lista.
	 */
	@Test
	public void searchFirstPlayerTest() {
		setupScene2();
		Player found = null;
		
		// Orden ascendente
		game.sortPlayerNames( 1 );
		found = game.searchPlayerName( "123" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get(0).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayerName( "goku" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get(0).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );		
	}
	
	/**
	 * Prueba el método de búsqueda binaria para el último jugador de la lista.
	 */
	@Test
	public void searchLastPlayerTest() {
		setupScene2();
		Player found = null;
		
		// Orden ascendente
		game.sortPlayerNames( 1 );
		found = game.searchPlayerName( "goku" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayerName( "123" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
	}
	
	/**
	 * Prueba el método de búsqueda binaria para un jugador que no está en la lista.
	 */
	@Test
	public void searchNonExistentPlayerTest() {
		setupScene2();
		
		game.sortPlayerNames( 1 );
		Player found = game.searchPlayerName( "NoPlayer" );
		assertNull( found, "Se encontró un jugador cuando debería devolver null." );
	}
	
	/**
	 * Prueba que la búsqueda binaria encuentra un jugador dado un puntaje que solo lo tiene ese jugador.
	 */
	@Test
	public void searchOneScorePlayerTest() {
		setupScene2();
		game.sortPlayerScores(true);
		ArrayList<Player> playersFound = null;
		
		playersFound = game.searchPlayerScore(350);
		assertEquals(1, playersFound.size(), "El ArrayList debe tener un elemento.");
		assertTrue( playersFound.get(0).getName().equals("camilo"), "El nombre del jugador encontrado no coincide con el nombre del jugador con el puntaje buscado." );
		
		playersFound = game.searchPlayerScore(230);
		assertEquals(1, playersFound.size(), "El ArrayList debe tener un elemento.");
		assertTrue( playersFound.get(0).getName().equals("Zzz"), "El nombre del jugador encontrado no coincide con el nombre del jugador con el puntaje buscado." );
	}
	
	/**
	 * Prueba que la búsqueda binaria encuentra los jugadores que tienen el puntaje dado como parámetro.
	 */
	@Test
	public void searchSeveralScorePlayerTest() {
		setupScene2();
		game.sortPlayerScores(true);
		ArrayList<Player> playersFound = null;
		
		playersFound = game.searchPlayerScore(480);
		assertEquals(3, playersFound.size(), "El ArrayList debe tener tres elementos.");
		
		playersFound = game.searchPlayerScore(250);
		assertEquals(2, playersFound.size(), "El ArrayList debe tener dos elementos.");
	}
	
	/**
	 * Prueba que la búsqueda binaria encuentra el o los jugadores que tienen el puntaje más bajo.
	 */
	@Test
	public void searchLowestScoreTest() {
		setupScene2();
		game.sortPlayerScores(true);
		ArrayList<Player> playersFound = null;
		
		playersFound = game.searchPlayerScore(100);
		assertEquals(1, playersFound.size(), "El ArrayList debe tener un elemento.");
	}
	
	/**
	 * Prueba que la búsqueda binaria encuentra el o los jugadores que tienen el puntaje más alto.
	 */
	@Test
	public void searchHighestScoreTest() {
		setupScene2();
		game.sortPlayerScores(true);
		ArrayList<Player> playersFound = null;
		
		playersFound = game.searchPlayerScore(500);
		assertEquals(1, playersFound.size(), "El ArrayList debe tener un elemento.");
	}
	
	/**
	 * Prueba que el método para eliminar un jugador es capaz de eliminar la raíz del árbol.
	 */
	@Test
	public void deleteRootTest() {
		setupScene2();
		game.sortPlayerNames(1);
		game.deletePlayerFromTree( game.searchPlayerName("Player") );
		assertFalse( game.playerExists("Player"), "El jugador debió haber sido eliminado." );
	}
	
	/**
	 * Prueba que el método para eliminar un jugador es capaz de eliminar una hoja del árbol.
	 */
	@Test
	public void deleteLeafTest() {
		setupScene1();
		game.sortPlayerNames(1);
		game.deletePlayerFromTree( game.searchPlayerName("Player") );
		assertFalse( game.playerExists("Player"), "El jugador debió haber sido eliminado." );
	}
	
	/**
	 * Prueba que el método para eliminar es capaz de eliminar un jugador en medio del árbol.
	 */
	@Test
	public void deleteMiddlePlayerTest() {
		setupScene2();
		game.sortPlayerNames(1);
		game.deletePlayerFromTree(game.searchPlayerName("camilo"));
		assertFalse( game.playerExists("camilo"), "El jugador debió haber sido eliminado." );
	}
	
}
