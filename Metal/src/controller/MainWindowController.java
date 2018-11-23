package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

		if (!nickNameTextField.getText().isEmpty()) {
			if (nickNameTextField.getText().length() >= 3) {
				Player p = new Player(nickNameTextField.getText());
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
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese un nombre de tres caracteres o más", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ingrese un nombre para el jugador", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@FXML
	void scoresClicked(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ScoresWindow.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(scene);
			window.setResizable(false);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void loadGameClicked(ActionEvent event) {

		
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				
			game=new Game();	
			}
			 
		
		MusicThread musicThread = new MusicThread();
		musicThread.start();
	}

}
