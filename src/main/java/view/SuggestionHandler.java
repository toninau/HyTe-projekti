package view;

import java.util.ArrayList;

import model.Staff;

public class SuggestionHandler {

	Staff[] staffList;
	
	
	public ArrayList<String> findWithPrefix(String prefix) {
		ArrayList<String> returnList = new ArrayList<String>();
		
		for (Staff staff : staffList) {
			String string = staff.getSurname() + ", " + staff.getFirstName();
			if (staff.getSurname().startsWith(prefix)) {	
				returnList.add(string);
				System.out.println(staff.getSurname());
			}	
		}
		return returnList;
	}
	
	
	
	public void setStaffLista(Staff[] staffList) {
		this.staffList = staffList;
	}
}
