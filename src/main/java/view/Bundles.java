package view;

public enum Bundles {
	ADMINMENU("properties.AdminMenu"),
	LOGIN("properties.LoginProperties");
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
