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
import model.Asiakas;
import model.DAOManager;
import model.Henkilökunta;

/**
 * 
 * Sisäänkirjautumisnäkymän kontrolloimiseen tarkoitettu luokka.
 *
 */

public class LoginViewController {
	
	/**
	 * Olio, jota käytetään muiden data access objectien hallinnoimiseen.
	 */
	private DAOManager daom;

	
	/**
	 * Välilehti henkilökunnan kirjautumiselle
	 */
	@FXML private Tab staffTab;
	
	/**
	 * Tekstikenttä henkilökunnalle salasanan kirjoittamista varten
	 */
	@FXML private PasswordField pw;
	
	/**
	 * Kirjautumisnappi henkilökunnalle
	 */
	@FXML private Button loginBtn;
	
	/**
	 * Tekstikenttä henkilökunnalle käyttäjänimen kirjoittamista varten
	 */
	@FXML private TextField username;
	
	/**
	 * Välilehti asiakkaiden kirjautumiselle
	 */
	@FXML private Tab customerTab;
	
	/**
	 * Tekstikenttä asiakkaan salasanan kirjoittamista varten
	 */
	@FXML private PasswordField pwAsiakas;
	
	/**
	 * Tekstikenttä asiakkaan käyttäjänimen kirjoittamista varten
	 */
	@FXML private TextField usernameAsiakas;
	
	/**
	 * Kirjautumisnappi asiakkaille
	 */
	@FXML private Button loginBtnAsiakas;

	
	/**
	 * Konstruktori LoginController -luokalle.
	 * Luo Data access object -managerin. 
	 */
	public LoginViewController() {	
		daom = new DAOManager();
	}
		
	/**
	 * Toteutetaan kun Henkilökunnan kirjautumisnappia painetaan.
	 * Sisältää myös ylläpitäjän kirjautumisen
	 * 
	 * @param event	Napin klikkaus hiirellä
	 * @throws IOException virhe tapahtuu
	 */
	@FXML
	public void loginStaff(MouseEvent event) throws IOException {
		Henkilökunta hkunta = new Henkilökunta();
		String fxml = "";
		String title = "Login";
		if(getUsernameStaff().equals("admin") && getPasswordStaff().equals("admin")) {
			fxml = "/AdminView.fxml";
			title = "Admin view";
		}
		/*else if(hkunta.getHenkilökuntaID() == Integer.parseInt(getUsername())) {
			fxml = "/StaffView.fxml";
			title = "Staff view";
		}*/else {
			fxml = "/Skene.fxml";			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login failed.");
			alert.setHeaderText(null);
			alert.setContentText("Login failed.");
			alert.showAndWait();
		}
		sceneContent(fxml, event, title);
	}
	
	
	/**
	 * Toteutetaan kun Asiakkaan kirjautumisnappia painetaan
	 * @param event Napin klikkaus hiirellä
	 */
	@FXML
	public void loginCustomer(MouseEvent event) {
		Asiakas asiakas = new Asiakas();
		String fxml = "/AsiakasView.fxml";
		String title = "Welcome!";
		
		if(getUsernameStaff().equals(asiakas.getSposti()) && getPasswordStaff().equals("asiakkaanpassword")) {
			
		}
	}
	
	/**
	 * Vaihtaa skenen sisällön parametrien mukaan
	 * @param fxml Fxml -tiedostom jota käytetään
	 * @param event	Tapahtuma, jonka avulla valitaan seuraava scene
	 * @param title	Ruudun otsikko
	 * @throws IOException virhe tapahtuu
	 */
	public void sceneContent(String fxml, MouseEvent event, String title) throws IOException {
		Pane p = FXMLLoader.load(getClass().getResource(fxml));		
		Scene scene = new Scene(p);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);	
		stage.setTitle(title);
		stage.show();
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
