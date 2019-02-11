package model;


public class Main {

	public static void main(String[] args) {
		AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
		Asiakas asiakas = asiakasDAO.readAsiakas("101150-010A");
		System.out.println(asiakas.getEtunimi());
	}
}
