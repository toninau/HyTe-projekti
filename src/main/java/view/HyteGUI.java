package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * Sovelluksen käyttäjäliittymän kirjautumisruutu
 * 
 */
public class HyteGUI extends Application implements HyteGUI_IF{

	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
			
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");        
        AnchorPane rootLayout = FXMLLoader.load(getClass().getResource("/Skene.fxml"));       
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
