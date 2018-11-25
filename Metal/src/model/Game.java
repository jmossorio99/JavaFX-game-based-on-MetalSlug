package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private Player rootPlayer;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hero hero;
	private int sortedList;

	public Game() {
		rootPlayer = null;
		sortedList = 0;
	}

	public void addPlayerToTree(Player player) {
		if (rootPlayer == null) {

			rootPlayer = player;
		} else {

			rootPlayer.insertPlayer(player);
		}
	}

	public void deletePlayerFromTree(Player p) {

	}

	public void addPlayerToArrayList(Player p) {
		players.add(p);
	}
	
	/**
	 * Ordenamiento por inserci�n.
	 * @param n
	 */
	public void sortPlayerNames(int n) {
		sortedList = n;
		for (int i = 1; i < players.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (players.get(j - 1).compareTo(players.get(j)) == n) {
					players.set(j - 1, players.set(j, players.get(j - 1)));
				}
			}
		}
	}
	
	/**
	 * Ordenamiento por selecci�n.
	 * @param sort
	 */
	public void sortPlayerScores(boolean sort) {
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
	
	public Player searchPlayer( String name ) {
		int min = 0;
		int max = players.size() - 1;
		int middle = ( min + max ) / 2;
		boolean found = false;
		while( min <= max && !found  ) {
			middle = ( min + max ) / 2;
			if( players.get(middle).getName().compareTo(name) == 0 ) 
				return players.get(middle);
			else if( players.get(middle).getName().compareTo(name) > 0 ) { 
				if( sortedList == 1 )
					max = middle - 1;
				else if( sortedList == -1 )
					min = middle + 1;
			}
			else {
				if( sortedList == 1 )
					min = middle + 1;
				else if( sortedList == -1 )
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
	
	public boolean isListSorted() {
		return sortedList == 1 || sortedList == -1;
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
