package threads;

import java.util.ArrayList;

import controller.GameViewController;
import model.Block;
import model.Game;
import model.Hero;

public class BlocksThread extends Thread {

	private GameViewController controller;
	private boolean running = false;
	private double posX;
	private double posY;
	private Hero hero;
	private Game game;
	private ArrayList<Block> blocks = new ArrayList<Block>();

	public BlocksThread(GameViewController controller, Hero hero, Game game) {

		this.controller = controller;
		posX = hero.getPosX();
		posY = hero.getPosY();
		this.hero = hero;
		this.game = game;
		blocks = game.getBlocks();

	}

	public void init() {

		running = true;

	}

	@Override
	public void run() {
	
		init();
		while (running) {
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < blocks.size(); i++) {
				Block current = blocks.get(i);
				if (!current.isOnPlatform(hero)) {
					hero.setFalling(true);
				}
				else {
					
					hero.setFalling(false);
					
				}
				
			}
			
		}
		
	}

	
}
