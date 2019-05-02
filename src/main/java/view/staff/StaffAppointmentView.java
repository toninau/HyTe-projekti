package view.staff;

import controller.StaffController;
import controller.StaffController_IF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Appointment;
import model.Customer;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

import java.net.URL;
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




	StaffController_IF controller;
	
	public StaffAppointmentView() {
		controller = new StaffController(this);
	}
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());
		//populateAppointmentListView();
		//populateCustomerListView();
	}
}
