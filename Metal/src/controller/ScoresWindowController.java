package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import exceptions.ArrayListIsNotSortedException;
import exceptions.NoSortCriteriaException;
import exceptions.PlayerDoesNotExistException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;
import model.Player;

public class ScoresWindowController implements Initializable {
	
	private final String sortingNames = "Names";
	private final String sortingScores = "Scores";

	@FXML
	private TextField playerNameTextField;
	@FXML
	private ListView<Player> listView;
	@FXML
	private ChoiceBox<String> sortChoiceBox; 

	private Game game;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setScoresWindow(Game game) {
		this.game = game;
		game.setSortedListByNames(0);
		updateListView(game.getPlayersList());
		ObservableList<String> sort = FXCollections.observableArrayList(sortingNames, sortingScores);
		sortChoiceBox.setItems(sort);
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
	public void upwardTime(ActionEvent event) {
		game.sortPlayerTimes(true);
		updateListView(game.getPlayersList());
	}
	
	@FXML
	public void downwardTime(ActionEvent event) {
		game.sortPlayerTimes(false);
		updateListView(game.getPlayersList());
	}

	@FXML
	public void searchPlayer(ActionEvent event) {
		try {
			if(sortChoiceBox.getSelectionModel().isEmpty())
				throw new NoSortCriteriaException();
			if (!game.isListSortedByNames() && sortChoiceBox.getSelectionModel().getSelectedItem().equals(sortingNames))
				throw new ArrayListIsNotSortedException("Se deben ordenar los jugadores por su nombre primero.");
			if(!game.isListSortedByScores() && sortChoiceBox.getSelectionModel().getSelectedItem().equals(sortingScores))
				throw new ArrayListIsNotSortedException("Se deben ordenar los jugadores por sus puntajes primero.");
			if (playerNameTextField.getText().isEmpty())
				throw new PlayerNameException("Debe ingresar el " + ( (game.isListSortedByNames())? "nombre" : "puntaje" ) + " de un jugador para buscarlo.");
			Player found = null;
			ArrayList<Player> a = null;
			if(sortChoiceBox.getSelectionModel().getSelectedItem().equals(sortingNames)) {
				found = game.searchPlayerName(playerNameTextField.getText());
				a = new ArrayList<Player>();
				a.add(found);
				if (found == null)
					throw new PlayerDoesNotExistException("El jugador con el nombre \"" + playerNameTextField.getText() + "\" no existe.");
			}
			else if(sortChoiceBox.getSelectionModel().getSelectedItem().equals(sortingScores)) {
				a = game.searchPlayerScore(Integer.parseInt(playerNameTextField.getText()));
				if (a.size() == 0)
					throw new PlayerDoesNotExistException("No hay jugadores con puntajes de " + playerNameTextField.getText() + ".");
			}
			updateListView(a);
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Debe introducir un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (PlayerNameException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (PlayerDoesNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			playerNameTextField.setText("");
		}
		catch (ArrayListIsNotSortedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			playerNameTextField.setText("");
		}
		catch(NoSortCriteriaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			playerNameTextField.setText("");
		}
		
	}

	@FXML
	public void deletePlayer(ActionEvent event) {
		try {
			if (!game.isListSortedByNames())
				throw new ArrayListIsNotSortedException("Se deben ordenar los jugadores por su nombre primero.");
			if (playerNameTextField.getText().isEmpty())
				throw new PlayerNameException("Debe ingresar el nombre de un jugador para eliminarlo.");
			Player player = game.searchPlayerName(playerNameTextField.getText());
			if (player == null)
				throw new PlayerDoesNotExistException(playerNameTextField.getText());
			game.deletePlayerFromTree(player);
			game.deletePlayerFromArrayList(player);
			playerNameTextField.setText("");
			updateListView(game.getPlayersList());
		} catch (PlayerNameException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (PlayerDoesNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ArrayListIsNotSortedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	void backToMenu(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setResizable(false);
		window.setScene(scene);
		window.show();
	}

}