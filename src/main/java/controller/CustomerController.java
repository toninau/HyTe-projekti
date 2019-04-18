package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.BloodValue;
import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.Prescription;
import view.HyteGUI;
import view.customer.CustomerCalendarView;
import view.customer.CustomerHealthView;
import view.customer.CustomerHelpView;
import view.customer.CustomerHomeView;

public class CustomerController implements CustomerController_IF {

	private CustomerCalendarView calendarview;
	private CustomerHomeView homeview;
	private CustomerHealthView healthview;
	private CustomerHelpView helpview;
	private static Customer customer;
	private DAOManager_IF daom;
	
	public CustomerController() {
	}
	public CustomerController(CustomerCalendarView calendarview) {
		this.calendarview = calendarview;
		daom = new DAOManager();
	}
	public CustomerController(CustomerHomeView homeview) {
		this.homeview = homeview;
		daom = new DAOManager();
	}
	public CustomerController(CustomerHealthView healthview) {
		this.healthview = healthview;
		daom = new DAOManager();
	}
	
	public CustomerController(CustomerHelpView helpview) {
		this.helpview = helpview;
		daom = new DAOManager();
	}
	
	public Prescription[] prescriptions() {
		return daom.getPrescriptionDAO().readCustomersPrescriptions(customer);
	}
	
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

	public Customer getCustomer() {
		return CustomerController.customer;
	}
	
	public void customersAppointments() {
		daom.getAppointmentDAO().readCustomerAppointments(customer);
	}

	@Override
	public void loggedCustomer(Customer customer) {
		CustomerController.customer = customer;	
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
