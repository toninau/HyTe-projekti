package view;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class for a preloader screen that shows while the application is initialising.
 * @author IdaKi
 *
 */
public class PreloaderView extends Preloader {
	private Stage stage;
    private boolean isEmbedded = false;


	/**
	 * Creates a preloader scene.
	 * @return preloader scene.
	 */
	private Scene createPreloaderScene() {        
        ImageView iv2= new ImageView();
        iv2.setImage(new Image(getClass().getResource("/pictures/logo_1.png").toExternalForm()));
        iv2.setFitWidth(720);
        iv2.setFitHeight(720);
        BorderPane p = new BorderPane();
        p.setStyle("-fx-background-color:  #cfe0fc");
        p.setTop(iv2);
        BorderPane.setAlignment(iv2, Pos.CENTER);
        return new Scene(p, 1280, 720);        
    }
	
	/**
	 * Start method for preloader.
	 * @param Stage.
	 */
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/ICON.png")));
	    isEmbedded = (stage.getWidth() > 0);
		stage.setScene(createPreloaderScene());
		stage.show();
	}
	
	/**
	 * Fades out the preloader.
	 * @param State change notification.
	 */
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
    	 if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
				if (isEmbedded  && stage.isShowing()) {
    	            FadeTransition ft = new FadeTransition(
    	                Duration.millis(1000), stage.getScene().getRoot());
    	                ft.setFromValue(1.0);
    	                ft.setToValue(0.0);
    	                final Stage s = stage;
    	                EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
    	                    public void handle(ActionEvent t) {
    	                        s.hide();
    	                    }
    	                };
    	                ft.setOnFinished(eh);
    	                ft.play();
    	        } else {
    	            stage.hide();
    	        }
    	    }
    } 
	
}
