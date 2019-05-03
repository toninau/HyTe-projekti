package view.customer;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import antlr.collections.List;
import controller.CustomerController;
import controller.CustomerController_IF;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class CustomerCalendarView extends ViewChanger implements Initializable {

	@FXML
	private Button homeButton;
	@FXML
	private Button calendarButton;
	@FXML
	private Button helpButton;
	@FXML
	private Button myHealthButton;
	@FXML
	private Button logout;
	@FXML
	private Tab dayTab;
	@FXML
	private TabPane calendarTabPane;
	@FXML
	private GridPane grid;
	@FXML
	private Button nextMonth;
	@FXML
	private Button previousMonth;
	@FXML
	private Label monthLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Button nextDay;
	@FXML
	private Button previousDay;
	@FXML private Label weekLabel;
	@FXML private ListView<String> weekTimeList;
	@FXML
	private Button mondaybutton;
	@FXML
	private Button tuesdaybutton;
	@FXML
	private Button wednesdaybutton;
	@FXML
	private Button thursdaybutton;
	@FXML
	private Button fridaybutton;
	@FXML
	private Button saturdaybutton;
	@FXML
	private Button sundaybutton;
	@FXML
	private HBox mondaytextarea;
	@FXML
	private HBox tuesdaytextarea;
	@FXML
	private HBox wednesdaytextarea;
	@FXML
	private HBox thursdaytextarea;
	@FXML
	private HBox fridaytextarea;
	@FXML
	private HBox saturdaytextarea;
	@FXML
	private HBox sundaytextarea;
	@FXML
	private ListView<String> appointmentListView;
	@FXML
	private GridPane addAppointmentPane;
	@FXML
	private ListView<String> tuesdayList;

	private ResourceBundle bundle;
	private CustomerController controller;
	private String previoustextarea = "jotain";
	private HashMap<Button, HBox> map = new HashMap<Button, HBox>();
	private Month month;
	private int year;
	private int day;

	private Appointment[] appointments;

	public CustomerCalendarView() {
		controller = new CustomerController(this);

	}

	public void showDayAppointments(LocalDate date) {
		dateLabel.setText(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
		ObservableList<String> noContent = FXCollections.observableArrayList();
		ObservableList<Appointment> todaysAppointments = appointmentsList(date);
		if (appointmentListView.getItems().size() > 0) {
			appointmentListView.getItems().clear();
		}
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				noContent.add("0" + Integer.toString(i) + ".00");
			} else {
				noContent.add(Integer.toString(i) + ".00");
			}
		}
		appointmentListView.getItems().addAll(noContent);

		for (Appointment appointment : todaysAppointments) {
			int index = appointment.getTime().getHour();
			appointmentListView.getItems().set(index, index + ".00   " + appointment.getInfo());
		}
		appointmentListView.scrollTo(LocalTime.now().getHour());
	}

	public void addAppointment() {
		appointmentListView.setVisible(false);
		addAppointmentPane.setVisible(true);

	}

	public void exitAddAppointment() {
		appointmentListView.setVisible(true);
		addAppointmentPane.setVisible(false);
	}

	public void nextDay() {
		if (day == month.maxLength()) {
			month = month.plus(1);
			day = 0;
		}
		day++;
		showDayAppointments(LocalDate.of(year, month, day));

	}

	public void previousDay() {
		if (day == 1) {
			month = month.minus(1);
			day = month.maxLength();
		}
		day--;
		showDayAppointments(LocalDate.of(year, month, day));
	}

	public ObservableList<Appointment> appointmentsList(LocalDate date) {
		ObservableList<Appointment> data = FXCollections.observableArrayList();
		if (!data.isEmpty()) {
			data.clear();
		}
		for (Appointment appointment : appointments) {
			if (appointment.getDate().isEqual(date))
				data.add(appointment);
		}
		return data;
	}

	public int startDay() {
		LocalDate d = LocalDate.of(year, month.getValue(), 1);
		return d.getDayOfWeek().getValue();
	}

	public void populateGridPane(Month month, int year) {
		final int MAX_ROW = 7;
		final int MAX_COL = 7;
		int row = 1;
		int column = 0;
		day = 0;
		monthLabel.setText(month.toString() + " " + year);

		if (grid.getChildren().size() > 0) {
			grid.getChildren().clear();
		}
		loadGridPaneFirstRow();

		for (int i = 1; i < startDay(); i++) {
			StackPane p = createGridCell();
			Label dayLabel = new Label(Integer.toString(month.minus(1).maxLength() + 1 - startDay() + i));
			p.getChildren().add(dayLabel);
			grid.add(p, column, row);
			column++;
			p.setDisable(true);
		}

		while (day < month.maxLength()) {
			day++;

			StackPane p = createGridCell();
			Label dayLabel = new Label(Integer.toString(day));
			p.getChildren().add(dayLabel);
			GridPane.setHalignment(p, HPos.CENTER);
			if (column == MAX_COL) {
				column = 0;
				row++;
			}
			grid.add(p, column, row);

			column++;

			if (checkAppointments(day, month.getValue(), year)) {
				p.setStyle("-fx-background-color: red");
				p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					calendarTabPane.getSelectionModel().select(dayTab);
					day = Integer.parseInt(dayLabel.getText());
					showDayAppointments(LocalDate.of(year, month, day));
				});
			}
			if (LocalDate.now().isEqual(LocalDate.of(year, month.getValue(), day))) {
				p.getStyleClass().add("currentDate");
			}
		}

		day = 0;
		while (row < MAX_ROW && column <= MAX_COL) {
			day++;

			StackPane p = createGridCell();
			Label dayLabel = new Label(Integer.toString(day));
			p.getChildren().add(dayLabel);
			GridPane.setHalignment(p, HPos.CENTER);
			p.setDisable(true);

			if (column == MAX_COL) {
				column = 0;
				row++;
			}
			grid.add(p, column, row);
			column++;
		}
	}

	public boolean checkAppointments(int day, int month, int year) {
		boolean isAppointment = false;
		for (Appointment appointment : appointments) {
			if (appointment.getDate().getDayOfMonth() == day && appointment.getDate().getMonthValue() == month
					&& appointment.getDate().getYear() == year) {
				isAppointment = true;
			}
		}
		return isAppointment;
	}

	private void loadGridPaneFirstRow() {
		String[] dayList = new String[DayOfWeek.values().length + 1];

		for (int i = 1; i < dayList.length; i++) {
			dayList[i] = (bundle.getString("calendar." + DayOfWeek.of(i).toString().toLowerCase()));
		}

		for (int i = 1; i < dayList.length; i++) {
			StackPane p = createGridCell();
			Label tempLabel = new Label(dayList[i]);
			p.getChildren().add(tempLabel);
			GridPane.setHalignment(tempLabel, HPos.CENTER);
			GridPane.setValignment(tempLabel, VPos.CENTER);
			grid.add(p, i - 1, 0);
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

	public void showWeekAppointments(LocalDate date) {
		
		weekLabel.setText(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + date.get(WeekFields.ISO.weekOfYear()));
		ObservableList<ListView<String>> listViews = FXCollections.observableArrayList();
		listViews.add(tuesdayList);
		ObservableList<String> noContent = FXCollections.observableArrayList();

		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				noContent.add("0" + Integer.toString(i) + ".00");
			} else {
				noContent.add(Integer.toString(i) + ".00");
			}
		}
		weekTimeList.getItems().addAll(noContent);
				
		for (int j = 0; j < listViews.size(); j++) {
			ObservableList<Appointment> todaysAppointments = appointmentsList(date.plusDays(j));
			if (listViews.get(0).getItems().size() > 0) {
				listViews.get(0).getItems().clear();
			}


			for (Appointment appointment : todaysAppointments) {
				int index = appointment.getTime().getHour();
				listViews.get(0).getItems().set(index, index + ".00   " + appointment.getInfo());
			}
			listViews.get(0).scrollTo(LocalTime.now().getHour());
		}

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
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		month = LocalDate.now().getMonth();
		year = LocalDate.now().getYear();
		day = LocalDate.now().getDayOfMonth();
		appointments = controller.customersAppointments();
		addAppointmentPane.setVisible(false);

		createHashMap();
		populateGridPane(month, year);
		showDayAppointments(LocalDate.now());
		showWeekAppointments(LocalDate.now());
	}

}
