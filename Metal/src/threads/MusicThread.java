package threads;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicThread extends Thread {

	private String[] routes = new String[7];

	private Media musicFile;
	private MediaPlayer player;

	public MusicThread() {
		routes[0] = "data/sound/music/1.mp3";
		routes[1] = "data/sound/music/2.mp3";
		routes[2] = "data/sound/music/3.mp3";
		routes[3] = "data/sound/music/4.mp3";
		routes[4] = "data/sound/music/5.mp3";
		routes[5] = "data/sound/music/6.mp3";
		routes[6] = "data/sound/music/7.mp3";
		int songNum = ThreadLocalRandom.current().nextInt(1, 8);
		String path = new File(routes[songNum-1]).getAbsolutePath();
		musicFile = new Media(new File(path).toURI().toString());
		player = new MediaPlayer(musicFile);
	}

	@Override
	public void run() {

		super.run();
		player.setVolume(0.1);
		player.setCycleCount(5);
		player.play();

	}

}
