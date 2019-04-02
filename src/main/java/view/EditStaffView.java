package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.AdminController;
import controller.AdminController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Staff;

/**
 * Class for editing staff members in database.
 * @author IdaKi
 *
 */
public class EditStaffView extends ViewChanger implements Initializable {

	@FXML 
	private Button search;
	@FXML
	private TextField searchStaff;
	@FXML 
	private Button save;
	@FXML
	private TextField firstName;
	@FXML
	private TextField surname;
	@FXML
	private TextField email;
	@FXML
	private TextField phoneNumber;
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	
	private ArrayList<String> resultSet;

	private AdminController_IF c;
	Staff staff;
	
	public EditStaffView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	public void allStaff() {
		Staff[] staffs = c.findStaffAll();
        for (Staff staff : staffs) {
            resultSet.add(staff.getStaffID() + ", " + staff.getSurname() + ", " + staff.getFirstName());
        }
	}
	
	public void showStaffInfo() {
		String [] split = searchStaff.getText().split(","); ;		
		int before = Integer.parseInt(split[0]);
		staff = c.findStaffWithID(before);
		firstName.setText(staff.getFirstName());
		surname.setText(staff.getSurname());
		email.setText(staff.getEmail());
		phoneNumber.setText(staff.getPhoneNumber());
	}
	
	public void updateStaffInfo() {
		staff.setFirstName(getFirstName());
		staff.setSurname(getSurname());
		staff.setEmail(getEmail());
		staff.setPhoneNumber(getPhoneNumber());
		c.updateStaff(staff);
	}
	
	/**
	 * Changes scene back to Login view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}
	
	/**
	 * Changes scene back to Admin's menu view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void toMenu(MouseEvent event) throws IOException {
		toAdminMenu(event);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allStaff();

		TextFields.bindAutoCompletion(searchStaff, SuggestionProvider.create(resultSet));
		
	}
	
	public String getFirstName() {
		return this.firstName.getText();
	}
	
	public String getSurname() {
		return this.surname.getText();
	}
	
	public String getEmail() {
		return this.email.getText();
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber.getText();
	}
	
	
	
}
