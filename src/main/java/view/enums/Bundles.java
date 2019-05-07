package view.enums;

public enum Bundles {
	/**
	 * Property file for admin views.
	 */
	ADMIN("properties.Admin"),
	/**
	 * Property file for login view.
	 */
	LOGIN("properties.LoginProperties"),
	/**
	 * Property file for customer views.
	 */
	CUSTOMER("properties.Customer"),
	/**
	 * Propert file for staff views.
	 */
	STAFF("properties.Staff");
	
	private String bundleName;
	
	/**
	 * Bundle enum constructor
	 * @param bundleName Name of the bundle.
	 */
	Bundles(String bundleName){
		this.bundleName = bundleName;
	}
	
    public String getBundleName() {
        return bundleName;
    }
 
}
