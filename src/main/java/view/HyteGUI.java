package view;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.enums.Bundles;
import view.enums.FxmlEnum;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * Application login screen
 * 
 */

public class HyteGUI extends Application{

	private Stage primaryStage;
	private ResourceBundle bundle;
	static Locale currentLocale = new Locale("fi", "FI");
	AnchorPane rootLayout;

	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		//launch(args);
		LauncherImpl.launchApplication(HyteGUI.class, PreloaderView.class, args);
	}

	@Override
	public void init() throws IOException {
		initLocale();
		bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), currentLocale);
		rootLayout = FXMLLoader.load(getClass().getResource(FxmlEnum.LOGIN.getFxml()), bundle);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		//AnchorPane rootLayout = FXMLLoader.load(getClass().getResource(FxmlEnum.LOGIN.getFxml()), bundle);
		Scene scene = new Scene(rootLayout);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/logo_250.png")));
		primaryStage.setScene(scene);
		primaryStage.show();
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
