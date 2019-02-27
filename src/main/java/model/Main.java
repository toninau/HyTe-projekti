package model;


public class Main {

	public static void main(String[] args) {
		AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
		SairausAccessObject sairausDAO = new SairausAccessObject();
		HenkilökuntaAccessObject henkilöDAO = new HenkilökuntaAccessObject();
		//KOMMENTOIDUT OSAT LUO UUDET ASIAKKAAT,SAIRAUDET,HENKILÖKUNNAN JÄSENET
		//KÄYTÖSSÄ OLEVAAN TIETOKANTAAN NÄMÄ OVAT JO LUOTU
		
		//Luo ensimmäinen henkilökunnan jäsen
		Henkilökunta henkilökunta = new Henkilökunta();
		henkilökunta.setEtunimi("test");
		henkilökunta.setSukunimi("tohtori");
		henkilökunta.setPuhnumero("112");
		henkilökunta.setSposti("test@mail.com");
		henkilökunta.setOikeus("Lääkäri");
		henkilöDAO.createHenkilökunta(henkilökunta);
		henkilökunta = henkilöDAO.readHenkilökunta(1);
		//Ensimmäinen asiakas
		Asiakas asiakas = new Asiakas();
		asiakas.setEtunimi("Jorma");
		asiakas.setSukunimi("Testi");
		asiakas.setHetu("123456-7890");
		asiakas.setIcenumero("12312145");
		asiakas.setKotiosoite("Testikuja 2");
		asiakas.setSposti("jorma@mail.com");
		asiakas.setPuhnumero("12341235");
		asiakasDAO.createAsiakas(asiakas);
		asiakas = asiakasDAO.readAsiakas(1);
		//liitetään henkilökunnan jäsen asiakkaaseen
		henkilöDAO.addAsiakas(henkilökunta, asiakas);
		//lisätään sairaus asiakkaaseen
		Sairaus sairaus = new Sairaus("yskä", asiakas);
		sairausDAO.createSairaus(sairaus);
		//Toinen henkilökunna jäsen
		henkilökunta = new Henkilökunta();
		henkilökunta.setEtunimi("tohtori");
		henkilökunta.setSukunimi("testinen");
		henkilökunta.setPuhnumero("112");
		henkilökunta.setSposti("test@mail.com");
		henkilökunta.setOikeus("Lääkäri");
		henkilöDAO.createHenkilökunta(henkilökunta);
		henkilökunta = henkilöDAO.readHenkilökunta(2);
		//Toinen asiakas
		asiakas = new Asiakas();
		asiakas.setEtunimi("Jarmo");
		asiakas.setSukunimi("Testinen");
		asiakas.setHetu("098765-4321");
		asiakas.setIcenumero("85723948");
		asiakas.setKotiosoite("kujatesti 9");
		asiakas.setSposti("jarmo.testinen@mail.com");
		asiakas.setPuhnumero("94844938");
		asiakasDAO.createAsiakas(asiakas);
		//liitetään henkilökunnan jäsen asiakkaaseen
		henkilöDAO.addAsiakas(henkilökunta, asiakas);
		henkilökunta = henkilöDAO.readHenkilökunta(1);
		henkilöDAO.addAsiakas(henkilökunta, asiakas);
		//lisätään sairaudet
		asiakas = asiakasDAO.readAsiakas(2);
		sairaus = new Sairaus("jalka poikki", asiakas);
		sairausDAO.createSairaus(sairaus);
		sairaus= new Sairaus("käsi poikki", asiakas);
		sairausDAO.createSairaus(sairaus);
		//Henkilökunnan jäsenen asiakkaiden lukeminen
		Henkilökunta henkilö = henkilöDAO.readHenkilökunta(1);
		Asiakas[] asiakkaat2 = henkilöDAO.readHenkilönAsiakkaat(henkilö);
		System.out.println(henkilö.getEtunimi() + ", " + henkilö.getSukunimi() + "\nAsiakkaat:");
		for (Asiakas a : asiakkaat2) {
			System.out.println(a.getEtunimi() + ", " + a.getSukunimi());
		}
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
