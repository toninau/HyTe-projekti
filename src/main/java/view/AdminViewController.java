package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import model.Asiakas;
import model.AsiakasAccessObject;
import model.DAOManager;
import model.Henkilökunta;
import model.HenkilökuntaAccessObject;

/**
 * Luokka kontrolloi ylläpitäjän näkymää 
 *
 */
public class AdminViewController  implements Initializable  {

	@FXML Tab editTab;	
	@FXML Button logout;
	
	@FXML Tab addTab;
	@FXML TextField fNameStaff; 
	@FXML TextField sNameStaff; 
	@FXML TextField phoneNroStaff; 
	@FXML TextField emailStaff;
	@FXML ChoiceBox<String> profession;
	@FXML Button addCustomer; 
	
	@FXML Button addStaff;
	@FXML TextField hetuCust; 
	@FXML TextField fNameCust; 
	@FXML TextField sNameCust; 
	@FXML TextField phoneNroCust; 
	@FXML TextField emailCust; 
	@FXML TextField addressCust; 
	@FXML TextField ICECust; 

	@FXML Button find;
	@FXML Button findCustomer;
	@FXML TextField staffID;
	@FXML TextField customerID;

	/**
	 * Olio, jota käytetään muiden data access objectien hallinnoimiseen.
	 */
	private DAOManager daoM;

	/**
	 * Ylläpitäjän näkymän konstruktori.
	 * Luo myös data access object managerin.
	 */
	public AdminViewController() {
		daoM = new DAOManager();
		
	}
	
	/**
	 * Metodi asiakkaan luontia varten.
	 * Käynnistyy napin painalluksesta.
	 */
	public void addCustomer() {
		
		Asiakas asiakas = new Asiakas();
		String hetu = getCustHetu();
		String etunimi = getCustFirstname();
		String sukunimi = getCustSurname();
		String puhnro = getCustPhone();
		String email = getCustEmail();
		String ICE = getCustICE();
		String osoite = getCustAddress();
		AsiakasAccessObject ao = daoM.getAsiakasDAO();
		ao.createAsiakas(asiakas);
		String[] info = {etunimi, sukunimi, puhnro, email, hetu,
				ICE, osoite};
		
		for (String string : info) {
			if(string == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
			}
			System.out.println(string);
		}
		
	}
	
	/**
	 * Metodi työntekijän luontia varten.
	 * Käynnistyy napin painalluksesta.
	 */
	public void addStaff() {
		Henkilökunta hkunta = new Henkilökunta();
		String etunimi = getStaffFirstName();
		String sukunimi = getStaffSurname();
		String puhnro = getStaffPhone();
		String email = getStaffEmail();
		String ammatti = getProfession();
		String[] info = {etunimi, sukunimi, puhnro, email, ammatti};
		boolean onnistui = true;
		for (String string : info) {
			if(string.isEmpty()) {
				onnistui = false;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
				break;
			}
			System.out.println(string);
		}

		if (onnistui) {
			hkunta.setEtunimi(etunimi);
			hkunta.setSukunimi(sukunimi);
			hkunta.setPuhnumero(puhnro);
			hkunta.setSposti(email);
			hkunta.setOikeus(ammatti);
			HenkilökuntaAccessObject hdao = daoM.getHenkilökuntaDAO();
			hdao.createHenkilökunta(hkunta);
			}
		Henkilökunta [] kaikki = daoM.getHenkilökuntaDAO().readAll();
		for (Henkilökunta henkilökunta : kaikki) {
			System.out.println(henkilökunta.getEtunimi());
		}
	}
		

	/**
	 * Etsii halutun työntekijän tietokannasta ID:n avulla.
	 */
	public void findStaff() {
		Henkilökunta henk = new Henkilökunta();	
		if(henk.getHenkilökuntaID() != getStaffID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		}else {
			String[] tiedot = {henk.getEtunimi(), henk.getSukunimi(),
					henk.getPuhnumero(), henk.getSposti(), henk.getOikeus()};
				for (String string : tiedot) {
					System.out.println(string);
				}
		}
	}
	
	/**
	 * Etsii halutun asiakkaan tietokannasta ID:n avulla.
	 */
	public void findCustomer() {
		Asiakas asiakas = new Asiakas();	
		if(asiakas.getAsiakasID() != getCustomerID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		}else {
			String[] tiedot = {asiakas.getEtunimi(), asiakas.getSukunimi(),
				asiakas.getHetu(), asiakas.getKotiosoite(), asiakas.getPuhnumero(),
				asiakas.getIcenumero(), asiakas.getSposti()};
					
			for (String string : tiedot) {
				System.out.println(string);
			}
		}	
	}
	
	@FXML
	public void logout() {
		
	}
	
	/**
	 * Palauttaa asiakkaan henkilötunnuksen.
	 * @return Asiakkaan henkilötunnus.
	 */
	public String getCustHetu() {
		return this.hetuCust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan kotiosoitteen.
	 * @return Asiakkaan kotiosoite.
	 */
	public String getCustAddress() {
		return this.addressCust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan lähiomaisen numeron hätätilanteessa.
	 * @return	Asiakkaan lähiomaisen numeron ICE.
	 */
	public String getCustICE() {
		return this.ICECust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan sähköpostiosoitteen.
	 * @return	Asiakkaan sähköpostiosoite.
	 */
	public String getCustEmail() {
		return this.emailCust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan puhelinnumeron.
	 * @return	Asiakkaan puhelinnumero.
	 */
	public String getCustPhone() {
		return this.phoneNroCust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan sukunimen.
	 * @return Asiakkaan sukunimi.
	 */
	public String getCustSurname() {
		return this.sNameCust.getText();
	}
	
	/**
	 * Palauttaa asiakkaan etunimen.
	 * @return	Asiakkaan etunimi.
	 */
	public String getCustFirstname() {
		return this.fNameCust.getText();
	}	
	
	/**
	 * Palauttaa asiakkaan ID:n.
	 * @return	Asiakkaan ID.
	 */
	public int getCustomerID() {
		return Integer.parseInt(this.customerID.getText());
	}
	
	/**
	 * Palauttaa työntekijän ID:n.
	 * @return	Työntekijä ID.
	 */
	public int getStaffID() {
		return Integer.parseInt(this.staffID.getText());
	}	
	
	/**
	 * Palauttaa työntekijän etunimen.
	 * @return	Työnteijän etunimi.
	 */
	public String getStaffFirstName() {
		return this.fNameStaff.getText();
	}
	
	/**
	 * Palauttaa työntekijän sukunimen.
	 * @return	Työntekijän sukunimi.
	 */
	public String getStaffSurname() {
		return this.sNameStaff.getText();
	}
	
	/**
	 * Palauttaa työntekijän puhelinnumeron.
	 * @return	Työntekijän puhelinnumero.
	 */
	public String getStaffPhone() {
		return this.phoneNroStaff.getText();
	}
	
	/**
	 * Palauttaa työntekijän sähköpostiosoitteen.
	 * @return	Työntekijän sähköpostiosoite.
	 */
	public String getStaffEmail() {
		return this.emailStaff.getText();
	}
	
	/**
	 * Palauttaa työntekijän ammatin.
	 * @return	Työntekijän ammatti.
	 */
	public String getProfession() {
		return (String)this.profession.getValue();
	}
	
	/**
	 * Asettaa valintalaatikkoon vaihtoehdot.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		profession.setValue("Lääkäri");
		profession.getItems().add("Lääkäri");
		profession.getItems().add("Hoitaja");
		profession.getItems().add("Asiakaspalvelija");
	}
	
}
