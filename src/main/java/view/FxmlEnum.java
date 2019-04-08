package view;

public enum FxmlEnum {

	/**
	 * fxml files for admin
	 */
	ADMINMENU("/fxml/AdminMenuView.fxml"),
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
	STAFF("fxml/StaffView.fxml");
	
	private String fxmlName;
	
	FxmlEnum(String fxmlName){
		this.fxmlName = fxmlName;
	}
	
    public String getFxml() {
        return fxmlName;
    }
 
    @Override
    public String toString() {
        return fxmlName;
    }
}
