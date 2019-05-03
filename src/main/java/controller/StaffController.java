package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import view.staff.StaffAppointmentView;
import view.staff.StaffHomeView;

public class StaffController implements StaffController_IF {
	
	static Staff staff;
	private Customer[] staffCustomers;
	private DAOManager_IF daom;
	private StaffHomeView staffHomeView;
	private StaffAppointmentView appointmentView;
	
	public StaffController() {
		daom = new DAOManager();
	}
	
	public StaffController(StaffHomeView staffHomeView) {
		this.staffHomeView = staffHomeView;
		if (daom == null)
			daom = new DAOManager();
	}
	
	public StaffController(StaffAppointmentView appointmentView) {
		this.appointmentView = appointmentView;
		if (daom == null)
			daom = new DAOManager();
	}
	
		
	public ObservableList<Appointment> allAppointments() {
		ObservableList<Appointment> list = FXCollections.observableArrayList();
		Appointment[] appointments = daom.getAppointmentDAO().readStaffAppointments(staff);	
		for (Appointment appointment : appointments) {
			list.add(appointment);
		}		
		return list;
	}
	
	/**
	 * Sets the current logged employee.
	 * @param staff Logged employee.
	 */
	public void loggedStaff(Staff staff) {
		StaffController.staff = staff;
	}

	public Staff getLoggedStaff() {
		return StaffController.staff;
	}

	public boolean sendNotification(Notification notification) {
	    //daom.getNotificationDAO().create(notification);
	    return true;
    }


	public ObservableList<Customer> getStaffCustomers() {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		staffCustomers = daom.getStaffDAO().readStaffMembersCustomers(staff);
		
		for (Customer customer : staffCustomers) {
			customerList.add(customer);
			System.out.println(customer.getCustomerID());
		}
		return customerList;
	}


	public ObservableList<Appointment> getCustomersAppointments(Customer customer) {
		for (Customer c: staffCustomers) {
			if (c.equals(customer)) {
				Appointment[] appointmentList = daom.getAppointmentDAO().readCustomerAppointments(c);
				ObservableList<Appointment> list = FXCollections.observableArrayList(appointmentList);
				return list;
			}
		}
		return null;
	}

    public boolean addAppointment(Appointment appointment) {
        return daom.getAppointmentDAO().create(appointment);
    }

	public boolean saveAppointment(Appointment appointment) {
		return daom.getAppointmentDAO().update(appointment);
	}

	/**
	 *
	 * @param data the notification you want to send
	 * @param customer the customer object loaded in the UI
	 * @return true if success
	 */

	public void sendNotification(String data, Customer customer) {
		String date = java.time.LocalTime.now().toString();
		daom.getNotificationDAO().create(new Notification(date, data, customer, staff));
	}


	public String getDailyHappenings() {
		// TODO Auto-generated method stub
		return null;
	}
}
