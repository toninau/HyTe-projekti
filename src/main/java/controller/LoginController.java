package controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.HyteGUI_IF;

public class LoginController {
	HyteGUI_IF gui;
	
	@FXML PasswordField pw;
	@FXML Button loginBtn;
	@FXML TextField username;
	@FXML Label label;

	public LoginController() {	
	}
	
	@FXML
	public void login() throws IOException {	
		
		if(getUsername().equals("admin") && getPassword().equals("admin")) {
			AdminViewController ac = new AdminViewController();
			ac.init();
		}
		label.setText("Welcome");
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	public String getPassword() {
		return this.pw.getText();
	}

	/*
	public void sceneContent(String fxml) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));			
		try {
			rootLayout.getChildren().setAll(FXMLLoader.load(getClass().getResource(fxml)));		
		} catch (IOException e) {
			e.getMessage();
		}
		primaryStage.getScene().setRoot(rootLayout);			
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		a = new AdminViewController();		
	}
	*/
}
