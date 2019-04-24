package controller;

import model.Appointment;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.staff.StaffHomeView;

public class StaffController {
	
	static Staff staff;
	private DAOManager_IF daom;
	private StaffHomeView staffHomeView;
	
	public StaffController() {
		daom = new DAOManager();
	}
	
	public StaffController(StaffHomeView staffHomeView) {
		this.staffHomeView = staffHomeView;
		daom = new DAOManager();
	}
		
	public Appointment[] allAppointments() {
		return daom.getAppointmentDAO().readStaffAppointments(staff);
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
}
