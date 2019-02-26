package view;

import java.io.IOException;

import controller.Controller;
import controller.Controller_IF;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HyteGUI extends Application implements HyteGUI_IF {
	private Controller_IF controller;
	private TextField username;
	private PasswordField password;
	private BorderPane rootLayout;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void init() {
		controller = new Controller(this);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Testiäppi");

        initLayout();
		
	}
	
	public void initLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(HyteGUI.class.getResource("Skene.fxml"));
		
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public String getUsername() {
		return this.username.getText();
	}
	public String getPassword() {
		return this.password.getText();
	}

}
