package controller;

import model.Appointment;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.staff.StaffAppointmentView;
import view.staff.StaffHomeView;
import view.staff.StaffPrescriptionView;

public class StaffController {
	
	static Staff staff;
	private DAOManager_IF daom;
	private StaffHomeView staffHomeView;
	private StaffAppointmentView appointmentView;
	private StaffPrescriptionView prescriptionView;
	
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
	
	public StaffController(StaffPrescriptionView prescriptionView) {
		this.prescriptionView = prescriptionView;
		if (daom == null)
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
