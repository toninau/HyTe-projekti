package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.CustomerController;
import controller.CustomerController_IF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class CustomerHomeView extends ViewChanger implements Initializable {

	@FXML
	Button homeButton;
	@FXML
	Button calendarButton;
	@FXML
	Button helpButton;
	@FXML
	Button myHealthButton;
	@FXML
	Slider happinessSlider;
	@FXML
	Label welcome;
	
	private ResourceBundle bundle;
	private CustomerController_IF controller;
	
	public CustomerHomeView() {
		controller = new CustomerController(this);
	}
	
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		//welcome.setText(controller.getCustomer().getFirstName());
	}
}
