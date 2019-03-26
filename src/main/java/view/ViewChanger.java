package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewChanger {
	
	/**
	 * Changes scene's content according to given parameters.
	 * @param fxml Given fxml file.
	 * @param event	Event used to help determine which scene is wanted.
	 * @param title	Stage's title.
	 * @throws IOException Loading the fxml file failed.
	 */
	public void sceneContent(String fxml, MouseEvent event, String title) throws IOException {
		Pane p = FXMLLoader.load(getClass().getResource(fxml));		
		Scene scene = new Scene(p);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);	
		stage.setTitle(title);
		stage.show();
	}
}
