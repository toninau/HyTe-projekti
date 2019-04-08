package view;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import controller.LoginController;
import controller.LoginController_IF;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;
import model.DAOManager;
import model.Staff;

/**
 * 
 * Class for login view.
 *
 */

public class LoginView extends ViewChanger implements Initializable, LoginView_IF {

	@FXML private Tab staffTab;	
	@FXML private PasswordField pw;	
	@FXML private Button loginBtn;
	@FXML private TextField username;
	
	@FXML private Tab customerTab;
	@FXML private PasswordField pwAsiakas;
	@FXML private TextField usernameAsiakas;
	@FXML private Button loginBtnAsiakas;

	@FXML private ComboBox<Locale> languageChange;
	
	private ResourceBundle bundle;
	private LoginController_IF c;
	
	/**
	 * Constructor for LoginView -class.
	 * Luo Data access object -managerin. 
	 */
	public LoginView() {	
		c = new LoginController(this);
	}
		
	/**
	 * Function for staff and admin's login.
	 * Fired when login button in Staff -tab is clicked.
	 * 
	 * @param event	Mouse clicked
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginStaff(MouseEvent event) throws IOException {
		String fxml = "";
		String title = "Login";
		if(!getUsernameStaff().equals("admin") && !getPasswordStaff().equals("admin")) {
			if(c.checkLoginStaff()) {
				fxml = FxmlEnum.STAFF.getFxml();
				title = "Staff view";
			}else {
				fxml = FxmlEnum.LOGIN.getFxml();	
				title = "Login";
			}		
		}
		else {
			fxml = FxmlEnum.ADMINMENU.getFxml();
			title = "Menu";
			bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		}
		sceneContent(fxml, event, title, bundle);
	}
	
	
	/**
	 * Fired when customer's login button is clicked.
	 * @param event Button clicked.
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginCustomer(MouseEvent event) throws IOException {
		String fxml = FxmlEnum.LOGIN.getFxml();;
		String title = "Welcome!";

		if (c.checkLoginCustomer()) {
			bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
			fxml = FxmlEnum.CUSTOMERHOME.getFxml();
			title = "Welcome!";
		}
		sceneContent(fxml, event, title, bundle);
	}

	public void loginFailed(String msg) {
		String title;
		switch (msg) {
		case "user":
			msg = bundle.getString("loginFailed.username");
			title = bundle.getString("loginFailed.title");
			break;
		case "password":
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
	
	public void changeLocale(ActionEvent event) throws IOException {
		if(languageChange.getValue() != null) {
			HyteGUI.setLocale(languageChange.getValue());	
			logoutForAll(event);
		}
	}
	
	/**
	 * Return the text written in the employee's user name -field.
	 * @return Employee's user name.
	 */
	public String getUsernameStaff() {
		return this.username.getText();
	}
	
	/**
	 * Return the text written in the employee's password -field.
	 * @return	Employee's password.
	 */
	public String getPasswordStaff() {
		return this.pw.getText();
	}
	
	/**
	 * Return the text written in the customer's user name -field.
	 * @return Customer's user name.
	 */
	public String getUsernameCustomer() {
		return this.usernameAsiakas.getText();
	}
	
	/**
	 * Return the text written in the customer's password -field.
	 * @return Customer's password.
	 */
	public String getPasswordCustomer() {
		return this.pwAsiakas.getText();
	}
	
	/**
	 * Create tooltips for components.
	 */
	public void tooltips() {
		Tooltip usernameTip = new Tooltip(bundle.getString("tooltip.username"));
		Tooltip pwTip = new Tooltip(bundle.getString("tooltip.password"));
		Tooltip loginTip = new Tooltip(bundle.getString("tooltip.login"));
		Tooltip languageTip = new Tooltip(bundle.getString("tooltip.language"));
		usernameAsiakas.setTooltip(usernameTip);
		username.setTooltip(usernameTip);
		pw.setTooltip(pwTip);
		pwAsiakas.setTooltip(pwTip);
		loginBtn.setTooltip(loginTip);
		loginBtnAsiakas.setTooltip(loginTip);
		languageChange.setTooltip(languageTip);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String languageChangePrompt = HyteGUI.getLocale().toString();
		bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), HyteGUI.getLocale());
		tooltips();
		languageChange.setItems(HyteGUI.getSupportedLocales());
		languageChange.setPromptText(languageChangePrompt);
	}
}
