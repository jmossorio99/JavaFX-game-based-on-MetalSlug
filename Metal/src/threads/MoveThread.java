package threads;

import controller.GameViewController;

public class MoveThread extends Thread {

	private GameViewController controller;
	
	public MoveThread(GameViewController controller) {
		
		this.controller = controller;
		
	}
	
	@Override
	public void run() {
		
		while (controller.getHeroMoving()) {
			
			
			
		}
		
	}
	
}
