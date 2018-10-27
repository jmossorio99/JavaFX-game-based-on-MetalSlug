package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Hero;
import threads.MoveThread;

public class GameViewController implements Initializable{

	@FXML
	private ImageView heroImageView; 
	private Hero hero;
	private MoveThread moveThread;
	
	public void setGame() {
		
		
		
	}
	
	public boolean getHeroMoving() {
		
		return hero.isMoving();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		heroImageView = new ImageView();
		hero = new Hero(heroImageView.getLayoutX(), heroImageView.getLayoutY());
		
		
	}
	
}
