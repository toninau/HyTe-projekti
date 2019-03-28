package view;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.WeatherAPICall;

public class CustomerView extends ViewChanger {
	
	@FXML Text weatherCity;
	@FXML Text weatherState;
	@FXML Text weatherCelsius;
	@FXML TextArea notification;
	@FXML Text welcomeText;
	@FXML Button logout;
	@FXML Button mondaybutton;
	@FXML Button tuesdaybutton;
	@FXML Button wednesdaybutton;
	@FXML HBox mondaytextarea;
	@FXML HBox tuesdaytextarea;
	@FXML HBox wednesdaytextarea;
	@FXML HBox thursdaytextarea;
	@FXML HBox fridaytextarea;
	@FXML HBox saturdaytextarea;
	@FXML HBox sundaytextarea;
	
	
	/**public void setCelcius() {
		this.celcius.setText(null);
	}**/
	
	public void logout(MouseEvent event) throws IOException {
		String fxml = "/LoginView.fxml";
		String title  = "Login";
		sceneContent(fxml, event, title);
	}
	
	public void openbox(MouseEvent event) throws IOException {
		Button btn = (Button) event.getSource();
		String id = btn.getId();
		mondaytextarea.setPrefWidth(200);
		tuesdaytextarea.setPrefWidth(200);
		wednesdaytextarea.setPrefWidth(200);
		thursdaytextarea.setPrefWidth(200);
		fridaytextarea.setPrefWidth(200);
		saturdaytextarea.setPrefWidth(200);
		sundaytextarea.setPrefWidth(200);
		
		
		new AnimationTimer() {
			private long sleepNanoseconds = 15 * 1000000;
            private long prevTime = 0;

            public void handle(long currentNanoTime) {

                if ((currentNanoTime - prevTime) < sleepNanoseconds) {
                    return;
                }
                if (id.equals("mondaybutton")) {
                	mondaytextarea.setPrefWidth(mondaytextarea.getPrefWidth()+10);
                }
                if (id.equals("tuesdaybutton")) {
                	tuesdaytextarea.setPrefWidth(tuesdaytextarea.getPrefWidth()+10);
                }
                if (id.equals("wednesdaybutton")) {
                	wednesdaytextarea.setPrefWidth(wednesdaytextarea.getPrefWidth()+10);
                }
                if (id.equals("thursdaybutton")) {
                	thursdaytextarea.setPrefWidth(thursdaytextarea.getPrefWidth()+10);
                }
                if (id.equals("fridaybutton")) {
                	fridaytextarea.setPrefWidth(fridaytextarea.getPrefWidth()+10);
                }
                if (id.equals("saturdaybutton")) {
                	saturdaytextarea.setPrefWidth(saturdaytextarea.getPrefWidth()+10);
                }
                if (id.equals("sundaybutton")) {
                	sundaytextarea.setPrefWidth(sundaytextarea.getPrefWidth()+10);
                }
   
                prevTime = currentNanoTime;
                if (mondaytextarea.getPrefWidth()>=400 || tuesdaytextarea.getPrefWidth()>=400 ||
                		wednesdaytextarea.getPrefWidth()>=400 || thursdaytextarea.getPrefWidth()>=400 ||
                		fridaytextarea.getPrefWidth()>=400 || saturdaytextarea.getPrefWidth()>=400 ||
                		sundaytextarea.getPrefWidth()>=400) {
                	stop();
                }
            }
		}.start();
	}
	
	public void initialize() {
		try {
			WeatherAPICall weather = new WeatherAPICall();
			weatherState.setText(weather.getState());
			weatherCelsius.setText(weather.getCelsius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
