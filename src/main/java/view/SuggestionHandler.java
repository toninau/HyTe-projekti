package view;

import java.util.ArrayList;

import model.Staff;

public class SuggestionHandler {

	Staff[] staffList;
	
	
	public ArrayList<Staff> findWithPrefix(String prefix) {
		ArrayList<Staff> returnList = new ArrayList<Staff>();
		
		for (Staff staff : staffList) {
			String string = staff.getSurname() + ", " + staff.getFirstName();
			if (staff.getSurname().startsWith(prefix)) {	
				returnList.add(staff);
				System.out.println(staff.getSurname());
			}	
		}
		return returnList;
	}
	
	
	
	public void setStaffLista(Staff[] staffList) {
		this.staffList = staffList;
	}
}