package view.customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.ResourceBundle;

import controller.CustomerController;
import controller.CustomerController_IF;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Appointment;
import model.Prescription;
import model.Staff;
import view.ViewChanger;

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
	Tab dayTab;
	@FXML
	TabPane calendarTabPane;
	@FXML
	GridPane grid;
	@FXML
	Button nextMonth;
	@FXML
	Button previousMonth;
	@FXML
	Label monthLabel;
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
	@FXML
	private ListView<Appointment> appointmentListView;
	@FXML
	private VBox timeBox;

	private CustomerController controller;
	private String previoustextarea = "jotain";
	private HashMap<Button, HBox> map = new HashMap<Button, HBox>();
	private Month month;
	private int year;
	private Appointment[] appointments;

	public CustomerCalendarView() {
		controller = new CustomerController(this);
		appointments = controller.customersAppointments();

	}


	public void showDayAppointments(LocalDate date) {
		for (int i = 1; i <= 24; i++) {
			if(i<10) {
				timeBox.getChildren().add(new Label("0"+Integer.toString(i)+".00"));
			}else {
				timeBox.getChildren().add(new Label(Integer.toString(i)+".00"));
			}
		}
		appointmentListView.getItems().addAll(appointmentsList(date));
	}

	public ObservableList<Appointment> appointmentsList(LocalDate date) {
		ObservableList<Appointment> data = FXCollections.observableArrayList();
		for (Appointment appointment : appointments) {
			if (appointment.getDate().isEqual(date))
				data.add(appointment);
		}
		return data;
	}
	

	public void populateGridPane(Month month, int year) {
		int day = 0;
		monthLabel.setText(month.toString() + " " + year);

		if (grid.getChildren().size() > 0) {
			grid.getChildren().clear();
		}
		loadGridPaneFirstRow();
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j <= 6; j++) {
				day++;
				StackPane p = createGridCell();
				Label dayLabel = new Label(Integer.toString(day));
				p.getChildren().add(dayLabel);
				GridPane.setHalignment(p, HPos.CENTER);
				grid.add(p, j, i);
				if (day == month.maxLength()) {
					day = 0;
				}
				if (checkAppointments(day, month.getValue(), year)) {
					p.setStyle("-fx-background-color: red");
					p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						calendarTabPane.getSelectionModel().select(dayTab);
					});
				}
			}
		}
	}

	public boolean checkAppointments(int day, int month, int year) {
		boolean isAppointment = false;
		for (Appointment appointment : appointments) {
			if (appointment.getDate().getDayOfMonth() == day && appointment.getDate().getMonthValue() == month
					&& appointment.getDate().getYear() == year) {
				isAppointment = true;
				showDayAppointments(LocalDate.of(year, month, day));
			}
		}
		return isAppointment;
	}

	private void loadGridPaneFirstRow() {
		String[] string = { "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday" };
		for (int i = 0; i < string.length; i++) {
			StackPane p = createGridCell();
			Label tempLabel = new Label(string[i]);
			p.getChildren().add(tempLabel);
			GridPane.setHalignment(tempLabel, HPos.CENTER);
			grid.add(p, i, 0);
		}
	}

	public StackPane createGridCell() {
		StackPane p = new StackPane();
		p.getStylesheets().add(getClass().getResource("/css/view.css").toExternalForm());
		p.getStyleClass().add("stackpane");
		return p;
	}

	public void nextMonth() {
		if (month.getValue() == 12) {
			year++;
		}
		month = month.plus(1);
		populateGridPane(month, year);
	}

	public void previousMonth() {
		if (month.getValue() == 1) {
			year--;
		}
		month = month.minus(1);
		populateGridPane(month, year);
	}

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
		month = LocalDate.now().getMonth();
		year = LocalDate.now().getYear();

		createHashMap();
		populateGridPane(month, year);
		showDayAppointments(LocalDate.now());
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
