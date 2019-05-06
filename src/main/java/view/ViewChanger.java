package view;

import java.io.IOException;

import java.util.ResourceBundle;


import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.enums.Bundles;
import view.enums.FxmlEnum;

/**
 * The <code> ViewChanger</code> class is a class that provides scene changing
 * methods for its subclasses.
 * 
 * @author IdaKi
 *
 */
public class ViewChanger {

	private Stage loadStage;

	/**
	 * Changes scene's content according to given parameters.
	 * 
	 * @param fxml   Given fxml file.
	 * @param event  Event used to help determine which scene is wanted.
	 * @param title  Stage's title.
	 * @param bundle Given ResourceBundle.
	 */
	public void sceneContent(String fxml, Event event, String title, ResourceBundle bundle) {
		try {
			Pane p = FXMLLoader.load(getClass().getResource(fxml), bundle);
			Scene scene = new Scene(p);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle(title);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/ICON.png")));
			stage.show();

		} catch (IOException e) {
		}
		loaded();
	}

	public Stage loading() {
		loadStage = new Stage();
		BorderPane pane = new BorderPane();
		ImageView iv = new ImageView();
		pane.setStyle("-fx-background-color:  #cfe0fc");
		iv.setImage(new Image(getClass().getResource("/pictures/logo_1.png").toExternalForm()));
		iv.setFitWidth(720);
		iv.setFitHeight(720);
		pane.setCenter(iv);
		loadStage.setTitle("Welcome");
		Scene s = new Scene(pane, 1280, 720);
		loadStage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/ICON.png")));
		loadStage.setScene(s);
		loadStage.show();
		return loadStage;
	}

	public void loaded() {
		if (loadStage != null)
			loadStage.hide();
	
	}

	/**
	 * Logout method for all views. Opens login view.
	 * 
	 * @param event Mouse clicked.
	 */
	public void logoutForAll(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.LOGIN.getFxml();
		String title = "Login";
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Method for all admin views. Returns user to admin menu view.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException
	 */
	public void toAdminMenu(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.ADMINMENU.getFxml();
		String title = "Menu";
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Method for all customer's views for changing scene to customer's home view.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void toCustomerHome(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.CUSTOMERHOME.getFxml();
		String title = "Home";
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Method for all customer's views for changing the scene to customer's calendar
	 * view.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void toCustomerCalendar(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.CUSTOMERCALENDAR.getFxml();
		String title = "Calendar";
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Method for all customer's views for changing the scene to customer's help
	 * view.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void toCustomerHelp(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.CUSTOMERHELP.getFxml();
		String title = "Help";
		sceneContent(fxml, event, title, bundle);
	}

	/**
	 * Method for all customer's views for changing the scene to customer's health
	 * view.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void toCustomerHealth(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.CUSTOMERHEALTH.getFxml();
		String title = "Health";
		sceneContent(fxml, event, title, bundle);
	}

	public void toStaffHome(Event event) {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.STAFFHOME.getFxml();
		String title = "Welcome";
		sceneContent(fxml, event, title, bundle);
	}

	public void toStaffAppointment(Event event) {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.STAFF.getBundleName(), HyteGUI.getLocale());
		String fxml = FxmlEnum.STAFFAPPOINTMENT.getFxml();
		String title = "Appointment";
		sceneContent(fxml, event, title, bundle);
	}

	public void changeToAddStaff(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		String fxml = FxmlEnum.ADDSTAFF.getFxml();
		String title = "Lisää henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}

	public void changeToEditStaff(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		String fxml = FxmlEnum.EDITSTAFF.getFxml();
		String title = "Muokkaa henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}

	public void changeToAddCustomer(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		String fxml = FxmlEnum.ADDCUSTOMER.getFxml();
		String title = "Lisää asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}

	public void changeToEditCustomer(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		String fxml = FxmlEnum.EDITCUSTOMER.getFxml();
		String title = "Muokkaa asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}

}
