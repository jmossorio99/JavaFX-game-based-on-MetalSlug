package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Player rootPlayer;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hero hero;

	public Game() {
		rootPlayer=null;
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

	public void deletePlayerFromArrayList(Player p) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) == p) {
				players.remove(i);
			}
		}
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

}
