package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.DAOManager;
import model.DAOManagerIF;
import model.Notification;
import model.Prescription;
import model.Staff;
import view.staff.StaffAppointmentView;
import view.staff.StaffHomeView;

public class StaffController implements StaffControllerIF{
	
	private static Staff staff;
	private Customer[] staffCustomers;
	private DAOManagerIF daom;


    /**
     * Empty constructor
     */
	public StaffController() {
		daom = new DAOManager();
	}

    /**
     * Controller constructor for the StaffHomeView
     * @param staffHomeView
     */
	public StaffController(StaffHomeView staffHomeView) {
		if (daom == null)
			daom = new DAOManager();
	}

    /**
     * Controller constructor for the StaffAppointmentView
     * @param appointmentView
     */
	public StaffController(StaffAppointmentView appointmentView) {
		if (daom == null)
			daom = new DAOManager();
	}
	

    /**
     * Gets all appointments from the database
     * @return List of appointments
     */
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
	public static void loggedStaff(Staff staff) {
		StaffController.staff = staff;
	}

    /**
     * Gets the staff object currently logged in
     * @return staff object
     */
	public Staff getLoggedStaff() {
		return StaffController.staff;
	}

	/**
	 * Sends the notification to the database
	 * @param notification object
	 * @return true if success
	 */
	public boolean sendNotification(Notification notification) {
		notification.setStaff(staff);
	    return daom.getNotificationDAO().create(notification);
    }

    /**
     * Gets the customers associated with this staff member
     * @return List of customers
     */
	public ObservableList<Customer> getStaffCustomers() {
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		staffCustomers = daom.getStaffDAO().readStaffMembersCustomers(staff);
		
		for (Customer customer : staffCustomers) {
			customerList.add(customer);
		}
		return customerList;
	}

    /**
     * Gets the customers appointments
     * @param customer object
     * @return List of appointments
     */
	public ObservableList<Appointment> getCustomersAppointments(Customer customer) {
		for (Customer c: staffCustomers) {
			if (c.equals(customer)) {
				Appointment[] appointmentList = daom.getAppointmentDAO().readCustomerAppointments(c);
				return FXCollections.observableArrayList(appointmentList);
			}
		}
		return null;
	}

    /**
     * Adds an appointment to the database
     * @param appointment object
     * @return true if success
     */
    public boolean addAppointment(Appointment appointment) {
        return daom.getAppointmentDAO().create(appointment);
    }

    /**
     * Saves the new values in the modified appointment to the database
     * @param appointment object
     * @return true if success
     */
	public boolean saveAppointment(Appointment appointment) {
		return daom.getAppointmentDAO().update(appointment);
	}

	/**
	 * Gets the customers prescriptions
	 * @param customer
	 * @return List of prescriptions
	 */
	public ObservableList<Prescription> getCustomersPrescriptions(Customer customer) {
		for (Customer c: staffCustomers) {
			if (c.equals(customer)) {
				Prescription[] prescriptionList = daom.getPrescriptionDAO().readCustomersPrescriptions(c);
				return  FXCollections.observableArrayList(prescriptionList);
			}
		}
		return null;
	}

	/**
	 * Saves the prescription to the database
	 * @param prescription
	 * @return true if success
	 */
	public boolean savePrescription(Prescription prescription) {
		if (daom.getPrescriptionDAO().update(prescription)) {
			return true;
		} else {
			prescription.setStaff(staff);
			return daom.getPrescriptionDAO().create(prescription);
		}
	}

	public String getDailyHappenings() {
		return null;
	}
}
