package view;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;
import model.DAOManager;
import model.Staff;

/**
 * 
 * Sisäänkirjautumisnäkymän kontrolloimiseen tarkoitettu luokka.
 *
 */

public class LoginView extends ViewChanger {

	private DAOManager daom;

	@FXML private Tab staffTab;	
	@FXML private PasswordField pw;	
	@FXML private Button loginBtn;
	@FXML private TextField username;
	
	@FXML private Tab customerTab;
	@FXML private PasswordField pwAsiakas;
	@FXML private TextField usernameAsiakas;
	@FXML private Button loginBtnAsiakas;

	
	/**
	 * Constructor for LoginView -class.
	 * Luo Data access object -managerin. 
	 */
	public LoginView() {	
		//daom = new DAOManager();
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
		Staff hkunta = new Staff();
		String fxml = "";
		String title = "Login";
		if(getUsernameStaff().equals("admin") && getPasswordStaff().equals("admin")) {
			fxml = "/AdminMenuView.fxml";
			title = "Menu";
		}
		/*else if(hkunta.getHenkilökuntaID() == Integer.parseInt(getUsername())) {
			fxml = "/StaffView.fxml";
			title = "Staff view";
		}*/else {
			fxml = "/LoginView.fxml";			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login failed.");
			alert.setHeaderText(null);
			alert.setContentText("Login failed.");
			alert.showAndWait();
		}
		sceneContent(fxml, event, title);
	}
	
	
	/**
	 * Fired when customer's login button is clicked.
	 * @param event Napin klikkaus hiirellä
	 * @throws IOException virhe tapahtuu
	 */
	@FXML
	public void loginCustomer(MouseEvent event) throws IOException {
		Customer customer = new Customer();
		String fxml = "/AsiakasView.fxml";
		String title = "Welcome!";
		sceneContent(fxml, event, title);
		if(getUsernameStaff().equals(customer.getEmail()) && getPasswordStaff().equals("asiakkaanpassword")) {
			
		}
	}
	

	
	/**
	 * Palauttaa henkilökunnan käyttäjätunnus -kenttään kirjoitetun tiedon
	 * @return Työntekijän käyttäjätunnus
	 */
	public String getUsernameStaff() {
		return this.username.getText();
	}
	
	/**
	 * Palauttaa henkilökunnan salasana -kenttään kirjoitetun tiedon
	 * @return	Työntekijän salasana
	 */
	public String getPasswordStaff() {
		return this.pw.getText();
	}
	
	/**
	 * Palauttaa asiakkaan käyttäjätunnus -kenttään kirjoitetun tiedon
	 * @return Asiakkaan käyttäjätunnus
	 */
	public String getUsernameCustomer() {
		return this.usernameAsiakas.getText();
	}
	
	/**
	 * Palauttaa asiakkaan salasana -kenttään kirjoitetun tiedon
	 * @return Asiakkaan salasana
	 */
	public String getPasswordCustomer() {
		return this.pwAsiakas.getText();
	}
}
