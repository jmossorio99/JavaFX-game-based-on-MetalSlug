package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * clase Game
 */
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private Player rootPlayer;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hero hero;
	private int listSortedNames;
	private boolean listSortedScores;
	
/**
 *contructor de Game
 */
	public Game() {
		rootPlayer = null;
		listSortedNames = 0;
	}

	/**
	 * este metodo adiciona un Player al arbol, si las raiz es nula lo adiciona ahi si la raiz no es nula llama a insertPlayer de la clase jugador
	 * @param player : el juegador a adicionar en el arbol
	 */
	public void addPlayerToTree(Player player) {
		if (rootPlayer == null) {
			rootPlayer = player;
		} else {
			rootPlayer.insertPlayer(player);
		}
	}

	/**
	 * este metodo borra a un jugador del arbol el cual ya este en el
	 * @param p : el jugador a borrar 
	 */
	public void deletePlayerFromTree(Player p) {
		if (rootPlayer != null) {
			rootPlayer = rootPlayer.deletePlayer(p);
		}
	}

	/**
	 * este metodo adiciona un jugador al arrayList de jugadores
	 * @param p : el jugador a addicionar
	 */
	public void addPlayerToArrayList(Player p) {
		players.add(p);
	}

	/**
	 * este metodo ordena por inserción la lista de players.
	 * @param n: Modo de ordenamiento, 1 para ascendente y -1 para descendente.
	 */
	public void sortPlayerNames(int n) {
		listSortedNames = n;
		listSortedScores = false;
		for (int i = 1; i < players.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (players.get(j - 1).compareTo(players.get(j)) == n) {
					players.set(j - 1, players.set(j, players.get(j - 1)));
				}
			}
		}
	}

	/**
	 * este metodo ordena por selección la lista de jugadores.
	 * @param sort: Modo de ordenamiento, true para ascendente y false para
	 *        descendente.
	 */
	public void sortPlayerScores(boolean sort) {
		listSortedNames = 0;
		listSortedScores = true;
		for (int i = 0; i < players.size() - 1; i++) {
			Player aux = players.get(i);
			int index = i;
			for (int j = i + 1; j < players.size(); j++) {
				if ((sort) ? players.get(j).getMaxScore() < aux.getMaxScore()
						: players.get(j).getMaxScore() > aux.getMaxScore()) {
					aux = players.get(j);
					index = j;
				}
			}
			players.set(index, players.set(i, aux));
		}
	}

	/**
	 * este metodo ordena por intercambio (burbuja) la lista de players.
	 * 
	 * @param sor: Modo de ordenamiento, true para ascendente y false para
	 *        descendente.
	 */
	public void sortPlayerTimes(boolean sort) {
		listSortedNames = 0;
		listSortedScores = false;
		for (int i = 0; i < players.size() - 1; i++) {
			for (int j = 0; j < players.size() - 1; j++) {
				if ((sort) ? players.get(j).getTimePlayed() > players.get(j + 1).getTimePlayed()
						: players.get(j).getTimePlayed() < players.get(j + 1).getTimePlayed()) {
					players.set(j, players.set(j + 1, players.get(j)));
				}
			}
		}
	}

	/**
	 * este metodo busca un jugador por su nombre usando busqueda binaria 
	 * @param name Nombre del jugador a buscar.
	 * @return Objeto Player si se encontró el jugador. Null si el jugador a buscar
	 *         no existía.
	 */
	public Player searchPlayerName(String name) {
		int min = 0;
		int max = players.size() - 1;
		while (min <= max) {
			int middle = (min + max) / 2;
			if (players.get(middle).getName().compareToIgnoreCase(name) == 0)
				return players.get(middle);
			else if (players.get(middle).getName().compareToIgnoreCase(name) > 0) {
				if (listSortedNames == 1)
					max = middle - 1;
				else if (listSortedNames == -1)
					min = middle + 1;
			} else {
				if (listSortedNames == 1)
					min = middle + 1;
				else if (listSortedNames == -1)
					max = middle - 1;
			}
		}
		return null;
	}

	/**
	 * este metodo busca a un jugador por su puntaje usando búsqueda binaria.
	 * 
	 * @param score Puntaje para buscar al/los jugadores a buscar.
	 * @return ArrayList de objetos Player si se encontrarón jugadores con ese
	 *         puntaje. ArrayList vacío si ningún jugador posee ese puntaje.
	 */
	public ArrayList<Player> searchPlayerScore(int score) {
		ArrayList<Player> list = new ArrayList<Player>();
		int min = 0;
		int max = players.size() - 1;
		while (min <= max) {
			int middle = (min + max) / 2;
			if (players.get(middle).getMaxScore() == score) {
				list.add(players.get(middle));
				for (int i = middle - 1; i >= 0 && players.get(i).getMaxScore() == score; i--) {
					list.add(players.get(i));
				}
				for (int i = middle + 1; i < players.size() && players.get(i).getMaxScore() == score; i++) {
					list.add(players.get(i));
				}
				return list;
			} else if (players.get(middle).getMaxScore() > score) {
				if (listSortedScores)
					max = middle - 1;
				else if (!listSortedScores)
					min = middle + 1;
			} else {
				if (listSortedScores)
					min = middle + 1;
				else if (!listSortedScores)
					max = middle - 1;
			}
		}
		return list;
	}

	/**
	 * este metodo borra a un jugador del arrayList de jugadores
	 * @param p : el jugador a eliminar
	 */
	public void deletePlayerFromArrayList(Player p) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) == p) {
				players.remove(i);
			}
		}
	}

	/**
	 * este metodo devulve a la lista de jugadores
	 * @return players : la lista de jugadores
	 */
	public ArrayList<Player> getPlayersList() {
		return players;
	}

	/**
	 * este metodo retorna el heroe
	 * @return hero : el heroe
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * este metodo cambia el atributo hero
	 * @param hero : el nuevo hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}
//------------------------------------------ wtf no es lo mismo?----------------------
	/**
	 * este metodo modifica el atributo listSortedNames
	 * @param s : el nuevo valor
	 */
	public void setSortedListByNames(int s) {
		listSortedNames = s;
	}

	/**
	 * este metodo modifica el atributo listSortedNames
	 * @param s : el nuevo valor
	 */
	public void setSortedListByScores(int s) {
		listSortedNames = s;
	}


	/**
	 * 
	 * @return
	 */
	public boolean isListSortedByNames() {
		return listSortedNames == 1 || listSortedNames == -1;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isListSortedByScores() {
		return listSortedScores;
	}
	
//-------------------------------------------------------------------------------------

	/**
	 * este metodo retorna un boolean true si el jugador existe en el arbol de jugadores
	 * @param name : el nombre por el que va a ser buscado
	 * @return boolean : true:existe , false: no existe
	 */
	public boolean playerExists(String name) {
		if (rootPlayer != null) {
			if (rootPlayer.getName().equalsIgnoreCase(name)) {
				return true;
			} else {
				return rootPlayer.playerExists(name);
			}
		} else {
			return false;
		}
	}
	
}
