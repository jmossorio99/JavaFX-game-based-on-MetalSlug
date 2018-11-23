package model;

import java.util.ArrayList;

public class Game {

	private Player rootPlayer = null;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Hero hero;

	private void addPlayerToTree(Player player) {

		if (rootPlayer == null) {
			rootPlayer = player;
		} else {
			player.insertPlayer(player);
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
