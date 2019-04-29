package view.admin;

import controller.AdminController;
import controller.AdminController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Staff;
import org.controlsfx.control.textfield.TextFields;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class for editing staff members in database.
 * @author IdaKi
 *
 */
public class EditStaffView extends ViewChanger implements Initializable, EditStaffIF {

	@FXML 
	private Button search;
	@FXML
	private TextField searchStaff;
	@FXML 
	private Button save;
	@FXML
	private Button remove;
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
	private Staff staff;
	ResourceBundle bundle;
	
	public EditStaffView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	/**
	 * Generates an ArrayList for the suggestion provider.
	 */
	public void allFromDatabase() {
		Staff[] staffs = c.findStaffAll();
        for (Staff staff : staffs) {
            resultSet.add(staff.getStaffID() + ", " + staff.getSurname() + ", " + staff.getFirstName());
        }
	}
	
	/**
	 * Shows chosen employee's information from the database in the text fields.
	 */
	public void showInfo() {
		String [] split = searchStaff.getText().split(",");
        String before = split[0];
		staff = c.findStaffWithID(before);
		firstName.setText(staff.getFirstName());
		surname.setText(staff.getSurname());
		email.setText(staff.getStaffID());
		phoneNumber.setText(staff.getPhoneNumber());
	}
	
	/**
	 * Updates the employee's information according to the text in the text fields.
	 */
	public void updateInfo() {
		staff.setFirstName(getFirstName());
		staff.setSurname(getSurname());
		staff.setStaffID(getEmail());
		staff.setPhoneNumber(getPhoneNumber());
		if(c.updateStaff(staff)) {
			clearFields();
		}else {
			alert("update");

		}
	}
	
	/**
	 * Deletes the chosen employee from database.
	 */
	public void removeStaff() {
		if(c.removeStaffFromDatabase(getEmail())){
			clearFields();
		}else {
			alert("remove");
		}
	}
	
	public void clearFields() {
		firstName.clear();
		surname.clear();
		email.clear();
		phoneNumber.clear();
	}
	
	public void alert(String msg) {
		String title;
		switch (msg) {
		case "remove":
			msg = bundle.getString("loginFailed.username");
			title = bundle.getString("loginFailed.title");
			break;
		case "update":
			msg = bundle.getString("loginFailed.password");
			title = bundle.getString("loginFailed.title");
			break;
		default:
			msg = "Login failed.";
			title = "Login failed";
			break;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	
	/**
	 * Changes scene back to Login view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#logoutForAll(Event);
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}
	
	/**
	 * Changes scene back to Admin's menu view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toAdminMenu(MouseEvent);
	 */
	public void toMenu(MouseEvent event) throws IOException {
		toAdminMenu(event);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		allFromDatabase();
		TextFields.bindAutoCompletion(searchStaff, SuggestionProvider.create(resultSet));	
	}
	
	
	
	/**
	 * @return 
	 */
	public String getFirstName() {
		return this.firstName.getText();
	}
	
	/**
	 * 
	 */
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
