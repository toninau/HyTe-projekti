package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.enums.Bundles;
import view.enums.FxmlEnum;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.application.LauncherImpl;

/**
 * 
 * Application login screen
 * 
 */

@SuppressWarnings("restriction")
public class HyteGUI extends Application{

	private static Locale currentLocale = new Locale("fi", "FI");
	private AnchorPane rootLayout;

	public static void main(String[] args) {
		LauncherImpl.launchApplication(HyteGUI.class, PreloaderView.class, args);
	}

	@Override
	public void init() throws IOException {
		initLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), currentLocale);
		rootLayout = FXMLLoader.load(getClass().getResource(FxmlEnum.LOGIN.getFxml()), bundle);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage stage = primaryStage;
		stage.setTitle("Login");
		Scene scene = new Scene(rootLayout);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/ICON.png")));
		stage.setScene(scene);
		stage.show();
	}

	public static ObservableList<Locale> getSupportedLocales() {
		ObservableList<Locale> observableList = FXCollections.observableArrayList();
		observableList.add(new Locale("de", "DE"));
		observableList.add(new Locale("en", "GB"));
		observableList.add(new Locale("es", "ES"));
		observableList.add(new Locale("fi", "FI"));
		observableList.add(new Locale("fr", "FR"));
		observableList.add(new Locale("sv", "SE"));

		return observableList;
	}

	public static void initLocale() {
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
