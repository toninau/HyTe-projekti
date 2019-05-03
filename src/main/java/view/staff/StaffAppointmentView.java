package view.staff;

import controller.StaffController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Appointment;
import model.Customer;
import view.ViewChanger;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class StaffAppointmentView extends ViewChanger implements Initializable {
	
	@FXML ListView<Appointment> appointmentList;
	@FXML ListView<Customer> customerListView;



    @FXML TextField appointmentInfo;
    @FXML TextField modifyAppointmentInfo;

    @FXML TextField appointmentTime;
    @FXML TextField modifyAppointmentTime;

    @FXML DatePicker appointmentDate;
    @FXML DatePicker modifyAppointmentDate;

    @FXML Button addAppointment;
    @FXML Button modifyAppointment;
    @FXML Button loadCustomers;
    @FXML Button logout;

    @FXML Text loadedCustomerName;

	private ResourceBundle bundle;


	private Appointment appointment;
	private Customer customer;
	StaffController controller;
	
	public StaffAppointmentView() { controller = new StaffController(this); }
	
	public void populateCustomerListView() {
		customerListView.getItems().addAll(controller.getStaffCustomers());
		customerListView.setCellFactory(param -> new ListCell<Customer>() {
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
	
	public void populateAppointmentListView() {
		appointmentList.getItems().addAll(controller.allAppointments());
		appointmentList.getSelectionModel().getSelectedItems();
	}
	@FXML
	public void addAppointment()  {
		Appointment appointment = new Appointment();
		appointment.setStaff(controller.getLoggedStaff());
		appointment.setInfo(appointmentInfo.getText());
		appointment.setCustomer(customer);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String date = appointmentDate.getValue().format(dateFormatter);
		appointment.setDate(date);
		appointment.setTime(appointmentTime.getText());
		if (controller.addAppointment(appointment)) {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.show();
		}
	}
	@FXML
	public void loadCustomer() {
		customer = customerListView.getSelectionModel().getSelectedItem();
		appointmentList.getItems().addAll(controller.getCustomersAppointments(customer));
		appointmentList.setCellFactory(param -> new ListCell<Appointment>() {
			@Override
			protected void updateItem(Appointment item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getDate() + ", " + item.getTime());
				}
			}
		});
	}

	@FXML
	public void loadAppointmentInfo() {
		appointment = appointmentList.getSelectionModel().getSelectedItem();
		modifyAppointmentInfo.setText(appointment.getInfo());

		modifyAppointmentTime.setText(""+appointment.getTime().truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ofPattern("HH.mm")));
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = appointment.getDate().format(dateFormatter);
		LocalDate Date = LocalDate.parse(date, dateFormatter);
		modifyAppointmentDate.setValue(Date);
	}

	@FXML
	public void saveAppointment()  {
		appointment.setTime(modifyAppointmentTime.getText());
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String date = modifyAppointmentDate.getValue().format(dateFormatter);

		appointment.setDate(date);
		appointment.setInfo(modifyAppointmentInfo.getText());
		if(controller.saveAppointment(appointment)) {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.show();
		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCustomerListView();
	}
}
