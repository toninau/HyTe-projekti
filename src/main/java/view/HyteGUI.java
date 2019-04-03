package view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 * 
 * @author Jeremiaza
 * Sovelluksen käyttäjäliittymän kirjautumisruutu
 * 
 */

public class HyteGUI extends Application {
	
	private Stage primaryStage;
	private ResourceBundle bundle;
	static Locale currentLocale;
	
	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws IOException {
		//initPreload();
		initLocale();
		bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), currentLocale);	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");        
        AnchorPane rootLayout = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"), bundle);       
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	
	public void initPreload() {
		VBox vbox = new VBox();
		Label label = new Label();
		label.setText("Loading");
		vbox.getChildren().add(label);
		
        Scene sc = new Scene(vbox, 200, 200);
        primaryStage.setScene(sc);
        primaryStage.show();
	}
	
	public void initLocale() {
		String language = "fi";
		String country = "FI";
		currentLocale = new Locale(language, country);
	}
	
	public static Locale getLocale() {
		return currentLocale;
	}
}
