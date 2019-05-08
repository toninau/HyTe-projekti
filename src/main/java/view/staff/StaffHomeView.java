package view.staff;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import controller.StaffController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Appointment;
import model.Customer;
import model.Staff;
import view.ViewChanger;

public class StaffHomeView extends ViewChanger implements Initializable {

	@FXML
	ListView<String> dailyNews;
	@FXML
	ListView<Customer> staffCustomers;
	@FXML
	Label welcomeText;
	@FXML
	Button newsTab;
	@FXML
	ListView<String> appointmentList;
	@FXML
	private Button logout;
	
	private StaffController controller;

	public StaffHomeView() {
		controller = new StaffController(this);
	}

	public void populateCustomerListView() {
		staffCustomers.getItems().addAll(controller.getStaffCustomers());
		staffCustomers.setCellFactory(param -> new ListCell<Customer>() {
			@Override
			protected void updateItem(Customer item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getSurname() + ", " + item.getFirstName());
				}
			}
		});

	}

	public void todaysAppointments() {
		ObservableList<Appointment> appointments = controller.allAppointments();

		for (Appointment appointment : appointments) {
			if (appointment.getDate().isEqual(LocalDate.now())) {
				appointmentList.getItems().add(appointment.toStringStaff());
			}
		}
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		todaysAppointments();
		Staff staff = controller.getLoggedStaff();
		welcomeText.setText(welcomeText.getText()+ " " +staff.getFirstName()+ " " + staff.getSurname());
		populateCustomerListView();
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
		return LocalTime.parse(strTime, DateTimeFormatter.ofPattern("HH:mm"));
	}
}
