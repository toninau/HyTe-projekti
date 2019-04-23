package view;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PreloaderView extends Preloader {
	ImageView iv;
	Stage stage;
	boolean isEmbedded;
	
	private Scene createPreloaderScene() {
        iv = new ImageView();
        iv.setImage(new Image(getClass().getResource("/pictures/tenor.gif").toExternalForm()));
        BorderPane p = new BorderPane();
        p.setStyle("-fx-background-color: white");
        p.setCenter(iv);
        return new Scene(p, 1280, 720);        
    }
	
	
	public void start(Stage primaryStage) throws Exception {
		isEmbedded = (primaryStage.getWidth()>0);
		this.stage = primaryStage;
	
		stage.setScene(createPreloaderScene());
		stage.show();
	}
	
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    } 
	
	@Override
	public void handleProgressNotification(ProgressNotification pn) {

	}
}
