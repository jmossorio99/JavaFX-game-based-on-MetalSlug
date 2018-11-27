package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;
import exceptions.PlayerNameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import threads.MusicThread;

public class MainWindowController implements Initializable {

	@FXML
	private TextField nickNameTextField;
	@FXML
	private Button loadGameButton;
	@FXML
	private Button playButton;
	@FXML
	private Button scoresButton;
	private Game game;

	@FXML
	void playClicked(ActionEvent event) {
		Player p;
		try {
			if (nickNameTextField.getText().isEmpty())
				throw new PlayerNameException("Ingrese un nombre para el jugador.");
			if (nickNameTextField.getText().length() < 3)
				throw new PlayerNameException("Ingrese un nombre de tres caracteres o más.");
			if (playerExists(nickNameTextField.getText())) {
				game.sortPlayerNames(1);
				p = game.searchPlayerName(nickNameTextField.getText());
			} else {
				p = new Player(nickNameTextField.getText());
			}
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/GameView.fxml"));
			Parent gameView;
			try {
				gameView = loader.load();
				Scene gameScene = new Scene(gameView);
				GameViewController controller = loader.getController();
				controller.setGame(gameScene, game, p);
				Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
				window.setScene(gameScene);
				window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (PlayerNameException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void scoresClicked(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ScoresWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ScoresWindowController controller = loader.getController();
		controller.setScoresWindow(game);
		Scene scene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void loadGameClicked(ActionEvent event) {

		File file = new File("gameData.txt");
		File file2 = new File("enemieBullets.txt");
		File file3 = new File("robots.txt");
		File file4 = new File("heroBulletsRight.txt");
		File file5 = new File("heroBulletsLeft.txt");
		File file6 = new File("ufoBullets.txt");
		if (file.exists() && file2.exists() && file3.exists() && file4.exists() && file5.exists() && file6.exists()) {
			try {
				FileReader fr = new FileReader(file);
				ArrayList<String> arr1 = new ArrayList<String>();
				FileReader fr2 = new FileReader(file2);
				ArrayList<Double> arr2 = new ArrayList<Double>();
				FileReader fr3 = new FileReader(file3);
				ArrayList<Double> arr3 = new ArrayList<Double>();
				FileReader fr4 = new FileReader(file4);
				ArrayList<Double> arr4 = new ArrayList<Double>();
				FileReader fr5 = new FileReader(file5);
				ArrayList<Double> arr5 = new ArrayList<Double>();
				FileReader fr6 = new FileReader(file6);
				ArrayList<Double> arr6 = new ArrayList<Double>();

				Scanner sc1 = new Scanner(fr);
				while (sc1.hasNext()) {
					arr1.add(sc1.nextLine());
				}
				Scanner sc2 = new Scanner(fr2);
				while (sc2.hasNext()) {
					arr2.add(Double.parseDouble(sc2.nextLine()));
				}
				Scanner sc3 = new Scanner(fr3);
				while (sc3.hasNext()) {
					arr3.add(Double.parseDouble(sc3.nextLine()));
				}
				Scanner sc4 = new Scanner(fr4);
				while (sc3.hasNext()) {
					arr4.add(Double.parseDouble(sc4.nextLine()));
				}
				Scanner sc5 = new Scanner(fr5);
				while (sc3.hasNext()) {
					arr5.add(Double.parseDouble(sc5.nextLine()));
				}
				Scanner sc6 = new Scanner(fr6);
				while (sc6.hasNext()) {
					arr6.add(Double.parseDouble(sc6.nextLine()));
				}

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/GameView.fxml"));
				Parent gameView;
				try {
					gameView = loader.load();
					Scene gameScene = new Scene(gameView);
					GameViewController controller = loader.getController();
					controller.loadGame(gameScene, game, arr1, arr2, arr3, arr4, arr5, arr6);
					Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
					window.setScene(gameScene);
					window.show();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe una partida guardada", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public boolean playerExists(String name) {

		return game.playerExists(name);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File file = new File("Save");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				game = (Game) ois.readObject();
				fis.close();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			game = new Game();
		}

	}

}
