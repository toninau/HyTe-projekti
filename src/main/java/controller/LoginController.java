package controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Asiakas;
import model.DAOManager;
import model.Henkilökunta;
import view.HyteGUI;
import view.HyteGUI_IF;

public class LoginController {
	HyteGUI gui;
	Henkilökunta hkunta;
	Asiakas asiakas;
	
	DAOManager daom;

	@FXML PasswordField pw;
	@FXML Button loginBtn;
	@FXML TextField username;
	@FXML Tab customerTab;
	
	@FXML Tab staffTab;
	@FXML PasswordField pwAsiakas;
	@FXML TextField usernameAsiakas;
	@FXML Button loginBtnAsiakas;

	public LoginController() {	
		daom = new DAOManager();

	}
	
	@FXML
	public void loginStaff(MouseEvent event) throws IOException {
		String fxml = "";
		String title = "Login";
		if(getUsername().equals("admin") && getPassword().equals("admin")) {
			fxml = "/AdminView.fxml";
			title = "Admin view";
		}
		/*else if(hkunta.getHenkilökuntaID() == Integer.parseInt(getUsername())) {
			fxml = "/StaffView.fxml";
			title = "Staff view";
		}*/else {
			fxml = "/Skene.fxml";			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login failed.");
			alert.setHeaderText(null);
			alert.setContentText("Login failed.");
			alert.showAndWait();
		}
		sceneContent(fxml, event, title);
	}
	
	@FXML
	public void loginCustomer(MouseEvent event) {
		String fxml = "/CustomerView.fxml";
		String title = "Welcome!";
		
		if(getUsername().equals(asiakas.getSposti()) && getPassword().equals("asiakkaanpassword")) {
			
		}
	}
	
	public void sceneContent(String fxml, MouseEvent event, String title) throws IOException {
		Pane p = FXMLLoader.load(getClass().getResource(fxml));		
		Scene scene = new Scene(p);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);	
		stage.setTitle(title);
		stage.show();
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	public String getPassword() {
		return this.pw.getText();
	}

	/*
	public void initialize(URL location, ResourceBundle resources) {
		a = new AdminViewController();		
	}
	*/
}
