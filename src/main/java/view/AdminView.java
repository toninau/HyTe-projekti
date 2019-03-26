package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.AdminController;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import model.Customer;
import model.DAOManager;
import model.Staff;

/**
 * Luokka kontrolloi ylläpitäjän näkymää
 *
 */
public class AdminView implements Initializable {

	@FXML
	private Tab editTab;
	@FXML
	private Button logout;

	@FXML
	private Tab addTab;
	@FXML
	private TextField fNameStaff;
	@FXML
	private TextField sNameStaff;
	@FXML
	private TextField phoneNroStaff;
	@FXML
	private TextField emailStaff;
	@FXML
	private ChoiceBox<String> profession;
	@FXML
	private Button addCustomer;

	@FXML
	private Button addStaff;
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
	private Button find;
	@FXML
	private Button findCustomer;
	@FXML
	private TextField findStaffName;
	@FXML
	private TextField customerID;

	private AdminController c;
	private SuggestionHandler suggestionHandler;
	

	/**
	 * Consturctor for AdminView. Creates a controller and a suggestionHandler.
	 */
	public AdminView() {
		c = new AdminController(this);
		suggestionHandler = new SuggestionHandler();
	}
	

	/**
	 * Method for creating a customer.
	 */
	public void addCustomer() {
		c.addCustomer();
		fNameCust.clear();
		sNameCust.clear();
		hetuCust.clear();
		phoneNroCust.clear();
		emailCust.clear();
		addressCust.clear();
		ICECust.clear();
	}

	/**
	 * Metodi työntekijän luontia varten. Käynnistyy napin painalluksesta.
	 */
	public void addStaff() {
		c.addStaff();
		fNameStaff.clear();
		sNameStaff.clear();
		emailStaff.clear();
		phoneNroStaff.clear();

	}

	/**
	 * Etsii halutun työntekijän tietokannasta ID:n avulla.
	 */
	public void findStaff() {
		Staff[] staffs = c.findStaffAll();
		suggestionHandler.setStaffLista(staffs);
		System.out.println("findstaffkutsuttu");
	}
	
	public void showStaffSuggestions() {
		ArrayList<Staff> a = suggestionHandler.findWithPrefix(getStaffName());
		
		for (Staff staff : a) {
			//System.out.println(staff.getSurname());
			
		}

		TextFields.bindAutoCompletion(findStaffName, SuggestionProvider.create(a));
		
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

	public String getStaffName() {
		return this.findStaffName.getText();
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
