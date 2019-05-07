package controller;

import model.*;
import view.HyteGUI;
import view.customer.CustomerCalendarView;
import view.customer.CustomerHealthView;
import view.customer.CustomerHelpView;
import view.customer.CustomerHomeView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for customer view classes.
 * 
 * @author IdaKi
 *
 */
 
public class CustomerController implements CustomerController_IF {

	private CustomerHealthView healthview;
	private static Customer customer;
	private DAOManager_IF daom;

	public CustomerController() {
	}

	/**
	 * A constructor for customer's calendar view.
	 * 
	 * @param calendarview Customer's calendar view.
	 * @see view.customer.CustomerCalendarView#CustomerCalendarView()
	 */
	public CustomerController(CustomerCalendarView calendarview) {
		if (daom == null)
			daom = new DAOManager();
	}

	/**
	 * A constructor for customer's home view.
	 * 
	 * @param homeview Customer's home view.
	 * @see view.customer.CustomerHomeView#CustomerHomeView()
	 */
	public CustomerController(CustomerHomeView homeview) {
		if (daom == null)
			daom = new DAOManager();
	}

	/**
	 * A constructor for customer's health view.
	 * 
	 * @param healthview Customer's health view.
	 * @see view.customer.CustomerHealthView#CustomerHealthView()
	 */
	public CustomerController(CustomerHealthView healthview) {
		this.healthview = healthview;
		if (daom == null)
			daom = new DAOManager();
	}

	/**
	 * A constructor for customer's help view. Creates a DAO manager.
	 * 
	 * @param helpview Customer's help view.
	 * @see view.customer.CustomerHelpView#CustomerHelpView()
	 */
	public CustomerController(CustomerHelpView helpview) {
		if (daom == null)
			daom = new DAOManager();
	}

	/**
	 * Gets all the logged in customer's prescriptions from database.
	 * 
	 * @return An array of customer's prescriptions.
	 * @see view.customer.CustomerHealthView#prescriptionsList()
	 * @see dao.PrescriptionDAO#readCustomersPrescriptions(Customer)
	 */
	public Prescription[] prescriptions() {
		return daom.readCustomerPrescriptions();
	}

	/**
	 * This method creates a blood sugar value to the database.
	 * @see dao.BloodValueDAO#create(BloodValue)
	 */
	public boolean createBloodsugarValue() {
		boolean success = false;
		BloodValue bloodvalue = new BloodValue();
		if (healthview.getBloodsugar() > 0)
			success = true;

		if (success) {
			bloodvalue.setBloodsugar(healthview.getBloodsugar());
			bloodvalue.setDate(healthview.getDate());
			bloodvalue.setTime(healthview.getTime());
			bloodvalue.setCustomer(customer);
			return daom.getBloodValueDAO().create(bloodvalue);
		}
		return success;
	}

	/**
	 * Creates a blood pressure value to the database.
	 * @see dao.BloodValueDAO#create(BloodValue)
	 */
	public boolean createBloodPressureValue() {
		boolean success = false;
		BloodValue bloodValue = new BloodValue();
		if (healthview.getHighPressure() > 0 && healthview.getLowPressure() > 0)
			success = true;

		if (success) {
			bloodValue.setHighPressure(healthview.getHighPressure());
			bloodValue.setLowPressure(healthview.getLowPressure());
			bloodValue.setDate(healthview.getDate());
			bloodValue.setTime(healthview.getTime());
			bloodValue.setCustomer(customer);
			return daom.getBloodValueDAO().create(bloodValue);
		}
		return success;
	}

	/**
	 * Gets all the customer's blood values from database and adds the blood values 
	 * with blood sugar values to an ArrayList.
	 * @return bList ArrayList of blood sugar values.
	 * @see dao.BloodValueDAO#readCustomerBloodvalues(Customer)
	 */
	public ArrayList<BloodValue> bloodSugarData() {
		BloodValue[] b = daom.readCustomerBloodValues();
		System.out.println(b.length);
		ArrayList<BloodValue> bList = new ArrayList<>();
		for (BloodValue bloodValue : b) {
			if (bloodValue.getBloodsugar() > 0)
				bList.add(bloodValue);
		}
		return bList;
	}

