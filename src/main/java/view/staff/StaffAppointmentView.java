package view.staff;

import controller.StaffController;
import controller.StaffControllerIF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Appointment;
import model.Customer;
import model.Notification;
import model.Prescription;
import view.ViewChanger;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class StaffAppointmentView extends ViewChanger implements Initializable {
	
	@FXML ListView<Appointment> appointmentList;
	@FXML ListView<Prescription> prescriptionList;
	@FXML ListView<Customer> customerListView;
	@FXML Tab prescriptionTab;
	@FXML HBox prescriptionBox;

    @FXML TextField appointmentInfo;
    @FXML TextField modifyAppointmentInfo;

    @FXML TextField appointmentTime;
    @FXML TextField modifyAppointmentTime;

    @FXML DatePicker appointmentDate;
    @FXML DatePicker modifyAppointmentDate;
    @FXML DatePicker startDate;
    @FXML DatePicker endDate;

    @FXML Button addAppointment;
    @FXML Button modifyAppointment;
    @FXML Button loadCustomers;
    @FXML Button logout;
    @FXML Button sendNotification;
    @FXML Button prescriptionSaveButton;
    @FXML Button prescriptionLoadButton;

    @FXML Text loadedCustomerName;
	@FXML TextArea notificationTextArea;

	@FXML TextArea prescriptionName;
	@FXML TextArea prescriptionUsage;
	@FXML TextArea prescriptionDescription;
	@FXML TextField prescriptionTimeToTake;

	private static final String ERROR = "Error";
	private static final String PATTERN = "dd.MM.yyyy";

	private Appointment appointment;
	private Customer customer;
	private StaffControllerIF controller;

	/**
	 * Constructor. Creates the controller for methods
	 */
	public StaffAppointmentView() { controller = new StaffController(this); }

	/**
	 * Populates the staff members ListView with their customers using the controllers getStaffCustomers method
	 */
	public void populateCustomerListView() {
		customerListView.getItems().addAll(controller.getStaffCustomers());
		customerListView.setCellFactory(param -> new ListCell<Customer>() {
		    @Override
		    protected void updateItem(Customer item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty || item == null) {
		            setText(null);
		        } else {
		            setText(item.getSurname() + ", " + item.getFirstName());
		        }
		    }
		});

	}

	/**
	 * Sends an error alert to the user
	 */
	public void loadCustomerError() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(ERROR);
		alert.setHeaderText(ERROR);
		alert.setContentText("Please load the customer first");
		alert.show();
	}

	/**
	 * Gets the information from the fields and uses the controllers addAppointment method
	 */
	@FXML
	public void addAppointment()  {
		Appointment app = new Appointment();
		app.setStaff(controller.getLoggedStaff());
		app.setInfo(appointmentInfo.getText());
		if (customer == null) {
			loadCustomerError();
		}
		else { app.setCustomer(customer); }
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN);
		String date = appointmentDate.getValue().format(dateFormatter);
		app.setDate(date);
		if (appointmentTime.getText().contains(":") && !appointmentTime.getText().isEmpty()) {
			app.setTime(appointmentTime.getText());
			if (controller.addAppointment(app)) {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.show();
			}
		} else {
			timeIsWrong();
		}


	}

	/**
	 * Gets the chosen prescription from the prescriptionList ListView and adds the data to their appropriate fields
	 */
	@FXML
	public void loadCustomersPrescription() {
		Prescription prescription = prescriptionList.getSelectionModel().getSelectedItem();
		prescriptionBox.setVisible(true);
		if (prescription!=null) {
			prescriptionName.setText(prescription.getPrescriptionName());
			prescriptionUsage.setText(prescription.getDosage());
			prescriptionTimeToTake.setText(prescription.getTimeToTake());
			prescriptionDescription.setText(prescription.getPrescriptionGuide());
			startDate.setValue(prescription.getStartDate());
			endDate.setValue(prescription.getEndDate());
		}

	}

	/**
	 * Saves the chosen prescription objects data to the database
	 * If a prescription is not chosen, a new prescription object is created and saved to the database
	 */
	@FXML
	public void savePrescription() {
		Prescription prescription = prescriptionList.getSelectionModel().getSelectedItem();
		if (prescription==null) {
			prescription = new Prescription();
			prescription.setCustomer(customer);
		}
		try {
			prescription.setPrescriptionName(prescriptionName.getText());
			prescription.setDosage(prescriptionUsage.getText());
			prescription.setPrescriptionGuide(prescriptionDescription.getText());
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN);
			String start = startDate.getValue().format(dateFormatter);
			String end = endDate.getValue().format(dateFormatter);
		if (prescriptionTimeToTake.getText().contains(":") && !prescriptionTimeToTake.getText().isEmpty()) {
			prescription.setTimeToTake(prescriptionTimeToTake.getText());
			prescription.setStartDate(start);
			prescription.setEndDate(end);
		}
		} catch (NullPointerException e) {
			fieldsAreEmpty();
		}
		if(controller.savePrescription(prescription)) {
			Alert b = new Alert(Alert.AlertType.CONFIRMATION);
			b.show();
		} else {
			timeIsWrong();
		}
	}

	public void fieldsAreEmpty() {
		Alert a = new Alert(Alert.AlertType.ERROR);
		a.setTitle(ERROR);
		a.setContentText("Some fields are empty");
		a.show();
	}

	public void timeIsWrong() {
		Alert a = new Alert(Alert.AlertType.ERROR);
		a.setTitle(ERROR);
		a.setContentText("You have written the time wrong, the correct style is XX:XX");
		a.show();
	}

	/**
	 * Hides the prescription box when the tab is closed
	 */
	@FXML
	public void hidePrescriptionBox() {
		prescriptionBox.setVisible(false);
	}



	/**
	 * Loads the customers appointments and prescriptions using the getCustomersAppointments method and adds them to a ListView
	 */
	@FXML
	public void loadCustomer() {
		appointmentList.getItems().clear();
		customer = customerListView.getSelectionModel().getSelectedItem();
		appointmentList.getItems().addAll(controller.getCustomersAppointments(customer));
		appointmentList.setCellFactory(param -> new ListCell<Appointment>() {
			@Override
			protected void updateItem(Appointment item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getDate() + ", " + item.getTime());
				}
			}
		});
		prescriptionList.getItems().clear();
		prescriptionList.getItems().addAll(controller.getCustomersPrescriptions(customer));
		prescriptionList.setCellFactory(param -> new ListCell<Prescription>() {
			@Override
			protected void updateItem(Prescription item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getPrescriptionName() + ", " + item.getPrescriptionGuide());
				}
			}
		});
	}

	/**
	 * Loads the selected appointment information and adds them to the appropriate fields
	 */
	@FXML
	public void loadAppointmentInfo() {
		appointment = appointmentList.getSelectionModel().getSelectedItem();
		modifyAppointmentInfo.setText(appointment.getInfo());

		modifyAppointmentTime.setText(""+appointment.getTime().truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ofPattern("HH.mm")));
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = appointment.getDate().format(dateFormatter);
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		modifyAppointmentDate.setValue(localDate);
	}

	/**
	 * Saves the modified appointment using controllers saveAppointment method
	 */
	@FXML
	public void saveAppointment()  {
		appointment.setTime(modifyAppointmentTime.getText());
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN);
		String date = modifyAppointmentDate.getValue().format(dateFormatter);

		appointment.setDate(date);
		appointment.setInfo(modifyAppointmentInfo.getText());
		if(controller.saveAppointment(appointment)) {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.show();
		}

	}

	/**
	 * Sends a message to the selected customer using the controllers sendNotification method
	 */
	@FXML
	public void sendNotification() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();
		Notification notification = new Notification();
		if (customer == null) {
			loadCustomerError();
		} else {
			notification.setCustomer(customer);
		}
		notification.setDate(dtf.format(now));
		notification.setText(notificationTextArea.getText());
		if (controller.sendNotification(notification)) {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.show();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCustomerListView();
		if(!controller.getLoggedStaff().getAccessLevel().equalsIgnoreCase("Lääkäri")) {
			prescriptionTab.setDisable(true);
		}
	}
}
