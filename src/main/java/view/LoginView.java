package view;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import controller.LoginController;
import controller.LoginController_IF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Customer;
import model.DAOManager;
import model.Staff;
import view.enums.Bundles;
import view.enums.FxmlEnum;
import javafx.scene.image.Image;

/**
 * 
 * Class for login view.
 *
 */

public class LoginView extends ViewChanger implements Initializable, LoginView_IF {

	@FXML
	private Tab staffTab;
	@FXML
	private PasswordField pwLogin;
	@FXML
	private Button loginBtn;
	@FXML
	private TextField usernameLogin;
	@FXML
	private Tab customerTab;
	@FXML
	private PasswordField pwAsiakasLogin;
	@FXML
	private TextField usernameAsiakasLogin;
	@FXML
	private Button loginBtnAsiakas;
	@FXML
	private ComboBox<Locale> languageChange;
	@FXML
	private ImageView customerUserValidation;
	@FXML private ImageView customerPasswordValidation;
	@FXML private ImageView staffUserValidation;
	@FXML private ImageView staffPasswordValidation;
	
	ObservableList<String> imageList;
	private ResourceBundle bundle;
	private LoginController_IF c;

	/**
	 * Constructor for LoginView -class.
	 */
	public LoginView() {
		c = new LoginController(this);
	}

	/**
	 * Function for staff and admin's login. Fired when login button in Staff -tab
	 * is clicked.
	 * 
	 * @param event Mouse clicked
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginStaff(Event event) throws IOException {
		String fxml = "";
		String title = "Login";
		if (!getUsernameStaff().equals("admin") && !getPasswordStaff().equals("admin")) {
			if (c.checkLoginStaff(getUsernameStaff(), getPasswordStaff())) {
				fxml = FxmlEnum.STAFFHOME.getFxml();
				bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());
				title = "Staff view";
			} else {
				fxml = FxmlEnum.LOGIN.getFxml();
				title = "Login";
			}
		} else {
			fxml = FxmlEnum.ADDCUSTOMER.getFxml();
			title = "Menu";
			bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		}
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Fired when customer's login button is clicked.
	 * 
	 * @param event Button clicked.
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginCustomer(Event event) throws IOException {
		String fxml = FxmlEnum.LOGIN.getFxml();
		String title = "Welcome!";
		loading();
		if (c.checkLoginCustomer(getUsernameCustomer(), getPasswordCustomer())) {	
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
			customerUserValidation.setVisible(true);
			break;
		case "password":
			msg = bundle.getString("loginFailed.password");
			title = bundle.getString("loginFailed.title");
			customerPasswordValidation.setVisible(true);
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
	
	public void inputValidation(KeyEvent event) throws IOException {

		TextField field = (TextField)event.getSource();
		
		if(event.getCode().equals(KeyCode.ENTER)) {
			if(field.getId().equals(usernameAsiakasLogin.getId()) || field.getId().equals(pwAsiakasLogin.getId()))
				loginCustomer(event);
			if(field.equals(pwLogin) || field.equals(usernameLogin))
				loginStaff(event);
		}else {
			if(!event.getCode().isLetterKey() && !event.getCode().isDigitKey() && !event.getCode().equals(KeyCode.BACK_SPACE) && !event.getCode().equals(KeyCode.TAB)) {	
				switch (field.getId()) {
				case "usernameAsiakasLogin":
					customerUserValidation.setVisible(true);
					break;
				case "pwAsiakasLogin":
					customerPasswordValidation.setVisible(true);
					break;
				case  "pwLogin":
					staffPasswordValidation.setVisible(true);
					break;
				case "usernameLogin":
					staffUserValidation.setVisible(true);
					break;
				default:
					break;
				}
			}else {
				customerUserValidation.setVisible(false);
				customerPasswordValidation.setVisible(false);
				staffUserValidation.setVisible(false);
				staffPasswordValidation.setVisible(false);
			}
		}

	}

	/**
	 * Changes the current locale according to combo box value.
	 * @param event Mouse clicked.
	 * @throws IOException Loading the fxml file failed.
	 */
	public void changeLocale(ActionEvent event) throws IOException {
		if (languageChange.getValue() != null) {
			HyteGUI.setLocale(languageChange.getValue());
			logoutForAll(event);
		}
	}

