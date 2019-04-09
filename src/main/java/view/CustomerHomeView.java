package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.CustomerController;
import controller.CustomerController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Prescription;
import model.WeatherAPICall;

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

	private ResourceBundle bundle;
	private CustomerController_IF controller;
	private ArrayList<String> locations;

	public CustomerHomeView() {
		controller = new CustomerController(this);
	}

	public void showPrescription() {
		Prescription[] pr = controller.prescriptions();
		for (Prescription p : pr) {
			if (p.getPrescriptionGuide().contains("aamu") && LocalTime.now().isBefore(LocalTime.NOON)) {

			}
		}

	}

	public void updateLocationClicked(Event event) {
		String[] loc = locationField.getText().split(",");
		initWeather(loc[0]);
	}
	public void updateLocation(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			updateLocationClicked(event);
		}
	}

	public void initWeather(String location) {
		Image image = null;
		WeatherAPICall weather;
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
			case "Sun":
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
		// welcome.setText(controller.getCustomer().getFirstName());
		locationSuggestions();
		TextFields.bindAutoCompletion(locationField, SuggestionProvider.create(locations));
		//initWeather(loc);
	}

	public void locationSuggestions() {
		locations = new ArrayList<>();
		InputStream csvFile = null;
		String line = "";
		String cvsSplitBy = "";
		int col1 = 0, col2 = 0;
		switch (HyteGUI.getLocale().getCountry()) {
		case "FI":
			csvFile = getClass().getResourceAsStream("/cityLists/kuntaluettelo.csv");
			cvsSplitBy = ";";
			col1 = 1;
			col2 = 15;
			break;
		case "GB":
			csvFile = getClass().getResourceAsStream("/cityLists/Towns_List.csv");
			cvsSplitBy = ",";
			col1 = 0;
			col2 = 1;
			break;
		default:
			csvFile = getClass().getResourceAsStream("/cityLists/kuntaluettelo.csv");
			cvsSplitBy = ";";
			col1 = 1;
			col2 = 15;
			break;
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] city = line.split(cvsSplitBy);
				locations.add(city[col1] + ", " + city[col2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

}
