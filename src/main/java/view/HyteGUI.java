package view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/**
 * 
 * @author Jeremiaza
 * Sovelluksen käyttäjäliittymän kirjautumisruutu
 * 
 */
public class HyteGUI extends Application {
	
	private Stage primaryStage;
	ResourceBundle bundle;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		Locale currentLocale;
		String language = "fi";
		String country = "FI";
		
		currentLocale = new Locale(language, country);
		bundle = ResourceBundle.getBundle("properties.LoginProperties", currentLocale);	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");        
        AnchorPane rootLayout = FXMLLoader.load(getClass().getResource("/LoginView.fxml"), bundle);       
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	

	public Stage getStage() {
		return primaryStage;
	}
}
