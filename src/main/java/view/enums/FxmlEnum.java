package view.enums;

public enum FxmlEnum {

	/**
	 * fxml files for admin
	 */
	ADDSTAFF("/fxml/AddStaffView.fxml"),
	ADDCUSTOMER("/fxml/AddCustomerView.fxml"),
	EDITSTAFF("/fxml/EditStaffView.fxml"),
	EDITCUSTOMER("/fxml/EditCustomerView.fxml"),
	
	/**
	 * fxml files for login
	 */
	LOGIN("/fxml/LoginView.fxml"),
	/**
	 * fxml files for customer
	 */
	CUSTOMERHOME("/fxml/CustomerHomeView.fxml"),
	CUSTOMERCALENDAR("/fxml/CalendarView.fxml"),
	CUSTOMERHELP("/fxml/CustomerHelpView.fxml"),
	CUSTOMERHEALTH("/fxml/CustomerHealthView.fxml"),
	/**
	 * fxml files for staff
	 */
	STAFFHOME("/fxml/StaffHomeView.fxml"),
	STAFFAPPOINTMENT("/fxml/StaffAppointmentView.fxml");
	
	private String fxmlName;
	
	/**
	 * Fxml enum constructor.
	 * @param fxmlName Name of the fxml.
	 */
	FxmlEnum(String fxmlName){
		this.fxmlName = fxmlName;
	}
	
    public String getFxml() {
        return fxmlName;
    }
 
 
}
