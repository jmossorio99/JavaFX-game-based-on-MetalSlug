package threads;

import controller.GameViewController;
import model.Hero;

public class RunningAnimationThread extends Thread {

	private GameViewController controller;
	private Hero hero;
	private boolean running = false;
	
	public RunningAnimationThread(GameViewController controller, Hero hero) {
		
		this.controller = controller;
		this.hero = hero;
		
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
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (controller.getHeroMoving()) {
				
				if (controller.getHeroDirection() == hero.RIGHT) {
					
					for (int i = 0; i < 11 && controller.getHeroMoving(); i++) {
						
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getRunningRightImage(i));
						
						
					}
					
				} else if(controller.getHeroDirection() == hero.LEFT){

					for (int i = 0; i < 11 && controller.getHeroMoving(); i++) {
						
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.setHeroImage(controller.getRunningLeftImage(i));
						
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
