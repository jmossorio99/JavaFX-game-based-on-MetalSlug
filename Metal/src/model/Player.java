package model;

import java.io.Serializable;
/**
 * clase Player
 */
public class Player implements Serializable, Comparable<Player> {

	private static final long serialVersionUID = -8063827302843788016L;

	private String name;
	private Player left = null;
	private Player right = null;
	private Score rootScore;
	private long timePlayed = 0;
	private TimeList firstTime;
	private KilledEnemie rootKilledEnemie;

	/**
	 * constructor de Player
	 * @param name: nombre del jugador
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * este metodo inserta un jugador
	 * @param p : el jugador a agregar
	 */
	public void insertPlayer(Player p) {
		if (p != null) {
			if (getMaxScore() > p.getMaxScore()) {
				if (left == null) {
					left = p;
				} else {
					left.insertPlayer(p);
				}
			} else {
				if (right == null) {
					right = p;
				} else {
					right.insertPlayer(p);
				}
			}
		}
	}

	/**
	 * este metodo devuelve el jugador left
	 * @return left : el jugador de la izquierda 
	 */
	public Player getLeft() {
		return left;
	}

	/**
	 * este metodo modifica el valor de left
	 * @param left : el nuevo valor de left (un Player)
	 */
	public void setLeft(Player left) {
		this.left = left;
	}

	/**
	 * este metodo devulve la variable right (un Player)
	 * @return right : la derecha 
	 */
	public Player getRight() {
		return right;
	}

	/**
	 * este metodo modifica la variable right
	 * @param right : el nuevo valor de right (un Player)
	 */
	public void setRight(Player right) {
		this.right = right;
	}

	/**
	 * este metodo devuelve el nombre del jugador
	 * @return name : nombre del jugador
	 */
	public String getName() {
		return name;
	}

	/**
	 * este metodo modifica el valor de name
	 * @param name : el nuevo nombre del jugador
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * este metodo adiciona un score a el arbol de scores  
	 * @param score : el nuevo score
	 */
	public void addScore(int score) {
		Score s = new Score(score);
		if (rootScore == null) {
			rootScore = s;
		} else {
			rootScore.insertScore(s);
		}
	}

	/**
	 * este metodo devulelve el score mas alto del arbol de scores
	 * @return maxScore : el score mas alto alcanzado
	 */
	public int getMaxScore() {
		int maxScore = 0;
		if (rootScore != null) {
			Score max = rootScore.searchMaxScore();
			maxScore = max.getScore();
		}
		return maxScore;
	}

	@Override
	public String toString() {
		String str = "";
		if (timePlayed < 10)
			str = String.format("%s%40s%40ss%40ss", name, getMaxScore(), getTimePlayed(), getHighestTime());
		else if (timePlayed < 100)
			str = String.format("%s%40s%40ss%40ss", name, getMaxScore(), getTimePlayed(), getHighestTime());
		else if (timePlayed >= 100)
			str = String.format("%s%40s%40ss%40ss", name, getMaxScore(), getTimePlayed(), getHighestTime());
		return str;
	}

	@Override
	public int compareTo(Player p) {
		if (name.compareTo(p.getName()) > 0)
			return 1;
		else if (name.compareTo(p.getName()) < 0)
			return -1;
		else
			return 0;
	}

	/**
	 * este metodo revisa si un jugador existe por su nombre
	 * @param name2 : el nombre por el que va a ser buscado
	 * @return boolean, true si existe false si no
	 */
	public boolean playerExists(String name2) {
		boolean a1 = false;
		boolean a2 = false;
		if (name.equalsIgnoreCase(name2)) {
			return true;
		}
		if (left != null) {
			a1 = left.playerExists(name2);
		}
		if (right != null) {
			a2 = right.playerExists(name2);
		}
		return a1 || a2;
	}

	/**
	 * este metodo revisa si soy una hoja, left y right son null
	 * @return boolean, true si lo soy false si no
	 */
	public boolean sheet() {
		return (left == null) && (right == null);
	}

	/**
	 * este metodo devuelve el menor player a la izquierda
	 * @return Player : el menor a la izquierda
	 */
	public Player getLess() {
		return (left == null) ? this : left.getLess();
	}

