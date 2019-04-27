package view.staff;

import java.net.URL;
import java.util.ResourceBundle;

import controller.StaffController;
import controller.StaffController_IF;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Appointment;
import model.Customer;
import view.ViewChanger;

public class StaffAppointmentView extends ViewChanger implements Initializable {
	
	@FXML ListView<Appointment> appointmentList;
	@FXML ListView<Customer> customerListView;

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
	
	public void toStaffHome(MouseEvent event) {
		toStaffHome(event);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateAppointmentListView();
		populateCustomerListView();
	}
}
