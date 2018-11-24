package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exceptions.PlayerNameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;
import model.Player;

public class ScoresWindowController implements Initializable {

	@FXML
	private TextField playerNameTextField;
	@FXML
	private ListView<Player> listView;

	private Game game;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setScoresWindow(Game game) {
		this.game = game;
		updateListView(game.getPlayersList());
	}

	public void updateListView(Collection<Player> array) {
		ObservableList<Player> list = FXCollections.observableArrayList(array);
		listView.setItems(list);
	}

	@FXML
	public void upwardNames(ActionEvent event) {
		game.sortPlayerNames(1);
		updateListView(game.getPlayersList());
	}

	@FXML
	public void downwardNames(ActionEvent event) {
		game.sortPlayerNames(-1);
		updateListView(game.getPlayersList());
	}

	@FXML
	public void upwardScores(ActionEvent event) {
		game.sortPlayerScores(true);
		updateListView(game.getPlayersList());
	}

	@FXML
	public void downwardScores(ActionEvent event) {
		game.sortPlayerScores(false);
		updateListView(game.getPlayersList());
	}

	@FXML
	public void searchPlayer(ActionEvent event) {
		try {
			if (playerNameTextField.getText().isEmpty())
				throw new PlayerNameException("Debe ingresar el nombre de un jugador para buscarlo.");

		} catch (PlayerNameException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void deletePlayer(ActionEvent event) {

	}
	
	@FXML
	void backToMenu (ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation( getClass().getResource( "/view/MainWindow.fxml" ) );
		Parent root = null;
		try {
			root = loader.load();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene( root );
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

}