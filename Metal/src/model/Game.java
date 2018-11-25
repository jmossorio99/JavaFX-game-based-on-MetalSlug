package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private Player rootPlayer;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hero hero;
	private int listSorted;

	public Game() {
		rootPlayer = null;
		listSorted = 0;
	}

	public void addPlayerToTree(Player player) {
		if (rootPlayer == null) {
			rootPlayer = player;
		}
		else {
			rootPlayer.insertPlayer(player);
		}
	}

	public void deletePlayerFromTree(Player p) {
		if(rootPlayer != null) {
			 rootPlayer = rootPlayer.deletePlayer(p);
		}
	}
	
	public void addPlayerToArrayList(Player p) {
		players.add(p);
	}
	
	/**
	 * Ordenamiento por inserción.
	 * @param n Modo de ordenamiento, 1 para ascendente y -1 para descendente.
	 */
	public void sortPlayerNames(int n) {
		listSorted = n;
		for (int i = 1; i < players.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (players.get(j - 1).compareTo(players.get(j)) == n) {
					players.set(j - 1, players.set(j, players.get(j - 1)));
				}
			}
		}
	}
	
	/**
	 * Ordenamiento por selección.
	 * @param sort Modo de ordenamiento, true para ascendente y false para descendente.
	 */
	public void sortPlayerScores(boolean sort) {
		listSorted = 0;
		for( int i = 0; i < players.size() - 1; i++ ) {
			Player aux = players.get(i);
			int index = i;
			for( int j = i + 1; j < players.size(); j++ ) {
				if( (sort) ? players.get(j).getMaxScore() < aux.getMaxScore() : players.get(j).getMaxScore() > aux.getMaxScore() ) {
					aux = players.get(j);
					index = j;
				}
			}
			players.set(index, players.set(i, aux));
		}
	}
	
	/**
	 * Búsqueda binaria por nombre.
	 * @param name Nombre del jugador a buscar.
	 * @return Objeto Player si se encontró el jugador. Null si el jugador a buscar no existía.
	 */
	public Player searchPlayer( String name ) {
		int min = 0;
		int max = players.size() - 1;
		while( min <= max ) {
			int middle = ( min + max ) / 2;
			if( players.get(middle).getName().compareTo(name) == 0 ) 
				return players.get(middle);
			else if( players.get(middle).getName().compareTo(name) > 0 ) { 
				if( listSorted == 1 )
					max = middle - 1;
				else if( listSorted == -1 )
					min = middle + 1;
			}
			else {
				if( listSorted == 1 )
					min = middle + 1;
				else if( listSorted == -1 )
					max = middle - 1;
			}
		}
		return null;
	}

	public void deletePlayerFromArrayList(Player p) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) == p) {
				players.remove(i);
			}
		}
	}

	public ArrayList<Player> getPlayersList() {
		return players;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public void setSortedList( int s ) {
		listSorted = s;
	}
	
	public boolean isListSorted() {
		return listSorted == 1 || listSorted == -1;
	}
	
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
