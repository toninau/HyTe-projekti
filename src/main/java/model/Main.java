package model;

import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {
		//Testaus main, jota voidaan myös käyttää tietokannan luomista varten
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory();
		NotificationDAO ilmoitusDAO = new NotificationDAO(istuntotehdas);
		CustomerDAO asiakasDAO = new CustomerDAO(istuntotehdas);
		IllnessDAO sairausDAO = new IllnessDAO(istuntotehdas);
		StaffDAO henkilöDAO = new StaffDAO(istuntotehdas);
		AppointmentDAO varausDAO = new AppointmentDAO(istuntotehdas);
		PrescriptionDAO reseptiDAO = new PrescriptionDAO(istuntotehdas);
		BloodValueDAO veriarvoDAO = new BloodValueDAO(istuntotehdas);
		
		
		//Luo ensimmäinen henkilökunnan jäsen
		Staff staff = new Staff();
		staff.setFirstName("test");
		staff.setSurname("tohtori");
		staff.setPhoneNumber("112");
		staff.setEmail("test@mail.com");
		staff.setAccessLevel("Lääkäri");
		henkilöDAO.create(staff);
		staff = henkilöDAO.read(1);
		
		//Ensimmäinen asiakas
		Customer customer = new Customer();
		customer.setFirstName("Jorma");
		customer.setSurname("Testi");
		customer.setSSN("123456-7890");
		customer.setIceNumber("12312145");
		customer.setAddress("Testikuja 2");
		customer.setEmail("jorma@mail.com");
		customer.setPhoneNumber("12341235");
		asiakasDAO.create(customer);
		customer = asiakasDAO.read(1);
		
		//liitetään henkilökunnan jäsen asiakkaaseen
		henkilöDAO.addCustomer(staff, customer);
		
		//lisätään varaus
		Appointment appointment = new Appointment("12.12.2020", "12:30", "leikkaus", customer, staff);
		varausDAO.create(appointment);
		//lisätään sairaus asiakkaaseen
		Illness illness = new Illness("yskä", customer);
		sairausDAO.create(illness);
		
		//Toinen henkilökunna jäsen
		staff = new Staff();
		staff.setFirstName("tohtori");
		staff.setSurname("testinen");
		staff.setPhoneNumber("112");
		staff.setEmail("test@mail.com");
		staff.setAccessLevel("Lääkäri");
		henkilöDAO.create(staff);
		staff = henkilöDAO.read(2);
		
		//Luodaan toinen varaus
		appointment = new Appointment("29.2.2090", "00:30", "katsastus", customer, staff);
		varausDAO.create(appointment);
		
		//Toinen asiakas
		customer = new Customer();
		customer.setFirstName("Jarmo");
		customer.setSurname("Testinen");
		customer.setSSN("098765-4321");
		customer.setIceNumber("85723948");
		customer.setAddress("kujatesti 9");
		customer.setEmail("jarmo.testinen@mail.com");
		customer.setPhoneNumber("94844938");
		asiakasDAO.create(customer);
		
		//liitetään henkilökunnan jäsen asiakkaaseen
		henkilöDAO.addCustomer(staff, customer);
		staff = henkilöDAO.read(1);
		henkilöDAO.addCustomer(staff, customer);
		
		//lisätään sairaudet
		customer = asiakasDAO.read(2);
		illness = new Illness("jalka poikki", customer);
		sairausDAO.create(illness);
		illness= new Illness("käsi poikki", customer);
		sairausDAO.create(illness);
		
		//Henkilökunnan jäsenen asiakkaiden lukeminen
		System.out.println("Henkilökunnan id=1 asiakkaiden lukeminen:");
		staff = henkilöDAO.read(1);
		Customer[] asiakkaat2 = henkilöDAO.readHenkilönAsiakkaat(staff);
		System.out.println("henkilökunta: " + staff.getFirstName() + ", " + staff.getSurname() + "\nAsiakkaat:");
		for (Customer a : asiakkaat2) {
			System.out.println(a.getFirstName() + ", " + a.getSurname());
		}
		
		//Asiakkaan henkilökunnan lukeminen
		System.out.println("Asiakkaan id=2 henkilökunnan lukeminen:");
		customer = asiakasDAO.read(2);
		Staff[] henkilöt = asiakasDAO.readAsiakkaanHenkilökunta(customer);
		System.out.println("asiakas: " + customer.getFirstName() + ", " + customer.getSurname() + "\nHenkilökunta:");
		for (Staff h : henkilöt) {
			System.out.println(h.getFirstName() + ", " + h.getSurname());
		}
		
		//Asiakkaan sairauksien lukeminen
		System.out.println("kaikkien asikkaiden sairauksien lukeminen:");
		Customer[] asiakkaat = asiakasDAO.readAll();
		int i = 1;
		for (Customer a : asiakkaat) {
			System.out.println("Asiakas: " + i);
			System.out.println("Nimi:\n\t" + a.getFirstName() + " " + a.getSurname() + "\nSairaudet:");
			Illness[] sairaudet = sairausDAO.readCustomersIllnessess(a);
			for (Illness s : sairaudet) {
				System.out.println("\t" + s.getIllnessName());
			}
			i++;
		}
		
		//Asiakkaan varauksien lukeminen
		System.out.println("asikas id=1 varauksien lukeminen");
		customer = asiakasDAO.read(1);
		Appointment[] varaukset = varausDAO.readCustomerAppointments(customer);
		System.out.println("Varaukset: " + customer.getFirstName() + ", " + customer.getSurname());
		for (Appointment v : varaukset) {
			System.out.println("\t" + v.getDate() + "/" + v.getTime() + "/" + v.getInfo() + "/"+ v.getStaff().getFirstName() + ", " + v.getStaff().getSurname());
		}
		
		//Henkilökunnan varauksien lukeminen
		System.out.println("henkilökunta id=1 varauksien lukeminen");
		staff = henkilöDAO.read(1);
		varaukset = varausDAO.readStaffAppointments(staff);
		System.out.println("Varaukset: " + staff.getFirstName() + ", " + staff.getSurname());
		for (Appointment v : varaukset) {
			System.out.println("\t" + v.getDate() + "/" + v.getTime() + "/" + v.getCustomer().getFirstName() + ", " + v.getCustomer().getSurname());
		}
		
		//Varauksen päivitys
		customer = asiakasDAO.read(2);
		appointment = varausDAO.read(1);
		appointment.setInfo("tämä on testi");
		appointment.setCustomer(customer);
		varausDAO.update(appointment);
		
		//Reseptin lisäys
		Prescription prescription = new Prescription();
		prescription.setAlkupvm("21.12.2012");
		prescription.setLoppupvm("22.12.2012");
		prescription.setReseptiNimi("testilääke 200mg");
		prescription.setReseptiOhje("Yksi pilleri aamuin ja illoin.");
		prescription.setAsiakas(customer);
		prescription.setHenkilökunta(staff);
		reseptiDAO.create(prescription);
		prescription = new Prescription();
		prescription.setAlkupvm("21.12.2012");
		prescription.setLoppupvm("22.12.2052");
		prescription.setReseptiNimi("testilääke 600mg");
		prescription.setReseptiOhje("kaksi pilleriä aamuin ja illoin.");
		prescription.setAsiakas(customer);
		staff = henkilöDAO.read(2);
		prescription.setHenkilökunta(staff);
		reseptiDAO.create(prescription);
		
		//Asiakkaan reseptien läpikäynti
		System.out.println("Reseptit: " + customer.getFirstName() + ", " + customer.getSurname());
		Prescription[] reseptit = reseptiDAO.readCustomersPrescriptions(customer);
		for (Prescription r : reseptit) {
			System.out.println("\t" + r.getAlkupvm() + "/" + r.getLoppupvm() + "/" + r.getReseptiNimi() + "/"+ r.getHenkilökunta().getFirstName() + ", " + r.getHenkilökunta().getSurname());
		}
		
		//Henkilökunnan reseptit
		staff = henkilöDAO.read(2);
		System.out.println("Reseptit: " + staff.getFirstName() + ", " + staff.getSurname());
		reseptit = reseptiDAO.readStaffsPrescriptions(staff);
		for (Prescription r : reseptit) {
			System.out.println("\t" + r.getAlkupvm() + "/" + r.getLoppupvm() + "/" + r.getReseptiNimi() + "/"+ r.getAsiakas().getFirstName() + ", " + r.getAsiakas().getSurname());
		}		
		
		//Asiakkaan veriarvon lisäys 2x
		customer = asiakasDAO.read(2);
		BloodValue bloodValue = new BloodValue();
		bloodValue.setCustomer(customer);
		bloodValue.setTime("12:12");
		bloodValue.setDate("1.1.2012");
		bloodValue.setBloodsugar(5.5);
		veriarvoDAO.create(bloodValue);
		bloodValue = new BloodValue();
		bloodValue.setCustomer(customer);
		bloodValue.setTime("12:15");
		bloodValue.setDate("1.1.2019");
		bloodValue.setVerenpaine("100/100/100");
		veriarvoDAO.create(bloodValue);
		
		//Asiakkaan veriarvojen hakeminen
		System.out.println("asiakkaan id=2 veriarvot");
		BloodValue[] veriarvot = veriarvoDAO.readCustomerBloodvalues(customer);
		System.out.println("Asiakas: " + customer.getFirstName() + ", " + customer.getSurname());
		for (BloodValue v : veriarvot) {
			System.out.println("\t"+ v.getTime() + "/" + v.getDate() + "/" + v.getBloodpressure() + "/" + v.getBloodsugar());
		}
		
		//Ilmoituksen lisääminen
		customer = asiakasDAO.read(1);
		staff = henkilöDAO.read(1);
		Notification notification = new Notification("12.12.2000", "syö lääkkeet", customer, staff);
		ilmoitusDAO.create(notification);
		notification = new Notification("12.12.2002", "syö lääkkeet", customer, staff);
		ilmoitusDAO.create(notification);
		
		//Ilmoituksen merkkaaminen luetuksi
		notification = ilmoitusDAO.read(1);
		notification.setRead(true);
		ilmoitusDAO.update(notification);
		
		// Ilmoituksien läpikäynti
		Notification[] ilmoitukset = ilmoitusDAO.readAsiakkaanIlmoitukset(customer);
		System.out.println("Asiakas: " + customer.getFirstName() + ", " + customer.getSurname());
		for (Notification il : ilmoitukset) {
			System.out.println("\t"+ il.getDate() + "/" + il.getText() + "/" + il.isRead() + "/" + il.getStaff().getFirstName() + ", " + il.getStaff().getSurname());
		}
		//Testi committi
	}
}
