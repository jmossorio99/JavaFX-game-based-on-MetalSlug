package tests;

import static org.junit.jupiter.api.Assertions.*;

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
	}
	
	/**
	 * Ocho elementos
	 */
	public void setupScene2() {
		setupScene1();
		game.addPlayerToArrayList( new Player( "L" ) );
		game.addPlayerToArrayList( new Player( "Victor" ) );
		game.addPlayerToArrayList( new Player( "555" ) );
		game.addPlayerToArrayList( new Player( "camilo" ) );
		game.addPlayerToArrayList( new Player( "Jose" ) );
		game.addPlayerToArrayList( new Player( "goku" ) );
		game.addPlayerToArrayList( new Player( "Zzz" ) );
		game.addPlayerToArrayList( new Player( "Freddie" ) );
		game.addPlayerToArrayList( new Player( "123" ) );
		game.addPlayerToArrayList( new Player( "david" ) );
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
		found = game.searchPlayer( "Jose" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Jose" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		found = game.searchPlayer( "goku" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "goku" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayer( "Jose" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "Jose" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		found = game.searchPlayer( "goku" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( found.getName().equals( "goku" ), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
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
		found = game.searchPlayer( "123" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get(0).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayer( "goku" );
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
		found = game.searchPlayer( "goku" );
		assertNotNull( found, "No se encontro el jugador." );
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).equals(found), "El jugador encontrado no tiene el mismo nombre del que se intentó buscar." );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayer( "123" );
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
		Player found = game.searchPlayer( "NoPlayer" );
		assertNull( found, "Se encontró un jugador cuando debería devolver null." );
	}
	
}
