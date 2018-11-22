package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import threads.MusicThread;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
		Parent root = loader.load();
		Scene mainScene = new Scene(root);
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Metal Slug");
		primaryStage.show();

	}

	@Override
	public void stop() {

		System.exit(0);

	}

	public static void main(String[] args) {

		MusicThread musicThread = new MusicThread();
		musicThread.start();
		launch();

	}

}