	/**
	 * Set images for combo box.
	 */
	public void languageChangePhotos() {	
		languageChange.setItems(HyteGUI.getSupportedLocales());
		languageChange.setCellFactory(new Callback<ListView<Locale>, ListCell<Locale>>() {
			@Override
			public ListCell<Locale> call(ListView<Locale> param) {
				return new ListCell<Locale>() {

					@Override
					public void updateItem(Locale item, boolean empty) {
						super.updateItem(item, empty);
						Image flag = null;
						String text = "";
						if (item == null || empty) {
							setGraphic(null);
						} else {
							switch (item.getCountry()) {
							case "FI":
								flag = new Image(getClass().getResourceAsStream("/pictures/finland_flag.png"));
								text = "Suomi";
								break;
							case "GB":
								flag = new Image(getClass().getResourceAsStream("/pictures/uk_flag.png"));
								text = "English";
								break;
							case "DE":
								flag = new Image(getClass().getResourceAsStream("/pictures/german_flag.jpg"));
								text = "Deutsch";
								break;
							case "FR":
								flag = new Image(getClass().getResourceAsStream("/pictures/france_flag.png"));
								text = "Français";
								break;
							case "SE":
								flag = new Image(getClass().getResourceAsStream("/pictures/sweden_flag.png"));
								text = "Svenska";
								break;
							case "ES":
								flag = new Image(getClass().getResourceAsStream("/pictures/spain_flag.png"));
								text = "Español";
								break;
							default:
								flag = new Image(getClass().getResourceAsStream("/pictures/finland_flag.png"));
								text ="Finnish";
								break;
							}
							ImageView iconImageView = new ImageView(flag);
							iconImageView.setFitHeight(20);
							iconImageView.setFitWidth(30);
							iconImageView.setPreserveRatio(true);
							setGraphic(iconImageView);
							setText(text);					
						}
					}
				};
			}

		});
	}

	
	/**
	 * Create tooltips for components.
	 */
	public void tooltips() {
		Tooltip usernameTip = new Tooltip(bundle.getString("tooltip.username"));
		Tooltip pwTip = new Tooltip(bundle.getString("tooltip.password"));
		Tooltip loginTip = new Tooltip(bundle.getString("tooltip.login"));
		Tooltip languageTip = new Tooltip(bundle.getString("tooltip.language"));
		usernameAsiakasLogin.setTooltip(usernameTip);
		usernameLogin.setTooltip(usernameTip);
		pwLogin.setTooltip(pwTip);
		pwAsiakasLogin.setTooltip(pwTip);
		loginBtn.setTooltip(loginTip);
		loginBtnAsiakas.setTooltip(loginTip);
		languageChange.setTooltip(languageTip);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), HyteGUI.getLocale());
		//tooltips();
		languageChangePhotos();
		languageChange.setPromptText("Choose a language");
	}

	/**
	 * Return the text written in the employee's user name -field.
	 * 
	 * @return Employee's user name.
	 */
	public String getUsernameStaff() {
		return this.usernameLogin.getText();
	}

	/**
	 * Return the text written in the employee's password -field.
	 * 
	 * @return Employee's password.
	 */
	public String getPasswordStaff() {
		return this.pwLogin.getText();
	}

	/**
	 * Return the text written in the customer's user name -field.
	 * 
	 * @return Customer's user name.
	 */
	public String getUsernameCustomer() {
		return this.usernameAsiakasLogin.getText();
	}

	/**
	 * Return the text written in the customer's password -field.
	 * 
	 * @return Customer's password.
	 */
	public String getPasswordCustomer() {
		return this.pwAsiakasLogin.getText();
	}

}
