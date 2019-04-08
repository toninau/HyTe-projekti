package controller;

import java.util.ArrayList;

import model.BloodValue;
import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import view.CustomerCalendarView;
import view.CustomerHealthView;
import view.CustomerHomeView;

public class CustomerController implements CustomerController_IF {

	private CustomerCalendarView calendarview;
	private CustomerHomeView homeview;
	private CustomerHealthView healthview;
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
}
