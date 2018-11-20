package model;

import java.util.ArrayList;

public class Game {

	private Player firstPlayer = null;
	private Hero hero;
	private ArrayList<Block> blocks = new ArrayList<>();

	public Game(Hero hero) {

		this.hero = hero;

	}
	
	public void addBlocks(Block block) {
		
		blocks.add(block);
		
	}
	
	public ArrayList<Block> getBlocks(){
		
		return blocks;
		
	}

}
