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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	public void initWeather() {
		Image image = null;
		try {
			WeatherAPICall weather = new WeatherAPICall();
			weatherState.setText(weather.getState());
			weatherCelsius.setText(weather.getCelsius());
			System.out.println(weather.getState());
			switch (weather.getState()) {
			case "Cloudy":
				image = new Image(getClass().getResourceAsStream("/pictures/finland_flag.png"));
				break;
			case "Rain":
				image = new Image(getClass().getResourceAsStream("/pictures/uk_flag.png"));
				break;
			default:
				image = new Image(getClass().getResourceAsStream("/pictures/sweden_flag.png"));
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weatherImageView.setImage(image);
		weatherImageView.setFitHeight(50);
		weatherImageView.setFitWidth(50);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		// welcome.setText(controller.getCustomer().getFirstName());
		setLocations();
		TextFields.bindAutoCompletion(locationField, SuggestionProvider.create(locations));
		initWeather();
	}

	public void setLocations() {
		locations = new ArrayList<>();
		InputStream csvFile = getClass().getResourceAsStream("/kuntaluettelo.csv");
		String line = "";
		String cvsSplitBy = ";";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);
				locations.add(country[1]);
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

}
