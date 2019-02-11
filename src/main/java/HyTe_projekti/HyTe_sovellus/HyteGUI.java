package HyTe_projekti.HyTe_sovellus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HyteGUI extends Application implements HyteGUI_IF {
	private Controller_IF controller;
	private TextField username;
	private PasswordField password;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void init() {
		controller = new Controller(this);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		username = new TextField("Käyttäjätunnus");
		password = new PasswordField();
		
		Button loginBtn = new Button("Kirjaudu sisään");
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
			}
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(20);
		grid.setHgap(10);
		grid.add(username, 0, 0);
		grid.add(password, 0, 1);
		
	}
	public String getUsername() {
		return this.username.getText();
	}
	public String getPassword() {
		return this.password.getText();
	}

}
