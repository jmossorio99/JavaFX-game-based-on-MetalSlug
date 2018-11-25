package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import model.Score;

class ScoreTest extends TestCase {
	
	private Score score;
	
	/**
	 * Escenario en donde se crea el arbol.
	 */
	public void setupScene1() {
		score = new Score(100);
	}
	
	/**
	 * Escenario en donde se agrega un puntaje a la izquierda de la raiz.
	 */
	public void setupScene2() {
		setupScene1();
		score.insertScore( new Score(50) );
	}
	
	/**
	 * Escenario en donde se agrega un puntaje a la derecha de la raiz.
	 */
	public void setupScene3() {
		setupScene2();
		score.insertScore( new Score(120) );
	}
	
	/**
	 * Prueba que el método crea la raiz correctamente.
	 */
	@Test
	public void createTreeTest() {
		setupScene1();
		assertEquals("El peso del arbol debe ser 1.", 1, score.getWidth());
	}
	
	/**
	 * Prueba el escenario 2, el cual agrega un puntaje a la izquierda de la raiz.
	 */
	@Test
	public void insertLeftTest() {
		setupScene2();
		assertEquals("El peso del arbol debe ser 2.", 2, score.getWidth() );
		assertNotNull( "El puntaje no se agregó a la izquierda.", score.getLeft() );
		assertTrue( "El puntaje a la izquierda no es el esperado.", score.getLeft().getScore() == 50);
	}
	
	/**
	 * Prueba el escenario 3, el cual agrega un puntaje a la derecha de la raiz.
	 */
	@Test
	public void insertRightTest() {
		setupScene3();
		assertEquals("El peso del arbol debe ser 3.", 3, score.getWidth());
		assertNotNull( "El puntaje no se agregó a la derecha.", score.getRight() );
		assertTrue( "El puntaje a la derecha no es el esperado.", score.getRight().getScore() == 120);
	}
	
	/**
	 * Prueba que el método searchMaxScore() devuelve el máximo puntaje correcto.
	 */
	@Test
	public void searchMaxScoreTest() {
		setupScene3();
		assertEquals("El puntaje máximo debió ser 120.", 120, score.searchMaxScore().getScore());
	}
}
