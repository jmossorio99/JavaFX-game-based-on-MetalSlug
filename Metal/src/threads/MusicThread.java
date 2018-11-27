package threads;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Clase MusicThread se encarga de reproducir una canción aleatoria de las 9 que
 * existen mientras el juego se ejecuta
 *
 */
public class MusicThread extends Thread {

	// atributos
	private String[] routes = new String[9];
	private Media musicFile;
	private MediaPlayer player;

	/**
	 * Constructor de la clase agrega al arreglo de canciones todas las canciones
	 * posibles para reproducir
	 */
	public MusicThread() {
		routes[0] = "data/sound/music/1.mp3";
		routes[1] = "data/sound/music/2.mp3";
		routes[2] = "data/sound/music/3.mp3";
		routes[3] = "data/sound/music/4.mp3";
		routes[4] = "data/sound/music/5.mp3";
		routes[5] = "data/sound/music/6.mp3";
		routes[6] = "data/sound/music/7.mp3";
		routes[7] = "data/sound/music/8.mp3";
		routes[8] = "data/sound/music/9.mp3";
		int songNum = ThreadLocalRandom.current().nextInt(1, 10);
		String path = new File(routes[songNum - 1]).getAbsolutePath();
		musicFile = new Media(new File(path).toURI().toString());
		player = new MediaPlayer(musicFile);
	}

	/**
	 * Metodo que se ejecuta al llamar el método start del hilo
	 */
	@Override
	public void run() {

		super.run();
		player.setVolume(0.1);
		player.setCycleCount(5);
		player.play();

	}

}
