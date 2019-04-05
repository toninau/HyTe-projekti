package controller;

import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import view.CustomerCalendarView;
import view.CustomerHomeView;

public class CustomerController implements CustomerController_IF {

	private CustomerCalendarView calendarview;
	private CustomerHomeView homeview;
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
