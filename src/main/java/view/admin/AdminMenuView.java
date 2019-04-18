package view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;
import view.enums.FxmlEnum;

public class AdminMenuView extends ViewChanger implements Initializable {
	
	@FXML
	Button addStaffButton;	
	@FXML
	Button addCustomerButton;
	@FXML
	Button editStaffButton;
	@FXML
	Button editCustomerButton;
		
	ResourceBundle bundle;
	
	public void changeToAddStaff(MouseEvent event) throws IOException {
		String fxml = FxmlEnum.ADDSTAFF.getFxml();
		String title  = "Lisää henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToEditStaff(MouseEvent event) throws IOException {
		String fxml =  FxmlEnum.EDITSTAFF.getFxml();
		String title  = "Muokkaa henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToAddCustomer(MouseEvent event) throws IOException {
		String fxml = FxmlEnum.ADDCUSTOMER.getFxml();
		String title  = "Lisää asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToEditCustomer (MouseEvent event) throws IOException {
		String fxml = FxmlEnum.EDITCUSTOMER.getFxml();
		String title  = "Muokkaa asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void toolTips() {
		Tooltip addStaffTip = new Tooltip(bundle.getString("addStaffButton.text"));
		Tooltip addCustomerTip = new Tooltip (bundle.getString("addCustomerButton.text"));
		Tooltip editStaffTip = new Tooltip (bundle.getString("editStaffButton.text"));
		Tooltip editCustomerTip = new Tooltip (bundle.getString("editCustomerButton.text"));

		addStaffButton.setTooltip(addStaffTip);
		addCustomerButton.setTooltip(addCustomerTip);
		editStaffButton.setTooltip(editStaffTip);
		editCustomerButton.setTooltip(editCustomerTip);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
		toolTips();
	}
	

	
	
}
