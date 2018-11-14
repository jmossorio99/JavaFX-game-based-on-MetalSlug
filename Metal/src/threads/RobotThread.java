package threads;

import java.util.ArrayList;

import controller.GameViewController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RobotThread extends Thread {

	private ArrayList<Image> images;
	private ImageView robot;
	private boolean running = false;

	public RobotThread(ArrayList<Image> images, Node robot) {

		this.robot = (ImageView) robot;
		this.images = images;

	}

	public void init() {

		running = true;

	}

	@Override
	public void run() {

		init();
		while (running) {
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 16; i++) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				robot.setImage(images.get(i));

			}
		}

	}

}
