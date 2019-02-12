package model;


public class Main {

	public static void main(String[] args) {
		AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
		// Lue yksittäinen asiakas
		Asiakas asiakas = asiakasDAO.readAsiakas("101150-010A");
		System.out.println(asiakas.getEtunimi());
		// Lue kaikki asiakkaat
		Asiakas[] asiakkaat = asiakasDAO.readAsiakkaat();
		for (Asiakas a : asiakkaat) {
			System.out.println(a.getEtunimi());
		}
	}
}
