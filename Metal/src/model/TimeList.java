package model;

import java.io.Serializable;

/**
 * Nodo de una lista doblemente enlazada que representa el tiempo que duro el jugador durante la partida.
 *
 */
public class TimeList implements Comparable<TimeList>, Serializable {
	
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Nodo que le sigue al actual.
	 */
	private TimeList next;
	
	/**
	 * Nodo que anterior al actual.
	 */
	private TimeList prev;
	
	/**
	 * Tiempo en segundos.
	 */
	private long data;
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Constructor de la clase.
	 * Pos: Se creó un objeto TimeList.
	 * @param playedTime: Tiempo que duró el jugador en la partida en segundos.
	 */
	public TimeList(long playedTime) {
		this.data = playedTime;
		next = null;
		prev = null;
	}

	/**
	 * Devuelve el siguiente nodo de la lista.
	 * @return next: Nodo que le sigue al actual.
	 */
	public TimeList getNext() {
		return next;
	}
	
	/**
	 * Cambia el nodo siguiente que tiene el nodo actual.
	 * @param next: Nuevo nodo siguiente.
	 */
	public void setNext(TimeList next) {
		this.next = next;
	}

	/**
	 * Devuelve el nodo anterior a este.
	 * @return prev: Nodo anterior.
	 */
	public TimeList getPrev() {
		return prev;
	}
	
	/**
	 * Cambia el nodo anterior que tiene el nodo actual.
	 * @param prev: Nuevo nodo anterior.
	 */
	public void setPrev(TimeList prev) {
		this.prev = prev;
	}

	/**
	 * Devuelve el tiempo de juego del jugador en segundos.
	 * @return data: Tiempo de juego.
	 */
	public long getData() {
		return data;
	}

	/**
	 * Cambia el tiempo de juego.
	 * @param data: Nuevo tiempo.
	 */
	public void setData(int data) {
		this.data = data;
	}
	
	/**
	 * Método sobreescrito de la interfaz Comparable.
	 * Compara el tiempo de juego del nodo actual con el tiempo de juego de otro nodo que se pasa por parámetro.
	 * @param tl - nodo Otro nodo de la lista doblemente enlazada.
	 * @return  0 si los tiempos son iguales.
	 * 			1 si el tiempo del nodo actual es mayor que el del nodo pasado por parámetro.
	 * 		   -1 si el tiempo del nodo actual es menor que el del nodo pasado por parámetro.
	 */
	public int compareTo(TimeList tl) {
		if (data == tl.getData()) {
			return 0;
		} else if (data > tl.getData()) {
			return 1;
		} else {
			return -1;
		}
	}

}
