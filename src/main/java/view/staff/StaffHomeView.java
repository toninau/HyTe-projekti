package view.staff;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import controller.StaffController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Appointment;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class StaffHomeView extends ViewChanger implements Initializable{

	@FXML ListView dailyNews;
	@FXML ListView StaffCustomers;
	@FXML Label welcomeText;
	@FXML Button newsTab;
	@FXML ListView<String> appointmentList;
	@FXML private Button logout;
	
	private StaffController controller;
	private ResourceBundle bundle;
	
	public StaffHomeView() {
		controller = new StaffController(this);
	}
	
		
	
	public void todaysAppointments() {
		Appointment[] appointments = controller.allAppointments();
		for (Appointment appointment : appointments) {
			if(appointment.getDate().equals(getDate())) {
				appointmentList.getItems().add(appointment.toStringStaff());
			}
		}
	}
	
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());	
		todaysAppointments();
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
}
