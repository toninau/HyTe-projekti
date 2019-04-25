package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;
import model.BloodValue;
import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.ImageLoader;
import model.Prescription;
import model.UserImage;
import view.HyteGUI;
import view.customer.CustomerCalendarView;
import view.customer.CustomerHealthView;
import view.customer.CustomerHelpView;
import view.customer.CustomerHomeView;

/**
 * Controller for customer view classes. 
 * @author IdaKi
 *
 */

public class CustomerController implements CustomerController_IF {

	private CustomerCalendarView calendarview;
	private CustomerHomeView homeview;
	private CustomerHealthView healthview;
	private CustomerHelpView helpview;
	private static Customer customer;
	private DAOManager_IF daom;
	
	public CustomerController() {
	}
	
	/**
	 * A constructor for customer's calendar view.
	 * @param calendarview Customer's calendar view.
	 * @see view.customer.CustomerCalendarView#CustomerCalendarView()
	 */
	public CustomerController(CustomerCalendarView calendarview) {
		this.calendarview = calendarview;
		if(daom==null)
			daom = new DAOManager();
	}
	
	/**
	 * A constructor for customer's home view.
	 * @param homeview Customer's home view.
	 * @see view.customer.CustomerHomeView#CustomerHomeView()
	 */
	public CustomerController(CustomerHomeView homeview) {
		this.homeview = homeview;
		if(daom==null)
			daom = new DAOManager();
	}
	
	/**
	 * A constructor for customer's health view.
	 * @param healthview Customer's health view.
	 * @see view.customer.CustomerHealthView#CustomerHealthView()
	 */
	public CustomerController(CustomerHealthView healthview) {
		this.healthview = healthview;
		if(daom==null)
			daom = new DAOManager();
	}
	
	/**
	 * A constructor for customer's help view. Creates a DAO manager.
	 * @param helpview Customer's help view.
	 * @see view.customer.CustomerHelpView#CustomerHelpView()
	 */
	public CustomerController(CustomerHelpView helpview) {
		this.helpview = helpview;
		if(daom==null)
			daom = new DAOManager();
	}
	
	/**
	 * Gets all the logged in customer's prescriptions from database.
	 * @return An array of customer's prescriptions.
	 * @see view.customer.CustomerHealthView#prescriptionsList()
	 * @see dao.PrescriptionDAO#readCustomersPrescriptions(Customer)
	 */
	public Prescription[] prescriptions() {
		return daom.getPrescriptionDAO().readCustomersPrescriptions(customer);
	}
	
	/**
	 * This method creates a blood
	 */
	public boolean createBloodsugar() {
		BloodValue bloodvalue = new BloodValue();
		bloodvalue.setHighPressure(healthview.getHighPressure());
		bloodvalue.setLowPressure(healthview.getLowPressure());
		bloodvalue.setBloodsugar(healthview.getBloodsugar());
		bloodvalue.setDate(healthview.getDate());
		bloodvalue.setTime(healthview.getTime());
		bloodvalue.setCustomer(customer);
		return daom.getBloodValueDAO().create(bloodvalue);
	}
	
	public BloodValue[] bloodValueData() {
		return daom.getBloodValueDAO().readCustomerBloodvalues(customer);
	}
	

	/**
	 * Returns the current customer logged in.
	 * @return Logged in customer.
	 */
	public Customer getLoggedCustomer() {
		return CustomerController.customer;
	}
	
	/**
	 * Gets all the logged in customers appointments from database.
	 * @return An array of customer's appointments.
	 * @see view.customer.CustomerHomeView#appointmentList()
	 */
	public Appointment[] customersAppointments() {
		return daom.getAppointmentDAO().readCustomerAppointments(customer);
	}

	/**
	 * Sets the current logged customer.
	 * @see view.LoginView#loginCustomer(javafx.event.Event)
	 */
	@Override
	public void loggedCustomer(Customer customer) {
		CustomerController.customer = customer;	
	}
	
	/**
	 * Creates an image to database.
	 * @param file
	 * @param imageSlot
	 * @see dao.UserImageDAO#create(UserImage)
	 */
	public void imageToDatabase(File file, int imageSlot) {
		byte [] bfile = new byte[(int) file.length()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(bfile);
			in.close();
		}catch(Exception e) {
			
		}
		UserImage image = new UserImage();
		image.setCustomer(customer);
		image.setImage(bfile);
		image.setImageName(customer.getCustomerID() + imageSlot);
		daom.getUserImageDAO().create(image);
	}
	
	public void updateImage(File file, int imageSlot) {
		UserImage image = daom.getUserImageDAO().read(imageSlot);
		//image.setImage(bfile);
		System.out.println(image.getImageID());
		daom.getUserImageDAO().update(image);
	}
	
	/**
	 * Gets all the customer's images from database.
	 * @return An array of images.
	 * @see dao.UserImageDAO#readCustomerUserImages(Customer)
	 */
	public UserImage[] imageFromDatabase() {
		return daom.readCustomerImages();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return locations;
	}
}
