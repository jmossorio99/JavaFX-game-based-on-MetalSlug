package threads;

import model.Tank;

public class TankThread extends Thread{

	public Tank tank;
	
	public TankThread(Tank tank) {
		this.tank=tank;
	}
	
	public void run() {
		while(true) {
			
			tank.changeTankImages();
			
			try {
				this.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
