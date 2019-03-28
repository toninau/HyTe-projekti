package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class EditCustomerView extends ViewChanger implements Initializable {

	@FXML
	private TextField findCustomer;
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
	private Button confirmEdit;
	@FXML
	private Button findButton;
	
	private ArrayList<String> resultSet;

	/**
	 * 
	 */
	
	public void logout() {
		
	}
	
	public void toMenu() {
		
	}
	
	public void getCustomers() {
		/*Staff[] staffs = c.findStaffAll();
        for (Staff staff : staffs) {
            resultSet.add(staff.getSurname());
        }*/
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TextFields.bindAutoCompletion(findCustomer, SuggestionProvider.create(resultSet));
	}

}
