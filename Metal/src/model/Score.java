package model;

import java.io.Serializable;

/**
 * Árbol binario de búsqueda con los puntajes que ha obtenido el jugador.
 *
 */
public class Score implements Serializable{
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Puntaje del nodo actual.
	 */
	private int score = 0;
	
	/**
	 * Raíz del subárbol derecho del nodo actual.
	 */
	private Score right;
	
	/**
	 * Raíz del subárbol izquierdo del nodo actual.
	 */
	private Score left;
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Método constructor.
	 * Crea un objeto de tipo Score.
	 * Pos: Se creó el objeto Score.
	 * @param score: Puntos obtenidos por el jugador.
	 */
	public Score(int score) {
		this.score=score;
	}
	
	/**
	 * Devuelve la puntuación que está almacenada en el nodo actual.
	 * @return score: Puntos obtenidos.
	 */
    public int getScore() {
		return score;
	}
    
    /**
     * Cambia la puntuación que almacena este nodo.
     * @param score: Nueva puntuación.
     */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Inserta de manera ordenada un nuevo nodo al árbol.
	 * Pre: El nodo actual está ordenado dentro del árbol.
	 * Pos: Se insertó el nuevo nodo.
	 * @param s: Nuevo objeto Score para ser insertado en el árbol.
	 */
	public void insertScore(Score s) {
		if(compareScore(s) > 0) {
			if(left == null) {
				left = s;
			}
			else {
				left.insertScore(s);
			}
		}
		else {
			if(right == null) {
				right = s;
			}
			else {
				right.insertScore(s);
			}
		}
	}
	
	/**
	 * Compara el puntaje del nodo actual con el de otro nodo.
	 * @param s: Objeto Score para comparar el puntaje.
	 * @return 	0 si los dos tienen el mismo puntaje.
	 * 			1 si el nodo actual tiene mayor puntaje que el que se pasa por parámetro.
	 * 		   -1 si el nodo actual tiene menor puntaje que el que se pasa por parámetro.
	 */
	public int compareScore(Score s) {
		int compare = 0;
		if(score > s.getScore()) {
			compare = 1;
		}else{
			compare = -1;
		}
		return compare;
	}
	
	/**
	 * Busca y devuelve el nodo con el mayor puntaje almacenado.
	 * Pre: El árbol está ordenado y debe haber un nodo con el puntaje máximo.
	 * Pos: Se encontró el nodo.
	 * @return nodo con mayor puntaje.
	 */
	public Score searchMaxScore() {
		return (right == null) ? this : right.searchMaxScore();
	}
	
	/**
	 * Indica si el nodo actual es una hoja.
	 * @return  true si no tiene subárbol izquierdo ni derecho.
	 * 			false si tiene al menos uno de ellos.
	 */
	public boolean sheet() {
		return (right == null) && (left == null);
	}
	
	/**
	 * Devuelve el peso del árbol, es decir, la cantidad de nodos que hay en él.
	 * Pre: Al menor hay un nodo en el árbol.
	 * @return cantidad de nodos que tiene el árbol.
	 */
	public int getWidth() {
		int w1 = ( left == null ) ? 0 : left.getWidth();
		int w2 = ( right == null ) ? 0 : right.getWidth();
		return 1 + w1 + w2;
	}
	
	/**
	 * Devuelve la raíz del subárbol izquierdo del nodo actual.
	 * @return left: Raíz del subárbol izquierdo.
	 */
	public Score getLeft() {
		return left;
	}
	
	/**
	 * Devuelve la raíz del subárbol derecho del nodo actual.
	 * @return right: Raíz del subárbol derecho.
	 */
	public Score getRight() {
		return right;
	}
}
