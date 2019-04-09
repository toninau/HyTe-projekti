package view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CustomerCalendarView extends ViewChanger implements Initializable {

	@FXML
	Button homeButton;
	@FXML
	Button calendarButton;
	@FXML
	Button helpButton;
	@FXML
	Button myHealthButton;
	@FXML
	Button logout;
	@FXML
	Button mondaybutton;
	@FXML
	Button tuesdaybutton;
	@FXML
	Button wednesdaybutton;
	@FXML
	Button thursdaybutton;
	@FXML
	Button fridaybutton;
	@FXML
	Button saturdaybutton;
	@FXML
	Button sundaybutton;
	@FXML
	HBox mondaytextarea;
	@FXML
	HBox tuesdaytextarea;
	@FXML
	HBox wednesdaytextarea;
	@FXML
	HBox thursdaytextarea;
	@FXML
	HBox fridaytextarea;
	@FXML
	HBox saturdaytextarea;
	@FXML
	HBox sundaytextarea;

	private String previoustextarea = "jotain";
	private HashMap<Button, HBox> map = new HashMap<Button, HBox>();

	/**
	 * public void setCelcius() { this.celcius.setText(null); }
	 **/

	public void openbox(MouseEvent event) throws IOException {
		final Button btn = (Button) event.getSource();
		new AnimationTimer() {
			private long sleepNanoseconds = 15 * 1000000;
			private long prevTime = 0;

			public void handle(long currentNanoTime) {

				if ((currentNanoTime - prevTime) < sleepNanoseconds) {
					return;
				}
				for (HBox hbox : map.values()) {
					if (previoustextarea.equals(hbox.getId())) {
						hbox.setPrefWidth(hbox.getPrefWidth() - 10);
					}
				}
				map.get(btn).setPrefWidth(map.get(btn).getPrefWidth() + 10);
				if (map.get(btn).getPrefWidth() >= 400) {
					previoustextarea = map.get(btn).getId();
					stop();
				}
				prevTime = currentNanoTime;
			}
		}.start();
	}

	public void createHashMap() {
		// if (map==null) {
		map.put(mondaybutton, mondaytextarea);
		map.put(tuesdaybutton, tuesdaytextarea);
		map.put(wednesdaybutton, wednesdaytextarea);
		map.put(thursdaybutton, thursdaytextarea);
		map.put(fridaybutton, fridaytextarea);
		map.put(saturdaybutton, saturdaytextarea);
		map.put(sundaybutton, sundaytextarea);

		// }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createHashMap();
	}

	/**
	 * Fired when Home button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHome(Event);
	 */
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	/**
	 * Fired when Calendar button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerCalendar(Event);
	 */
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	/**
	 * Fired when Help button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHelp(Event);
	 */
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	/**
	 * Fired when Health button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHealth(Event);
	 */
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	/**
	 * Fired when logout button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#logoutForAll(Event);
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

}
