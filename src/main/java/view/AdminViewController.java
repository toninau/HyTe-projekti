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
import model.Customer;
import model.CustomerDAO;
import model.DAOManager;
import model.Staff;
import model.StaffDAO;

/**
 * Luokka kontrolloi ylläpitäjän näkymää
 *
 */
public class AdminViewController implements Initializable {

	@FXML
	Tab editTab;
	@FXML
	Button logout;

	@FXML
	Tab addTab;
	@FXML
	TextField fNameStaff;
	@FXML
	TextField sNameStaff;
	@FXML
	TextField phoneNroStaff;
	@FXML
	TextField emailStaff;
	@FXML
	ChoiceBox<String> profession;
	@FXML
	Button addCustomer;

	@FXML
	Button addStaff;
	@FXML
	TextField hetuCust;
	@FXML
	TextField fNameCust;
	@FXML
	TextField sNameCust;
	@FXML
	TextField phoneNroCust;
	@FXML
	TextField emailCust;
	@FXML
	TextField addressCust;
	@FXML
	TextField ICECust;

	@FXML
	Button find;
	@FXML
	Button findCustomer;
	@FXML
	TextField staffID;
	@FXML
	TextField customerID;

	/**
	 * Olio, jota käytetään muiden data access objectien hallinnoimiseen.
	 */
	private DAOManager daoM;

	/**
	 * Ylläpitäjän näkymän konstruktori. Luo myös data access object managerin.
	 */
	public AdminViewController() {
		daoM = new DAOManager();

	}

	/**
	 * Metodi asiakkaan luontia varten. Käynnistyy napin painalluksesta.
	 */
	public void addCustomer() {

		Customer customer = new Customer();
		String hetu = getCustHetu();
		String etunimi = getCustFirstname();
		String sukunimi = getCustSurname();
		String puhnro = getCustPhone();
		String email = getCustEmail();
		String ICE = getCustICE();
		String osoite = getCustAddress();
		CustomerDAO ao = daoM.getAsiakasDAO();
		ao.create(customer);
		String[] info = { etunimi, sukunimi, puhnro, email, hetu, ICE, osoite };

		for (String string : info) {
			if (string == null) {
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
	 * Metodi työntekijän luontia varten. Käynnistyy napin painalluksesta.
	 */
	public void addStaff() {
		Staff hkunta = new Staff();
		String etunimi = getStaffFirstName();
		String sukunimi = getStaffSurname();
		String puhnro = getStaffPhone();
		String email = getStaffEmail();
		String ammatti = getProfession();
		String[] info = { etunimi, sukunimi, puhnro, email, ammatti };
		boolean onnistui = true;
		for (String string : info) {
			if (string.isEmpty()) {
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
			hkunta.setFirstName(etunimi);
			hkunta.setSurname(sukunimi);
			hkunta.setPhoneNumber(puhnro);
			hkunta.setEmail(email);
			hkunta.setAccessLevel(ammatti);
			StaffDAO hdao = daoM.getHenkilökuntaDAO();
			hdao.create(hkunta);
		}
		Staff[] kaikki = daoM.getHenkilökuntaDAO().readAll();
		for (Staff staff : kaikki) {
			System.out.println(staff.getFirstName());
		}
	}

	/**
	 * Etsii halutun työntekijän tietokannasta ID:n avulla.
	 */
	public void findStaff() {
		Staff henk = new Staff();
		if (henk.getStaffID() != getStaffID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		} else {
			String[] tiedot = { henk.getFirstName(), henk.getSurname(), henk.getPhoneNumber(), henk.getEmail(),
					henk.getAccessLevel() };
			for (String string : tiedot) {
				System.out.println(string);
			}
		}
	}

	/**
	 * Etsii halutun asiakkaan tietokannasta ID:n avulla.
	 */
	public void findCustomer() {
		Customer customer = new Customer();
		if (customer.getCustomerID() != getCustomerID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		} else {
			String[] tiedot = { customer.getFirstName(), customer.getSurname(), customer.getSSN(),
					customer.getAddress(), customer.getPhoneNumber(), customer.getIceNumber(), customer.getEmail() };

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
	 * 
	 * @return Asiakkaan henkilötunnus.
	 */
	public String getCustHetu() {
		return this.hetuCust.getText();
	}

	/**
	 * Palauttaa asiakkaan kotiosoitteen.
	 * 
	 * @return Asiakkaan kotiosoite.
	 */
	public String getCustAddress() {
		return this.addressCust.getText();
	}

	/**
	 * Palauttaa asiakkaan lähiomaisen numeron hätätilanteessa.
	 * 
	 * @return Asiakkaan lähiomaisen numeron ICE.
	 */
	public String getCustICE() {
		return this.ICECust.getText();
	}

	/**
	 * Palauttaa asiakkaan sähköpostiosoitteen.
	 * 
	 * @return Asiakkaan sähköpostiosoite.
	 */
	public String getCustEmail() {
		return this.emailCust.getText();
	}

	/**
	 * Palauttaa asiakkaan puhelinnumeron.
	 * 
	 * @return Asiakkaan puhelinnumero.
	 */
	public String getCustPhone() {
		return this.phoneNroCust.getText();
	}

	/**
	 * Palauttaa asiakkaan sukunimen.
	 * 
	 * @return Asiakkaan sukunimi.
	 */
	public String getCustSurname() {
		return this.sNameCust.getText();
	}

	/**
	 * Palauttaa asiakkaan etunimen.
	 * 
	 * @return Asiakkaan etunimi.
	 */
	public String getCustFirstname() {
		return this.fNameCust.getText();
	}

	/**
	 * Palauttaa asiakkaan ID:n.
	 * 
	 * @return Asiakkaan ID.
	 */
	public int getCustomerID() {
		return Integer.parseInt(this.customerID.getText());
	}

	/**
	 * Palauttaa työntekijän ID:n.
	 * 
	 * @return Työntekijä ID.
	 */
	public int getStaffID() {
		return Integer.parseInt(this.staffID.getText());
	}

	/**
	 * Palauttaa työntekijän etunimen.
	 * 
	 * @return Työnteijän etunimi.
	 */
	public String getStaffFirstName() {
		return this.fNameStaff.getText();
	}

	/**
	 * Palauttaa työntekijän sukunimen.
	 * 
	 * @return Työntekijän sukunimi.
	 */
	public String getStaffSurname() {
		return this.sNameStaff.getText();
	}

	/**
	 * Palauttaa työntekijän puhelinnumeron.
	 * 
	 * @return Työntekijän puhelinnumero.
	 */
	public String getStaffPhone() {
		return this.phoneNroStaff.getText();
	}

	/**
	 * Palauttaa työntekijän sähköpostiosoitteen.
	 * 
	 * @return Työntekijän sähköpostiosoite.
	 */
	public String getStaffEmail() {
		return this.emailStaff.getText();
	}

	/**
	 * Palauttaa työntekijän ammatin.
	 * 
	 * @return Työntekijän ammatti.
	 */
	public String getProfession() {
		return (String) this.profession.getValue();
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
