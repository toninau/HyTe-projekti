package view.customer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.controlsfx.control.CheckListView;
import org.controlsfx.control.textfield.TextFields;

import controller.CustomerController;
import controller.CustomerController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import model.Appointment;
import model.Prescription;
import model.UserImage;
import model.WeatherAPICall;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class CustomerHomeView extends ViewChanger implements Initializable {

	@FXML private Button homeButton;
	@FXML private Button calendarButton;
	@FXML private Button helpButton;
	@FXML private Button myHealthButton;
	@FXML private Label weatherCelsius;
	@FXML private TextField locationField;
	@FXML private ImageView weatherImageView;
	@FXML private Label weatherState;
	@FXML private Slider happinessSlider;
	@FXML private Label welcome;
	@FXML private ListView<String> appointmentReminder;
	@FXML private HBox prescriptionBox;
	@FXML private ImageView imageMe;
	@FXML private ImageView imageSecond;
	@FXML private ImageView imageThird;

	private CheckListView<String> checkListView;

	private WeatherAPICall weather;
	private ResourceBundle bundle;
	private CustomerController_IF controller;

	public CustomerHomeView() {
		controller = new CustomerController(this);
	}

	public void selectImage(ImageView imageView, int imageSlot, String action) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Profile Picture");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			File f = new File(selectedFile.getAbsolutePath());
			String url;
			try {
				url = f.toURI().toURL().toString();
				imageView.setImage(new Image(url));
				if (action.equals("update")) {
				
					controller.updateImage(selectedFile, imageSlot);
				} else {
					controller.imageToDatabase(selectedFile, imageSlot);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void selectImageMe() {
		String action;
		if(imageMe.getImage() != null) {
			action = "update";
		}else {
			action = "create";
		}
		selectImage(imageMe, 1, action);
	}

	public void selectSecondImage() {
		String action;
		if(imageSecond.getImage() != null) {
			action = "update";
		}else {
			action = "create";
		}
		selectImage(imageSecond, 2, action);
	}

	public void selectThirdImage() {
		String action;
		if(imageThird.getImage() != null) {
			action = "update";
		}else {
			action = "create";
		}
		selectImage(imageThird, 3, action);
	}

	public void showImage() {
		UserImage[] a = controller.imageFromDatabase();
		if(a != null) {
			BufferedImage img = null;
			ImageView imagev ;
			for (UserImage userImage : a) {
				switch (userImage.getImageName().charAt(userImage.getImageName().length()-1)) {
				case '1':
					imagev = imageMe;
					break;
				case '2':
					imagev = imageSecond;
					break;
				case '3':
					imagev = imageThird;
					break;
				default:
					imagev = imageMe;
					break;
				}
				try {
					img = ImageIO.read(new ByteArrayInputStream(userImage.getImage()));
					Image image = SwingFXUtils.toFXImage(img, null);
					imagev.setImage(image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			
		}
	}

	/*
	 * public void showImage() { byte[] imageMeFile =
	 * controller.imageFromDatabase()[0].getImage(); if(imageMeFile != null) {
	 * BufferedImage img = null; try { img = ImageIO.read(new
	 * ByteArrayInputStream(imageMeFile)); Image image = SwingFXUtils.toFXImage(img,
	 * null); imageMe.setImage(image); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }else {
	 * 
	 * }
	 * 
	 * }
	 */

	/**
	 * Generates a list view with check boxes.
	 */
	public void showPrescription() {
		checkListView = new CheckListView<>(prescriptionsList());
		checkListView.getStylesheets().add(getClass().getResource("/css/view.css").toExternalForm());
		checkListView.getStyleClass().add("checkListView");
		prescriptionBox.getChildren().add(checkListView);
		checkListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				System.out.println(checkListView.getCheckModel().getCheckedItems());

			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public ObservableList<String> prescriptionsList() {
		ObservableList<String> data = FXCollections.observableArrayList();
		Prescription[] a = controller.prescriptions();
		for (Prescription prescription : a) {
			if (prescription.getTimeToTake().equalsIgnoreCase("aamu")) {
				// && LocalTime.now().isBefore(LocalTime.NOON)
				data.add(prescription.getTimeToTake() + ", " + prescription.getDosage() + ", "
						+ prescription.getPrescriptionName());
			} else {
				data.add("eilääkkeit");
			}
		}
		return data;
	}

	public void showAppointments() {
		appointmentReminder.getItems().addAll(appointmentList());
	}

	public ObservableList<String> appointmentList() {
		ObservableList<String> data = FXCollections.observableArrayList();
		Appointment[] appointments = controller.customersAppointments();
		for (Appointment a : appointments) {
			System.out.println(LocalDate.now().toString());
			if (a.getDate().equalsIgnoreCase(getDate())) {
				data.add(a.getInfo() + " kello " + a.getTime());
			} else {
				data.add("no appointments for today");
			}
		}
		return data;
	}

	/**
	 * Fired when location is chosen and button is pressed.
	 * 
	 * @param event Mouse clicked.
	 */
	public void updateLocationClicked(Event event) {
		String[] loc = locationField.getText().split(",");
		showWeather(loc[0]);
	}

	/**
	 * Checks if the key pressed is enter, if yes updates the location.
	 * 
	 * @param event Key pressed.
	 */
	public void updateLocation(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			updateLocationClicked(event);
		}
	}

	/**
	 * Method for showing current weather of the wanted location.
	 * 
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
		// showWeather(loc);
		showPrescription();
		showAppointments();
		showImage();

	}

	/**
	 * Fired when Home button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHome(Event);
	 */
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	/**
	 * Fired when Calendar button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerCalendar(Event);
	 */
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	/**
	 * Fired when Help button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHelp(Event);
	 */
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	/**
	 * Fired when Health button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHealth(Event);
	 */
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	/**
	 * Fired when logout button is clicked.
	 * 
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