	/**
	 * Gets all the customer's blood values from database and adds the blood values
	 * with blood pressure values to an ArrayList.
	 * @return bList ArrayList of blood pressure values.
	 * @see dao.BloodValueDAO#readCustomerBloodvalues(Customer)
	 */
	public ArrayList<BloodValue> bloodPressureData() {
		BloodValue[] b = daom.readCustomerBloodValues();
		ArrayList<BloodValue> bList = new ArrayList<>();
		for (BloodValue bloodValue : b) {
			if (bloodValue.getHighPressure() > 0 && bloodValue.getLowPressure() > 0)
				bList.add(bloodValue);
		}
		return bList;
	}

	/**
	 * Returns the current customer logged in.
	 * 
	 * @return Logged in customer.
	 */
	public Customer getLoggedCustomer() {
		return CustomerController.customer;
	}

	/**
	 * Gets all the logged in customers appointments from database.
	 * 
	 * @return An array of customer's appointments.
	 * @see view.customer.CustomerHomeView#appointmentList()
	 */
	public Appointment[] customersAppointments() {
		return daom.readCustomerAppointments();
	}

	/**
	 * Sets the current logged customer.
	 * 
	 * @see view.LoginView#loginCustomer(javafx.event.Event)
	 */
	@Override
	public void loggedCustomer(Customer customer) {
		CustomerController.customer = customer;
	}

	/**
	 * Creates an image to database.
	 * 
	 * @param file
	 * @param imageSlot
	 * @see dao.UserImageDAO#create(UserImage)
	 */
	public void imageToDatabase(File file, int imageSlot) {
		byte[] bfile = new byte[(int) file.length()];
		try (FileInputStream in = new FileInputStream(file)) {
			int count = 0;
			while (in.read(bfile) > 0) {
				in.read(bfile);
			}
		} catch (Exception e) {}
		UserImage image = new UserImage();
		image.setCustomer(customer);
		image.setImage(bfile);
		image.setImageName(customer.getCustomerID() + imageSlot);
		image.setImageID(customer.getCustomerID() + imageSlot);
		daom.getUserImageDAO().create(image);
		daom.writeImageToFileDuringSession(customer);
	}

	/**
	 * Updates an image to database and to a temporary file.
	 * 
	 * @param file The file of the new image.
	 * @param imageSlot The slot of the image to be updated.
	 * @see dao.UserImageDAO#update(UserImage)
	 */
	public void updateImage(File file, int imageSlot) {
		UserImage image = daom.getUserImageDAO().read(customer.getCustomerID() + imageSlot);
		byte[] bfile = new byte[(int) file.length()];
		try (FileInputStream in = new FileInputStream(file)) {
			while (in.read(bfile) > 0) {
				in.read(bfile);
			}
		} catch (Exception e) {}
		image.setImage(bfile);
		daom.getUserImageDAO().update(image);
		daom.writeImageToFileDuringSession(customer);
	}

	/**
	 * Deletes the chosen image.
	 * @param imageSlot The slot of the chosen image-
	 */
	public void deleteImage(int imageSlot) {
		daom.getUserImageDAO().delete(customer.getCustomerID() + imageSlot);
	}

	/**
	 * Gets all the customer's images from database.
	 * 
	 * @return An array of images.
	 * @see dao.UserImageDAO#readCustomerUserImages(Customer)
	 */
	public UserImage[] imageFromTempFile() {
		return daom.readCustomerImages();
	}

	/**
	 * Updates medicine taken -column in prescription table.
	 * 
	 * @param prescription prescription to be updated.
	 * @see dao.PrescriptionDAO#update(Prescription)
	 */
	public void updateMedicineTaken(Prescription prescription) {
		daom.getPrescriptionDAO().update(prescription);
	}

	public List<String> locationSuggestions() {
		ArrayList<String> locations = new ArrayList<>();
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
		} catch (IOException e) {}

		return locations;
	}

	@Override
	public Notification[] getMyMessages() {
		return daom.getNotificationDAO().readCustomersNotifications(customer);

	}
}
