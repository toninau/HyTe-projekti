package view.customer;

import java.io.IOException;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.ResourceBundle;

import controller.CustomerController;
import controller.StaffController;
import javafx.animation.AnimationTimer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Appointment;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

/**
 * This class is a fxml controller for CalendarView fxml file. Creates a
 * monthly, weekly and daily calendar.
 * 
 * @author IdaKi
 *
 */
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
	@FXML
	private Label weekLabel;
	@FXML
	private Label tuesdayLabel;
	@FXML
	private Label mondayLabel;
	@FXML
	private Label wednesdayLabel;
	@FXML
	private Label thursdayLabel;
	@FXML
	private Label fridayLabel;
	@FXML
	private Label saturdayLabel;
	@FXML
	private Label sundayLabel;
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
	@FXML
	private ListView<String> mondayList;
	@FXML
	private ListView<String> wednesdayList;
	@FXML
	private ListView<String> thursdayList;
	@FXML
	private ListView<String> fridayList;
	@FXML
	private ListView<String> saturdayList;
	@FXML
	private ListView<String> sundayList;

	private ResourceBundle bundle;
	private CustomerController controller;
	private StaffController staffController;
	private String previoustextarea = "jotain";
	private HashMap<Button, HBox> map = new HashMap<>();
	private Month month;
	private int year;
	private int day;

	private Appointment[] appointments;

	/**
	 * Constructor for customer calendar view. Creates controllers for staff and
	 * customer.
	 * 
	 * @see controller.StaffController#StaffController(CustomerCalendarView)
	 * @see controller.CustomerController#CustomerController(CustomerCalendarView)
	 */
	public CustomerCalendarView() {
		controller = new CustomerController(this);
		staffController = new StaffController();
	}

	/**
	 * Adds the day appointment to a list view.
	 * 
	 * @param date The day which appointments are to be shown.
	 */
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

	/**
	 * Open add appointment view.
	 */
	public void addAppointment() {
		appointmentListView.setVisible(false);
		addAppointmentPane.setVisible(true);
	}

	/**
	 * Exit add appointment view.
	 */
	public void exitAddAppointment() {
		appointmentListView.setVisible(true);
		addAppointmentPane.setVisible(false);
	}

	/**
	 * Changes to the next day when a button is clicked.
	 */
	public void nextDay() {
		if (day == month.maxLength()) {
			month = month.plus(1);
			day = 0;
		}
		day++;
		showDayAppointments(LocalDate.of(year, month, day));

	}

	/**
	 * Changes to the previous day when a button is clicked.
	 */
	public void previousDay() {
		if (day == 1) {
			month = month.minus(1);
			day = month.maxLength();
		}
		day--;
		showDayAppointments(LocalDate.of(year, month, day));
	}

	/**
	 * Adds appointments with the given date to an observable list
	 * 
	 * @param date The date which appointments are wanted.
	 * @return Observable list of the given day's appointments.
	 */
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

	/**
	 * Calculates the day of the week that is the first day of the month.
	 * 
	 * @return integer value of the month's starting day. (e.g. Monday = 1 etc.)
	 */
	public int startDay() {
		LocalDate d = LocalDate.of(year, month.getValue(), 1);
		return d.getDayOfWeek().getValue();
	}

	/**
	 * Populates the grid pane to look like a monthly calendar.
	 * 
	 * @param month The month to be shown.
	 * @param year  The year.
	 */
	public void populateGridPane(Month month, int year) {
		final int MAX_ROW = 6;
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
			checkAppointments(day, month.getValue(), year, p);
			checkCurrentDate(day, month.getValue(), year, p);
		}
		nextMonthDays(column, MAX_COL, MAX_ROW, row);
	}

	/**
	 * Adds the next month's day to the grid pane.
	 * 
	 * @param day
	 * @param column
	 * @param maxcol
	 * @param maxrow
	 * @param row
	 */
	public void nextMonthDays(int column, int maxcol, int maxrow, int row) {
		int d = 0;
		while (row <= maxrow && column <= maxcol) {
			d++;
			boolean done = false;
			if (column == maxcol) {
				if (row == maxrow) {
					done = true;
				}
				column = 0;
				row++;
			}
			if (done) {
				break;
			} else {
				StackPane p = createGridCell();
				Label dayLabel = new Label(Integer.toString(d));
				p.getChildren().add(dayLabel);
				GridPane.setHalignment(p, HPos.CENTER);
				p.setDisable(true);
				grid.add(p, column, row);
			}
			column++;
		}
	}

	/**
	 * Checks if there are appointments on the given day.
	 * 
	 * @param day   Day of the date to be checked.
	 * @param month Month of the date to be checked.
	 * @param year  Year of the date to be checked.
	 * @param stack pane which is the cell of the grid pane.
	 */
	public void checkAppointments(int day, int month, int year, StackPane p) {
		for (Appointment appointment : appointments) {
			if (appointment.getDate().getDayOfMonth() == day && appointment.getDate().getMonthValue() == month
					&& appointment.getDate().getYear() == year) {
				setEventListener(p, day);
			}
		}
	}

	/**
	 * Sets the current dates cell style.
	 * 
	 * @param day   Day of the date to be checked.
	 * @param month Month of the date to be checked.
	 * @param year  Year of the date to be checked.
	 * @param p     StackPane which is the cell of the grid pane.
	 */
	public void checkCurrentDate(int day, int month, int year, StackPane p) {
		if (LocalDate.now().isEqual(LocalDate.of(year, month, day))) {
			p.getStyleClass().add("currentDate");
		}
	}

	/**
	 * Sets an event listener to a grid pane cell.
	 * 
	 * @param p   Cell to set the listener to.
	 * @param day Day which appointments can be seen when clicked.
	 */
	public void setEventListener(StackPane p, int day) {
		p.setStyle("-fx-background-color: #cfe0fc; -fx-cursor: hand ");
		p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			calendarTabPane.getSelectionModel().select(dayTab);
			showDayAppointments(LocalDate.of(year, month, day));
		});
	}

	/**
	 * Populates the grid panes first row with the weekdays. (Mon, Tue, Wed...)
	 */
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

	/**
	 * Creates the grid cell to be used in the grid pane.
	 * 
	 * @return StackPane working as the cell.
	 */
	public StackPane createGridCell() {
		StackPane p = new StackPane();
		p.getStylesheets().add(getClass().getResource("/css/view.css").toExternalForm());
		p.getStyleClass().add("stackpane");
		return p;
	}

	/**
	 * Changes to the next month when a button is clicked.
	 */
	public void nextMonth() {
		if (month.getValue() == 12) {
			year++;
		}
		month = month.plus(1);
		populateGridPane(month, year);
	}

	/**
	 * Changes to the previous month when a button is clicked.
	 */
	public void previousMonth() {
		if (month.getValue() == 1) {
			year--;
		}
		month = month.minus(1);
		populateGridPane(month, year);
	}

	/**
	 * Animation for widening the week calendar's weekday columns
	 * 
	 * @param event MouseClicked.
	 * @throws IOException
	 */
	public void openbox(MouseEvent event) throws IOException {
		final Button btn = (Button) event.getSource();
		new AnimationTimer() {
			private long sleepNanoseconds = 15000000;
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

	/**
	 * Calculates the day value for the week calendar.
	 * 
	 * @param date
	 * @param a
	 * @return The date for the week calendar.
	 */
	public LocalDate calculateDay(LocalDate date, int a) {
		int result = date.getDayOfMonth();
		int monthW = date.getMonthValue();
		int weekDayNow = date.getDayOfWeek().getValue();
		long weekDay = (long) a;
		if (weekDay < weekDayNow) {
			result = date.minusDays(weekDayNow - weekDay).getDayOfMonth();
			if (result > date.getDayOfWeek().getValue()) {
				monthW = month.minus(1).getValue();
			}
		} else if (weekDay > weekDayNow) {
			result = date.plusDays(weekDay - weekDayNow).getDayOfMonth();
			if (result > date.getMonth().maxLength()) {
				monthW = month.plus(1).getValue();
			}
		}
		return LocalDate.of(year, monthW, result);
	}

	/**
	 * Populates the labels and list views using the calculateDay() method.
	 * 
	 * @param date The date which week is to be shown.
	 * @see #calculateDay(LocalDate, int)
	 */
	public void showWeekAppointments(LocalDate date) {
		weekLabel.setText(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " WEEK "
				+ date.get(WeekFields.ISO.weekOfYear()));

		ObservableList<Appointment> mondayAppointments = appointmentsList(calculateDay(date, 1));
		ObservableList<Appointment> tuesdayAppointments = appointmentsList(calculateDay(date, 2));
		ObservableList<Appointment> wednesdayAppointments = appointmentsList(calculateDay(date, 3));
		ObservableList<Appointment> thursdayAppointments = appointmentsList(calculateDay(date, 4));
		ObservableList<Appointment> fridayAppointments = appointmentsList(calculateDay(date, 5));
		ObservableList<Appointment> saturdayAppointments = appointmentsList(calculateDay(date, 6));
		ObservableList<Appointment> sundayAppointments = appointmentsList(calculateDay(date, 7));

		Label[] labels = { mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel,
				sundayLabel };

		for (int i = 0; i < labels.length; i++) {
			labels[i].setText(Integer.toString(calculateDay(date, i + 1).getDayOfMonth()));
		}
		for (Appointment appointment : mondayAppointments) {
			mondayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : tuesdayAppointments) {
			tuesdayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : wednesdayAppointments) {
			wednesdayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : thursdayAppointments) {
			thursdayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : fridayAppointments) {
			fridayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : saturdayAppointments) {
			saturdayList.getItems().add(appointment.getInfo());
		}
		for (Appointment appointment : sundayAppointments) {
			sundayList.getItems().add(appointment.getInfo());
		}
	}

	/**
	 * Creates a hash map for the week calendar animation.
	 * 
	 * @see #openbox(MouseEvent)
	 */
	public void createHashMap() {
		map.put(mondaybutton, mondaytextarea);
		map.put(tuesdaybutton, tuesdaytextarea);
		map.put(wednesdaybutton, wednesdaytextarea);
		map.put(thursdaybutton, thursdaytextarea);
		map.put(fridaybutton, fridaytextarea);
		map.put(saturdaybutton, saturdaytextarea);
		map.put(sundaybutton, sundaytextarea);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		month = LocalDate.now().getMonth();
		year = LocalDate.now().getYear();
		day = LocalDate.now().getDayOfMonth();
		if (controller.getLoggedCustomer() != null) {
			appointments = controller.customersAppointments();
		}
		if (staffController.getLoggedStaff() != null) {
			ObservableList<Appointment> a = staffController.allAppointments();
			appointments = new Appointment[a.size()];
			for (int i = 0; i < a.size(); i++) {
				appointments[i] = a.get(i);
			}
		}
		addAppointmentPane.setVisible(false);

		createHashMap();
		populateGridPane(month, year);
		showDayAppointments(LocalDate.now());
		showWeekAppointments(LocalDate.now());
	}

}
