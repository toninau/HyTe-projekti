package model;


public class Main {

	public static void main(String[] args) {
		//Testaus main, jota voidaan myös käyttää tietokannan luomista varten
		AsiakasAccessObject asiakasDAO = new AsiakasAccessObject();
		SairausAccessObject sairausDAO = new SairausAccessObject();
		HenkilökuntaAccessObject henkilöDAO = new HenkilökuntaAccessObject();
		VarausAccessObject varausDAO = new VarausAccessObject();
		ReseptiAccessObject reseptiDAO = new ReseptiAccessObject();
		VeriarvoAccessObject veriarvoDAO = new VeriarvoAccessObject();
		
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
		
		//lisätään varaus
		Varaus varaus = new Varaus("12.12.2020", "12:30", "leikkaus", asiakas, henkilökunta);
		varausDAO.createVaraus(varaus);
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
		
		//Luodaan toinen varaus
		varaus = new Varaus("29.2.2090", "00:30", "katsastus", asiakas, henkilökunta);
		varausDAO.createVaraus(varaus);
		
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
		System.out.println("Henkilökunnan id=1 asiakkaiden lukeminen:");
		Henkilökunta henkilö = henkilöDAO.readHenkilökunta(1);
		Asiakas[] asiakkaat2 = henkilöDAO.readHenkilönAsiakkaat(henkilö);
		System.out.println("henkilökunta: " + henkilö.getEtunimi() + ", " + henkilö.getSukunimi() + "\nAsiakkaat:");
		for (Asiakas a : asiakkaat2) {
			System.out.println(a.getEtunimi() + ", " + a.getSukunimi());
		}
		
		//Asiakkaan henkilökunnan lukeminen
		System.out.println("Asiakkaan id=2 henkilökunnan lukeminen:");
		asiakas = asiakasDAO.readAsiakas(2);
		Henkilökunta[] henkilöt = asiakasDAO.readAsiakkaanHenkilökunta(asiakas);
		System.out.println("asiakas: " + asiakas.getEtunimi() + ", " + asiakas.getSukunimi() + "\nHenkilökunta:");
		for (Henkilökunta h : henkilöt) {
			System.out.println(h.getEtunimi() + ", " + h.getSukunimi());
		}
		
		//Asiakkaan sairauksien lukeminen
		System.out.println("kaikkien asikkaiden sairauksien lukeminen:");
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
		
		//Asiakkaan varauksien lukeminen
		System.out.println("asikas id=1 varauksien lukeminen");
		asiakas = asiakasDAO.readAsiakas(1);
		Varaus[] varaukset = varausDAO.readAsiakkaanVaraukset(asiakas);
		System.out.println("Varaukset: " + asiakas.getEtunimi() + ", " + asiakas.getSukunimi());
		for (Varaus v : varaukset) {
			System.out.println("\t" + v.getPäivämäärä() + "/" + v.getKellonaika() + "/" + v.getInfo() + "/"+ v.getHenkilökunta().getEtunimi() + ", " + v.getHenkilökunta().getSukunimi());
		}
		
		//Henkilökunnan varauksien lukeminen
		System.out.println("henkilökunta id=1 varauksien lukeminen");
		henkilö = henkilöDAO.readHenkilökunta(1);
		varaukset = varausDAO.readHenkilökunnanVaraukset(henkilö);
		System.out.println("Varaukset: " + henkilö.getEtunimi() + ", " + henkilö.getSukunimi());
		for (Varaus v : varaukset) {
			System.out.println("\t" + v.getPäivämäärä() + "/" + v.getKellonaika() + "/" + v.getAsiakas().getEtunimi() + ", " + v.getAsiakas().getSukunimi());
		}
		
		//Varauksen päivitys
		asiakas = asiakasDAO.readAsiakas(2);
		varaus = varausDAO.readVaraus(1);
		varaus.setInfo("tämä on testi");
		varaus.setAsiakas(asiakas);
		varausDAO.updateVaraus(varaus);
		
		//Reseptin lisäys
		Resepti resepti = new Resepti();
		resepti.setAlkupvm("21.12.2012");
		resepti.setLoppupvm("22.12.2012");
		resepti.setReseptiNimi("testilääke 200mg");
		resepti.setReseptiOhje("Yksi pilleri aamuin ja illoin.");
		resepti.setAsiakas(asiakas);
		resepti.setHenkilökunta(henkilökunta);
		reseptiDAO.createResepti(resepti);
		resepti = new Resepti();
		resepti.setAlkupvm("21.12.2012");
		resepti.setLoppupvm("22.12.2052");
		resepti.setReseptiNimi("testilääke 600mg");
		resepti.setReseptiOhje("kaksi pilleriä aamuin ja illoin.");
		resepti.setAsiakas(asiakas);
		henkilökunta = henkilöDAO.readHenkilökunta(2);
		resepti.setHenkilökunta(henkilökunta);
		reseptiDAO.createResepti(resepti);
		
		//Asiakkaan reseptien läpikäynti
		System.out.println("Reseptit: " + asiakas.getEtunimi() + ", " + asiakas.getSukunimi());
		Resepti[] reseptit = reseptiDAO.readAsiakkaanReseptit(asiakas);
		for (Resepti r : reseptit) {
			System.out.println("\t" + r.getAlkupvm() + "/" + r.getLoppupvm() + "/" + r.getReseptiNimi() + "/"+ r.getHenkilökunta().getEtunimi() + ", " + r.getHenkilökunta().getSukunimi());
		}
		
		//Henkilökunnan reseptit
		henkilökunta = henkilöDAO.readHenkilökunta(2);
		System.out.println("Reseptit: " + henkilökunta.getEtunimi() + ", " + henkilökunta.getSukunimi());
		reseptit = reseptiDAO.readHenkilökunnanReseptit(henkilökunta);
		for (Resepti r : reseptit) {
			System.out.println("\t" + r.getAlkupvm() + "/" + r.getLoppupvm() + "/" + r.getReseptiNimi() + "/"+ r.getAsiakas().getEtunimi() + ", " + r.getAsiakas().getSukunimi());
		}		
		
		//Asiakkaan veriarvon lisäys 2x
		asiakas = asiakasDAO.readAsiakas(2);
		Veriarvo veriarvo = new Veriarvo();
		veriarvo.setAsiakas(asiakas);
		veriarvo.setAika("12:12");
		veriarvo.setPvm("1.1.2012");
		veriarvo.setVerensokeri(5.5);
		veriarvoDAO.createVeriarvo(veriarvo);
		veriarvo = new Veriarvo();
		veriarvo.setAsiakas(asiakas);
		veriarvo.setAika("12:15");
		veriarvo.setPvm("1.1.2019");
		veriarvo.setVerenpaine("100/100/100");
		veriarvoDAO.createVeriarvo(veriarvo);
		
		//Asiakkaan veriarvojen hakeminen
		System.out.println("asiakkaan id=2 veriarvot");
		Veriarvo[] veriarvot = veriarvoDAO.readAsiakkaanVeriarvot(asiakas);
		System.out.println("Asiakas: " + asiakas.getEtunimi() + ", " + asiakas.getSukunimi());
		for (Veriarvo v : veriarvot) {
			System.out.println("\t"+ v.getAika() + "/" + v.getPvm() + "/" + v.getVerenpaine() + "/" + v.getVerensokeri());
		}
	}
}
