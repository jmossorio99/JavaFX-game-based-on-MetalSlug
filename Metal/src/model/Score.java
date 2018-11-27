package model;

import java.io.Serializable;

/**
 * �rbol binario de b�squeda con los puntajes que ha obtenido el jugador.
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
	 * Ra�z del sub�rbol derecho del nodo actual.
	 */
	private Score right;
	
	/**
	 * Ra�z del sub�rbol izquierdo del nodo actual.
	 */
	private Score left;
	
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	/**
	 * M�todo constructor.
	 * Crea un objeto de tipo Score.
	 * Pos: Se cre� el objeto Score.
	 * @param score: Puntos obtenidos por el jugador.
	 */
	public Score(int score) {
		this.score=score;
	}
	
	/**
	 * Devuelve la puntuaci�n que est� almacenada en el nodo actual.
	 * @return score: Puntos obtenidos.
	 */
    public int getScore() {
		return score;
	}
    
    /**
     * Cambia la puntuaci�n que almacena este nodo.
     * @param score: Nueva puntuaci�n.
     */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Inserta de manera ordenada un nuevo nodo al �rbol.
	 * Pre: El nodo actual est� ordenado dentro del �rbol.
	 * Pos: Se insert� el nuevo nodo.
	 * @param s: Nuevo objeto Score para ser insertado en el �rbol.
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
	 * 			1 si el nodo actual tiene mayor puntaje que el que se pasa por par�metro.
	 * 		   -1 si el nodo actual tiene menor puntaje que el que se pasa por par�metro.
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
	 * Pre: El �rbol est� ordenado y debe haber un nodo con el puntaje m�ximo.
	 * Pos: Se encontr� el nodo.
	 * @return nodo con mayor puntaje.
	 */
	public Score searchMaxScore() {
		return (right == null) ? this : right.searchMaxScore();
	}
	
	/**
	 * Indica si el nodo actual es una hoja.
	 * @return  true si no tiene sub�rbol izquierdo ni derecho.
	 * 			false si tiene al menos uno de ellos.
	 */
	public boolean sheet() {
		return (right == null) && (left == null);
	}
	
	/**
	 * Devuelve el peso del �rbol, es decir, la cantidad de nodos que hay en �l.
	 * Pre: Al menor hay un nodo en el �rbol.
	 * @return cantidad de nodos que tiene el �rbol.
	 */
	public int getWidth() {
		int w1 = ( left == null ) ? 0 : left.getWidth();
		int w2 = ( right == null ) ? 0 : right.getWidth();
		return 1 + w1 + w2;
	}
	
	/**
	 * Devuelve la ra�z del sub�rbol izquierdo del nodo actual.
	 * @return left: Ra�z del sub�rbol izquierdo.
	 */
	public Score getLeft() {
		return left;
	}
	
	/**
	 * Devuelve la ra�z del sub�rbol derecho del nodo actual.
	 * @return right: Ra�z del sub�rbol derecho.
	 */
	public Score getRight() {
		return right;
	}
}
