package view;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PreloaderView extends Preloader {
	ImageView iv;
	Stage stage;
    boolean isEmbedded = false;


	
	private Scene createPreloaderScene() {
        iv = new ImageView();
        iv.setImage(new Image(getClass().getResource("/pictures/tenor.gif").toExternalForm()));
        ImageView iv2= new ImageView();
        iv2.setImage(new Image(getClass().getResource("/pictures/logo_250.png").toExternalForm()));
        iv2.setFitWidth(200);
        iv2.setFitHeight(200);
        BorderPane p = new BorderPane();
        p.setStyle("-fx-background-color: white");
        p.setTop(iv2);
        BorderPane.setAlignment(iv2, Pos.CENTER);
        p.setCenter(iv);
        return new Scene(p, 1280, 720);        
    }
	
	
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
	    isEmbedded = (stage.getWidth() > 0);
		stage.setScene(createPreloaderScene());
		stage.show();
	}
	
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
	
	@Override
	public void handleProgressNotification(ProgressNotification pn) {

	}
}
