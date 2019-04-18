package view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import controller.AdminController;
import controller.AdminController_IF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

/**
 * Class for adding staff members to database.
 * @author IdaKi
 *
 */
public class AddStaffView extends ViewChanger implements Initializable, AddStaffIF{
	
	@FXML
	private TextField fNameStaff;
	@FXML
	private TextField surnameStaff;
	@FXML
	private TextField phoneNroStaff;
	@FXML
	private TextField emailStaff;
	@FXML
	private ComboBox<String> profession;
	@FXML
	private PasswordField passwordStaff;
	@FXML
	private Button addStaff;	
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	
	private AdminController_IF c;

	/**
	 * Constructor for AdminStaffView. Creates a controller.
	 */
	public AddStaffView() {
		c = new AdminController(this);
	}
	
	/**
	 * Fires when a button is clicked. Method for creating an employee.
	 */
	public void addStaff() {
		if(c.addStaff()) {
			fNameStaff.clear();
			surnameStaff.clear();
			emailStaff.clear();
			phoneNroStaff.clear();
			passwordStaff.clear();
		}
	}
	
	public void alert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Tiedot");
		alert.setContentText("Tarkista tiedot");
		alert.showAndWait();
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
	
	/**
	 * Returns the text written in the first name -field.
	 * @return Employee's first name
	 */
	public String getFirstName() {
		return this.fNameStaff.getText();
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Employee's surname
	 */
	public String getSurname() {
		return this.surnameStaff.getText();
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Employee's phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNroStaff.getText();
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Employee's email
	 */
	public String getEmail() {
		return this.emailStaff.getText();
	}

	/**
	 * Returns the choice of the ChoiceBox.
	 * @return Employee's profession
	 */
	public String getProfession() {
		return (String) this.profession.getValue();
	}

	/**
	 * Returns the text written in password -field.
	 * @return Employee's password
	 */
	public String getPassword() {
		return this.passwordStaff.getText();
	}
	
	/**
	 * Sets the ChoiceBox items.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		profession.setPromptText(bundle.getString("profession.choice"));
		profession.getItems().add(bundle.getString("profession.choice.Doctor"));
		profession.getItems().add(bundle.getString("profession.choice.Nurse"));
		profession.getItems().add(bundle.getString("profession.choice.CustServ"));

	}

}
