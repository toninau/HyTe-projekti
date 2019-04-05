package view;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewChanger {
	
	/**
	 * Changes scene's content according to given parameters.
	 * @param fxml Given fxml file.
	 * @param event	Event used to help determine which scene is wanted.
	 * @param title	Stage's title.
	 * @throws IOException Loading the fxml file failed.
	 */
	
	public void sceneContent(String fxml,Event event, String title, ResourceBundle bundle) throws IOException {
		Pane p = FXMLLoader.load(getClass().getResource(fxml), bundle);		
		Scene scene = new Scene(p);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);	
		stage.setTitle(title);
		stage.show();
	}
	
	public void logoutForAll(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), HyteGUI.getLocale());
		String fxml = fxmls.LOGIN.getFxml();
		String title = "Login";	
		sceneContent(fxml, event, title, bundle);
	}
	
	public void toAdminMenu(MouseEvent event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		String fxml = fxmls.ADMINMENU.getFxml();
		String title = "Menu";
		sceneContent(fxml, event, title,bundle);
	}
	
	public void toCustomerHome(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = fxmls.CUSTOMERHOME.getFxml();
		String title = "Home";
		sceneContent(fxml, event, title,bundle);
	}
	
	public void toCustomerCalendar(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = fxmls.CUSTOMERCALENDAR.getFxml();
		String title = "Calendar";
		sceneContent(fxml, event, title,bundle);
	}
	
	public void toCustomerHelp(Event event) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String fxml = fxmls.CUSTOMERHELP.getFxml();
		String title = "Help";
		sceneContent(fxml, event, title,bundle);
	}
}
