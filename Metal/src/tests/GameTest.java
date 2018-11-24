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
		game.addPlayerToArrayList( new Player( "Camilo" ) );
	}
	
	/**
	 * Seis elementos
	 */
	public void setupScene2() {
		setupScene1();
		game.addPlayerToArrayList( new Player( "Jose" ) );
		game.addPlayerToArrayList( new Player( "David" ) );
		game.addPlayerToArrayList( new Player( "Victor" ) );
		game.addPlayerToArrayList( new Player( "Freddie" ) );
		game.addPlayerToArrayList( new Player( "L" ) );
		game.addPlayerToArrayList( new Player( "Gok�" ) );
	}
	
	/**
	 * Prueba que el m�todo para ordenar por nombre funciona si solo hay un jugador.
	 */
	@Test
	public void sortOneNameTest() {
		setupScene1();
		
		// Ascendente
		game.sortPlayerNames( 1 );
		assertTrue( game.getPlayersList().get(0).getName().equals( "Camilo" ), "El nombre no es el mismo" );
		
		// Descendente
		game.sortPlayerNames( -1 );
		assertTrue( game.getPlayersList().get(0).getName().equals( "Camilo" ), "El nombre no es el mismo" );
		
	}
	
	/**
	 * Prueba que el m�todo para ordenar por nombre funciona si hay varios jugadores.
	 */
	@Test
	public void sortSeveralNamesTest() {
		setupScene2();
		
		// Ascendente
		game.sortPlayerNames( 1 );
		for( int i = 1; i < game.getPlayersList().size() - 1; i++ ) {
			if( game.getPlayersList().get(i - 1).compareTo( game.getPlayersList().get(i) ) > 0 ) {
				fail( "Hay un elemento que no est� ordenado." );
			}
		}
		assertTrue( game.getPlayersList().get(0).getName().equals("Camilo") ); // Primer elemento despu�s de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("Victor") ); // �ltimo elemento despu�s de ordenar
		
		// Descendente
		game.sortPlayerNames( -1 );
		for( int i = 1; i < game.getPlayersList().size() - 1; i++ ) {
			if( game.getPlayersList().get(i - 1).compareTo( game.getPlayersList().get(i) ) < 0 ) {
				fail( "Hay un elemento que no est� ordenado." );
			}
		}
		assertTrue( game.getPlayersList().get(0).getName().equals("Victor") ); // Primer elemento despu�s de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("Camilo") ); // �ltimo elemento despu�s de ordenar
	}
	
	/**
	 * Prueba el m�todo de b�squeda binaria para un jugador en el medio de la lista.
	 */
	@Test
	public void searchMiddlePlayerTest() {
		setupScene2();
		Player found = null;
		
		// Orden ascendente
		game.sortPlayerNames( 1 );
		found = game.searchPlayer( "Gok�" );
		assertNotNull( found );
		assertTrue( found.getName().equals( "Gok�" ) );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayer( "Freddie" );
		assertNotNull( found );
		assertTrue( found.getName().equals( "Freddie" ) );
	}
	
	/**
	 * Prueba el m�todo de b�squeda binaria para el primer jugador de la lista.
	 */
	@Test
	public void searchFirstPlayerTest() {
		setupScene2();
		Player found = null;
		
		// Orden ascendente
		game.sortPlayerNames( 1 );
		found = game.searchPlayer( "Camilo" );
		assertNotNull( found );
		assertTrue( found.getName().equals( "Camilo" ) );
		
		// Orden descendente
		game.sortPlayerNames( -1 );
		found = game.searchPlayer( "Victor" );
		assertNotNull( found );
		assertTrue( found.getName().equals( "Victor" ) );		
	}
	
	/**
	 * Prueba el m�todo de b�squeda binaria para un jugador que no est� en la lista.
	 */
	@Test
	public void searchNonExistentPlayerTest() {
		setupScene2();
		
		game.sortPlayerNames( 1 );
		Player found = game.searchPlayer( "Player" );
		assertNull( found );
	}
	
}
