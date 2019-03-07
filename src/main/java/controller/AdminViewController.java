package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Henkilökunta;
import view.HyteGUI_IF;

public class AdminViewController implements Initializable  {

	@FXML Button logout;	
	@FXML Pane rootPane;
	@FXML Tab add;
	@FXML Tab modify;
	@FXML TextField id;

	public AdminViewController() {	

	}

	@FXML
	public void init() throws IOException {
		Pane pane = FXMLLoader.load(getClass().getResource("/AdminView.fxml"));
		//rootPane.getChildren().setAll(pane);
		Stage stage = new Stage();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void addPerson() {
		
	}
	
	public void modify() {
		Henkilökunta hkunta = new Henkilökunta();
		
		if(Integer.parseInt(getNro()) == hkunta.getHenkilökuntaID()) {
			
		}
		
	}
	
	@FXML
	public void logout() {
		
	}
	
	public String getNro() {
		return this.id.getText();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialised");		
	}
	
}
