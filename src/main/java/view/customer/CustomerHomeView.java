package view.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;
import org.controlsfx.control.textfield.TextFields;

import controller.CustomerController;
import controller.CustomerController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Appointment;
import model.Prescription;
import model.WeatherAPICall;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class CustomerHomeView extends ViewChanger implements Initializable {

	@FXML
	Button homeButton;
	@FXML
	Button calendarButton;
	@FXML
	Button helpButton;
	@FXML
	Button myHealthButton;
	@FXML
	Label weatherCelsius;
	@FXML
	TextField locationField;
	@FXML
	ImageView weatherImageView;
	@FXML
	Label weatherState;
	@FXML
	Slider happinessSlider;
	@FXML
	Label welcome;
	@FXML
	ListView<String> appointmentReminder;
	@FXML
	HBox prescriptionBox;
	
	CheckListView<String> checkListView;
	
	WeatherAPICall weather;
	private ResourceBundle bundle;
	private CustomerController_IF controller;
	private ArrayList<String> locations;

	public CustomerHomeView() {
		controller = new CustomerController(this);
	}

	public void showPrescription() {
		checkListView = new CheckListView<>(prescriptionsList());
		prescriptionBox.getChildren().add(checkListView);
		checkListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		         System.out.println(checkListView.getCheckModel().getCheckedItems());
		     }
		 });
	}

	public ObservableList<String> prescriptionsList() {
		ObservableList<String> data = FXCollections.observableArrayList();
		Prescription [] a = controller.prescriptions();
		for (Prescription prescription : a) {
			if (prescription.getTimeToTake().equalsIgnoreCase("aamu")) {
				data.add(prescription.getDosage() + ", " + prescription.getPrescriptionName());
			}else {
				data.add("eilääkkeit");
			}
		}	
		return data;
	}
	
	public void showAppointments() {
		appointmentReminder.getItems().addAll(appointmentList());
	}
	
	public ObservableList<String> appointmentList(){
		ObservableList<String> data = FXCollections.observableArrayList();
		Appointment[] appointments = controller.customersAppointments();
		for (Appointment a : appointments) {
			System.out.println(LocalDate.now().toString());
			if (a.getDate().equalsIgnoreCase(getDate())) {
				data.add(a.getInfo() + " kello " + a.getTime());
			}else {
				data.add("no appointments for today");
			}
		}	
		return data;
	}
	

	
	/**
	 * Fired when location is chosen and button is pressed.
	 * @param event Mouse clicked.
	 */
	public void updateLocationClicked(Event event) {
		String[] loc = locationField.getText().split(",");
		showWeather(loc[0]);
	}
	/**
	 * Checks if the key pressed is enter, if yes updates the location.
	 * @param event Key pressed.
	 */
	public void updateLocation(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			updateLocationClicked(event);
		}
	}

	/**
	 * Method for showing current weather of the wanted location.
	 * @param location Wanted location for weather.
	 */
	public void showWeather(String location) {
		Image image = null;

		locationField.setText(location);
		try {
			weather = new WeatherAPICall(location);
			weatherState.setText(weather.getState());
			weatherCelsius.setText(weather.getCelsius() + "\u00b0C");
			switch (weather.getState()) {
			case "Clouds":
				image = new Image(getClass().getResourceAsStream("/pictures/finland_flag.png"));
				weatherState.setText(bundle.getString("weather.clouds"));
				break;
			case "Rain":
				image = new Image(getClass().getResourceAsStream("/pictures/uk_flag.png"));
				weatherState.setText(bundle.getString("weather.rain"));
				break;
			case "Clear":
				image = new Image(getClass().getResourceAsStream("/pictures/spain_flag.png"));
				weatherState.setText(bundle.getString("weather.sunny"));
				break;
			case "Haze":
				image = new Image(getClass().getResourceAsStream("/pictures/spain_flag.png"));
				weatherState.setText(bundle.getString("weather.sunny"));
				break;
			default:
				image = new Image(getClass().getResourceAsStream("/pictures/sweden_flag.png"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		weatherImageView.setImage(image);
		weatherImageView.setFitHeight(50);
		weatherImageView.setFitWidth(50);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		String loc = bundle.getString("weather.defaultLocation");
		String welcomeText = bundle.getString("welcome.morning");
		welcome.setText(welcomeText + " " + controller.getCustomer().getFirstName());
		TextFields.bindAutoCompletion(locationField, SuggestionProvider.create(controller.locationSuggestions()));
		//showWeather(loc);
		showPrescription();
		showAppointments();

	}


	

	/**
	 * Fired when Home button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHome(Event);
	 */
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	/**
	 * Fired when Calendar button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerCalendar(Event);
	 */
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	/**
	 * Fired when Help button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHelp(Event);
	 */
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	/**
	 * Fired when Health button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHealth(Event);
	 */
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	/**
	 * Fired when logout button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#logoutForAll(Event);
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}
}
