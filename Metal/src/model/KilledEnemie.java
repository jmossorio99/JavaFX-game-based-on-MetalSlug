package model;

import java.io.Serializable;

/**
 * clase KilledEnemie
 */
public class KilledEnemie implements Serializable{
	
	private int killedEnemies;
	private KilledEnemie next;
	private KilledEnemie prev;
	
	/**
	 * constructor de KilledEnemie
	 * @param killed : numero de enemigos asesinados
	 */
	public KilledEnemie(int killed) {
		killedEnemies=killed;
	}

	/**
	 * este metodo da el numero de enemigos asesinados
	 * @return killedEnemies : numero de enemigos asesinados
	 */
	public int getKilledEnemies() {
		return killedEnemies;
	}

	/**
	 * este metodo modifica el valor de killedEnemies
	 * @param killedEnemies : el nuevo valor de killedEnemies
	 */
	public void setKilledEnemies(int killedEnemies) {
		this.killedEnemies = killedEnemies;
	}

	/**
	 * este metodo devuleve la variable next (siguiente)
	 * @return next : el KilledEnemie siguiente
	 */
	public KilledEnemie getNext() {
		return next;
	}

	/**
	 * este metodo modifica el valor de la variable next
	 * @param next : nuevo next
	 */
	public void setNext(KilledEnemie next) {
		this.next = next;
	}
/**
 * este metodo devulve el killedEnemie anterior (prev)
 * @return prev : el Killed Enemie anterior
 */
	public KilledEnemie getPrev() {
		return prev;
	}

	/**
	 * este metodo modifica el valor de prev (anterior)
	 * @param prev : nuevo prev
	 */
	public void setPrev(KilledEnemie prev) {
		this.prev = prev;
	} 
	
	/**
	 * este metodo compara este KilledEnemie con otro que le ingresa por parametro por sus puntajes de enemigos asesinados
	 * @param k : el Killed enemie a ser comparado 
	 * @return compare : -1 si el que ingreso es mayor,1 caso contrario o que sean iguales
	 */
	public int compareByKilledEnemies(KilledEnemie k) {
		int compare=0;
		
		if(killedEnemies<k.getKilledEnemies()) {
			compare=-1;
		}else {
			compare=+1;
		}
		return compare;	
	}
	

}
