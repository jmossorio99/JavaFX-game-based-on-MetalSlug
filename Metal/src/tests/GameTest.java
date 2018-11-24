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
		game.addPlayerToArrayList( new Player( "Victor") );
		game.addPlayerToArrayList( new Player( "Freddie") );
		game.addPlayerToArrayList( new Player( "L") );
		game.addPlayerToArrayList( new Player( "Gokú") );
	}
	
	/**
	 * Prueba que el método para ordenar por nombre funciona si solo hay un jugador.
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
		assertTrue( game.getPlayersList().get(0).getName().equals("Camilo") ); // Primer elemento después de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("Victor") ); // Último elemento después de ordenar
		
		// Descendente
		game.sortPlayerNames( -1 );
		for( int i = 1; i < game.getPlayersList().size() - 1; i++ ) {
			if( game.getPlayersList().get(i - 1).compareTo( game.getPlayersList().get(i) ) < 0 ) {
				fail( "Hay un elemento que no está ordenado." );
			}
		}
		assertTrue( game.getPlayersList().get(0).getName().equals("Victor") ); // Primer elemento después de ordenar
		assertTrue( game.getPlayersList().get( game.getPlayersList().size() - 1 ).getName().equals("Camilo") ); // Último elemento después de ordenar
	}

}
