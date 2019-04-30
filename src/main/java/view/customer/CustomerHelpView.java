package view.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.CustomerController;
import controller.CustomerController_IF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.ViewChanger;


public class CustomerHelpView extends ViewChanger implements Initializable {

	@FXML
	Button homeButton;
	@FXML
	Button calendarButton;
	@FXML
	Button helpButton;
	@FXML
	Button myHealthButton;	
	@FXML
	Button previousButton;
	@FXML
	Button nextButton;
	@FXML
	Button startButton;
	@FXML
	ImageView slideShow;

	private CustomerController_IF controller;

	public CustomerHelpView() {
		controller = new CustomerController(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}



}
