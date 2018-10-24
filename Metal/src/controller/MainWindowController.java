package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML
    private TextField nickNameTextField;

    @FXML
    private Button playButton;

    @FXML
    private Button scoresButton;

    @FXML
    void playClicked(ActionEvent event) throws IOException {

    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/GameView.fxml"));
    	Parent gameView = loader.load();
    	Scene gameScene = new Scene(gameView);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(gameScene);
    	window.show();
    	
    }

    @FXML
    void scoresClicked(ActionEvent event) {

    }

}
