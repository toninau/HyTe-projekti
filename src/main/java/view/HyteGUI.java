package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	static Locale currentLocale = null;

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
        AnchorPane rootLayout = FXMLLoader.load(getClass().getResource(fxmls.LOGIN.getFxml()), bundle);       
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	
	public void initPreload() {

	}
	
	public static ObservableList<Locale> getSupportedLocales() {
		ObservableList<Locale> observableList = FXCollections.observableArrayList();
		observableList.add(new Locale("fi", "FI"));
		observableList.add(new Locale("en", "GB"));
	    return observableList;
	}
	
	public void initLocale() {
		String language = "fi";
		String country = "FI";
		currentLocale = new Locale(language, country);
	}
	
	public static void setLocale(Locale newLocale) {
		currentLocale = newLocale;
	}
	
	public static Locale getLocale() {
		return currentLocale;
	}
}
