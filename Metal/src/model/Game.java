package model;

import java.util.ArrayList;

public class Game {

	private Player firstPlayer = null;
	private Hero hero;
	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Robot> robots = new ArrayList<>();

	public Game(Hero hero) {

		this.hero = hero;

	}

	public void addBlocks(Block block) {

		blocks.add(block);

	}

	public ArrayList<Block> getBlocks() {

		return blocks;

	}
	
	public void addRobot(Robot r) {
		robots.add(r);
	}
	
	public Robot getRobot(int i) {
		return robots.get(i);
	}
	
	public void removeRobot(int i) {
		robots.remove(i);
	}

}
