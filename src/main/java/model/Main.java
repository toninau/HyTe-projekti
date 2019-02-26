package model;


public class Main {

	public static void main(String[] args) {
		AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
		SairausAccessObject sairausDAO = new SairausAccessObject();
		//KOMMENTOIDUT OSAT LUO UUDET ASIAKKAAT,SAIRAUDET,HENKILÖKUNNAN JÄSENET
		//KÄYTÖSSÄ OLEVAAN TIETOKANTAAN NÄMÄ OVAT JO LUOTU
		//Luo henkilökunnan jäsenen
		
		/*Henkilökunta jäsen = new Henkilökunta();
		jäsen.setEtunimi("test");
		jäsen.setSukunimi("tohtori");
		jäsen.setPuhnumero("112");
		jäsen.setSposti("test@mail.com");
		jäsen.setOikeus("Lääkäri");
		
		//Ensimmäinen asiakas
		
		Asiakas asiakas = new Asiakas();
		asiakas.setEtunimi("Jorma");
		asiakas.setSukunimi("Testi");
		asiakas.setHetu("123456-7890");
		asiakas.setIcenumero("12312145");
		asiakas.setKotiosoite("Testikuja 2");
		asiakas.setSposti("jorma@mail.com");
		asiakas.setPuhnumero("12341235");
		asiakas.addHenkilökunta(jäsen);		//liitetään henkilökunnan jäsen asiakkaaseen
		asiakasDAO.createAsiakas(asiakas);
		asiakas = asiakasDAO.readAsiakas(1);
		Sairaus sairaus = new Sairaus("yskä", asiakas);
		sairausDAO.createSairaus(sairaus);
		
		//Toinen asiakas

		asiakas = new Asiakas();
		asiakas.setEtunimi("Jarmo");
		asiakas.setSukunimi("Testinen");
		asiakas.setHetu("098765-4321");
		asiakas.setIcenumero("85723948");
		asiakas.setKotiosoite("kujatesti 9");
		asiakas.setSposti("jarmo.testinen@mail.com");
		asiakas.setPuhnumero("94844938");
		asiakas.addHenkilökunta(jäsen);		//liitetään henkilökunnan jäsen asiakkaaseen
		asiakasDAO.createAsiakas(asiakas);
		asiakas = asiakasDAO.readAsiakas(2);
		sairaus = new Sairaus("jalka poikki", asiakas);
		sairausDAO.createSairaus(sairaus);
		sairaus= new Sairaus("käsi poikki", asiakas);
		sairausDAO.createSairaus(sairaus);*/
		
		//Asiakkaan sairauksien lukeminen
		Asiakas[] asiakkaat = asiakasDAO.readAsiakkaat();
		int i = 1;
		for (Asiakas a : asiakkaat) {
			System.out.println("Asiakas: " + i);
			System.out.println("Nimi:\n\t" + a.getEtunimi() + " " + a.getSukunimi() + "\nSairaudet:");
			Sairaus[] sairaudet = sairausDAO.readAsiakkaanSairaudet(a);
			for (Sairaus s : sairaudet) {
				System.out.println("\t" + s.getSairausNimi());
			}
			i++;
		}
	}
}
