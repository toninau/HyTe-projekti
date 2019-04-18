package view.enums;

public enum Bundles {
	ADMIN("properties.Admin"),
	LOGIN("properties.LoginProperties"),
	CUSTOMER("properties.Customer");
	private String bundleName;
	
	Bundles(String bundleName){
		this.bundleName = bundleName;
	}
	
    public String getBundleName() {
        return bundleName;
    }
 
    @Override
    public String toString() {
        return bundleName;
    }
}
