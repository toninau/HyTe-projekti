package model;

public interface DAOManager_IF {
	public abstract void create(Object obj);
	public abstract boolean update(Object obj);
	public abstract Object[] readAll(String obj);
	public abstract Object readWithID(int id, String obj);
	public abstract Object readWithEmail(String key, String email);
	public abstract CustomerDAO getCustomerDAO();
	public abstract StaffDAO getStaffDAO();
	public abstract PrescriptionDAO getPrescriptionDAO();
	public abstract IllnessDAO getIllnessDAO();
	public abstract BloodValueDAO getBloodValueDAO();
	public abstract NotificationDAO getNotificationDAO();
	public abstract AppointmentDAO getAppointmentDAO();
}
