package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddCustomerView extends ViewChanger implements Initializable {

	@FXML
	private Button addCustomer;
	@FXML
	private TextField hetuCust;
	@FXML
	private TextField fNameCust;
	@FXML
	private TextField sNameCust;
	@FXML
	private TextField phoneNroCust;
	@FXML
	private TextField emailCust;
	@FXML
	private TextField addressCust;
	@FXML 
	private TextField ICECust; 
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	
	/**
	 * Method for creating a customer.
	 */
	public void addCustomer() {
		//c.addCustomer();
		fNameCust.clear();
		sNameCust.clear();
		hetuCust.clear();
		phoneNroCust.clear();
		emailCust.clear();
		addressCust.clear();
		ICECust.clear();
	}
	
	public void logout(MouseEvent event) throws IOException {
		String fxml = "/LoginView.fxml";
		String title = "Login";
		sceneContent(fxml, event, title);
	}
	
	public void toMenu(MouseEvent event) throws IOException {
		String fxml = "/AdminMenuView.fxml";
		String title = "Menu";
		sceneContent(fxml, event, title);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
