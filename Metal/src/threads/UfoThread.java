package threads;

import model.Ufo;

public class UfoThread extends Thread{

	
	public Ufo ufo;
	
	public UfoThread(Ufo ufo) {
		super();
		this.ufo=ufo;
	}
	
	public void run() {
		
		while(true) {
		
			ufo.moveUfo();
		
			try {
				this.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
}
