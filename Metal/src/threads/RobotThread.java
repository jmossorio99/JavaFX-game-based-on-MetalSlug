package threads;

import java.util.ArrayList;

import controller.GameViewController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RobotThread extends Thread {

	private ArrayList<Image> images;
	private ArrayList<Node> robots;
	private ImageView robot;
	private boolean running = false;
	private GameViewController controller;

	public RobotThread(ArrayList<Image> images, ArrayList<Node> robots, GameViewController controller) {

		this.robots = robots;
		this.images = images;
		this.controller = controller;

	}

	public void init() {

		running = true;

	}

	@Override
	public void run() {

		init();
		while (running && !controller.getHeroIsDead()) {
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
				for (int j = 0; j < robots.size(); j++) {
					ImageView r = (ImageView) robots.get(j);
					r.setImage(images.get(i));
				}
				

			}
		}

	}

}