	/**
	 * este metodo auxiliar borra a un jugador del arbol
	 * @param p : el jugador que va a ser eliminado
	 * @return Player : nueva raiz de donde es llamado
	 */
	public Player deletePlayer(Player p) {
		if (sheet()) {
			return null;
		}
		if (getMaxScore() == p.getMaxScore()) {

			if (left == null) {
				return right;
			}
			if (right == null) {
				return left;
			}

			Player inheritor = right.getLess();
			right = right.deletePlayer(inheritor);

			inheritor.left = left;
			inheritor.right = right;
			return inheritor;
		} else if (getMaxScore() > p.getMaxScore()) {
			left = left.deletePlayer(p);
		} else {
			right = right.deletePlayer(p);
		}
		return this;
	}

	/**
	 * este metodo da el tiempo jugado
	 * @return timePlayed : el tiempo jugado
	 */
	public long getTimePlayed() {
		return timePlayed;
	}

	/**
	 * este metodo modifica la variable timePlayed
	 * @param timePlayed : el nuevo tiempo jugado
	 */
	public void setTimePlayed(long timePlayed) {
		this.timePlayed = timePlayed;
	}

	/**
	 * este metodo adiciona un tiempo a la lista
	 * @param time : el nuevo tiempo
	 */
	public void addTimeList(TimeList time) {

		boolean stop = false;
		if (firstTime == null) {
			firstTime = time;
		} else if (firstTime.compareTo(time) < 0) {
			time.setNext(firstTime);
			firstTime.setPrev(time);
			firstTime = time;
		} else {
			TimeList temp = firstTime;
			while (temp.getNext() != null && stop) {
				if (temp.compareTo(time) <= 0) {
					TimeList prev = temp.getPrev();
					prev.setNext(time);
					time.setNext(temp);
					stop = true;
				}
				temp = temp.getNext();
			}
			if (!stop) {
				temp.setNext(time);
				time.setPrev(temp);
			}
		}

	}

	/**
	 * este metodo da el mayor tiempo que se ha sobrevivido el jugador
	 * @return ret : el mayor tiempo sobrevivido
	 */
	public long getHighestTime() {

		long ret = firstTime.getData();
		TimeList temp = firstTime;
		while (temp != null) {
			if (temp.getData() > ret) {
				ret = temp.getData();
			}
			temp = temp.getNext();
		}
		return ret;

	}
	
	/**
	 * este metodo adiciona el numero de enemigos asesinados a la lista de enemigos asesinados
	 * @param k : el valor KilledEnemie a adicionar 
	 */
	public void addToKilledEnemiesList(KilledEnemie k) {
		
		if(rootKilledEnemie==null) {
			rootKilledEnemie=k;
		}
		else {
			if(rootKilledEnemie.compareByKilledEnemies(k)<0) {
				k.setNext(rootKilledEnemie);
				rootKilledEnemie.setPrev(k);
				rootKilledEnemie=k;
			}
			else {
				addToKilledEnemiesListR(rootKilledEnemie,k);
			}
		}
		
	}
	
	/**
	 * metodo auxiliar de addToKilledEnemiesList
	 * @param aux : el KilledEnemie donde estoy 
	 * @param newKilled : el KilledEnemir a adicionar
	 */
	public void addToKilledEnemiesListR(KilledEnemie aux,KilledEnemie newKilled) {
		
		if(aux!=null) {
			
			if(aux.getNext()!=null) {
				
				
				if(aux.compareByKilledEnemies(newKilled)<0 && aux.getNext().compareByKilledEnemies(newKilled)>0) {
					insertAfterAKilledEnemie(aux,newKilled);
				}
				else {
					addToKilledEnemiesListR(aux.getNext(),newKilled);
				}
				
				
			}else {
				insertAfterAKilledEnemie(aux,newKilled);
			}
			
			
			
			
		}
		
	}
	
	/**
	 * este metodo inserta un KilledEnemie despues de uno ya especificado
	 * @param aux : el anterior  donde va a ser addicionado
	 * @param newKilled : el que se va a adicionar
	 */
	public void insertAfterAKilledEnemie(KilledEnemie aux,KilledEnemie newKilled) {
		
		if(aux.getNext()!=null) {
			
			newKilled.setNext(aux.getNext());
			aux.getNext().setPrev(newKilled);
			newKilled.setPrev(aux);
			aux.setNext(newKilled);
			
		}else {
			aux.setNext(newKilled);
			newKilled.setPrev(aux);
		}
		
	}
	
	/**
	 * este metodo devulve un int con el mayor valor de enemigos asesinados de un jugador en su historial
	 * @return maxKilled : el mayor numero de enemigos asesinados
	 */
	public int getMaxKilledEnemies() {
		int maxKilled=0;
		if(rootKilledEnemie!=null) {
			maxKilled=rootKilledEnemie.getKilledEnemies();
		}
		
		return maxKilled;
	 }
	

}
