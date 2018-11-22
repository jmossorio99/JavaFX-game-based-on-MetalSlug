package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ScoresWindowController implements Initializable {

	
    @FXML private TextField playerNameTextField;
    @FXML private ListView<String> listView;

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
	}

	@FXML
	public void upwardNames(ActionEvent event) {
		
	}

	@FXML
	public void downwardNames(ActionEvent event) {
		
	}

	@FXML
	public void upwardScores(ActionEvent event) {
		
	}

	@FXML
	public void downwardScores(ActionEvent event) {
		
	}

	@FXML
	public void searchPlayer(ActionEvent event) {
		
	}

	@FXML
	public void deletePlayer(ActionEvent event) {
		
    }
    
}