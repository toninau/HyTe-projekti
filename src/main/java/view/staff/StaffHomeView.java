package view.staff;

import controller.StaffController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Appointment;
import model.Staff;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StaffHomeView extends ViewChanger implements Initializable {

	@FXML
	ListView dailyNews;
	@FXML
	ListView StaffCustomers;
	@FXML
	Label welcomeText;
	@FXML
	Button newsTab;
	@FXML
	ListView<String> appointmentList;
	@FXML
	private Button logout;
	
	private StaffController controller;
	private ResourceBundle bundle;

	public StaffHomeView() {
		controller = new StaffController(this);
	}

	public void todaysAppointments() {
		ObservableList<Appointment> appointments = controller.allAppointments();

		for (Appointment appointment : appointments) {
			if (appointment.getDate().isEqual(LocalDate.now())) {
				appointmentList.getItems().add(appointment.toStringStaff());
				if(getTime().isBefore(appointment.getTime())) {
					
				}
			}
		}
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());
		todaysAppointments();
		Staff staff = controller.getLoggedStaff();
		welcomeText.setText(welcomeText.getText()+ " " +staff.getFirstName()+ " " + staff.getSurname());
	}

	/**
	 * Formats and returns the current date.
	 * 
	 * @return Formatted date.
	 */
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}

	public LocalTime getTime() {
		return LocalTime.now();
	}

	public LocalTime toDateTime(String strTime) {
		LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("HH:mm"));
		return time;
	}
}
