package model;

import javax.persistence.*;

/**
 * Varaus entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "varaus")
public class Varaus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "varausID")
	private int varausID;

	@Column(name = "päivämäärä")
	private String päivämäärä;

	@Column(name = "kellonaika")
	private String kellonaika;

	@Column(name = "info")
	private String info;

	@ManyToOne
	@JoinColumn(name = "asiakasID", nullable = false)
	private Asiakas asiakas;

	@ManyToOne
	@JoinColumn(name = "henkilökuntaID", nullable = false)
	private Henkilökunta henkilökunta;

	/**
	 * Vakio varaus konstruktori.
	 * 
	 * @param päivämäärä   päivä, jolle varaus on varattu
	 * @param kellonaika   varauksen ajankohta
	 * @param info         mitä varaus koskee
	 * @param asiakas      varauksen asiakas
	 * @param henkilökunta varauksen henkilökunnan jäsen
	 * @see #Varaus()
	 */
	public Varaus(String päivämäärä, String kellonaika, String info, Asiakas asiakas, Henkilökunta henkilökunta) {
		this.päivämäärä = päivämäärä;
		this.kellonaika = kellonaika;
		this.info = info;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}

	/**
	 * Tyhjä varaus konstruktori.
	 * 
	 * @see #Varaus(String, String, String, Asiakas, Henkilökunta)
	 */
	public Varaus() {
	}

	/**
	 * Palauttaa varauksen varausID:n. VarausID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return varausIDF
	 */
	public int getVarausID() {
		return varausID;
	}

	/**
	 * Asettaa varauksen varausID:n. VarausID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden varauksen luomisessa.
	 * 
	 * @param varausID varaukselle annettava varausID
	 */
	public void setVarausID(int varausID) {
		this.varausID = varausID;
	}

	/**
	 * Palauttaa varauksen päivämäärän.
	 * 
	 * @return päivämäärä
	 */
	public String getPäivämäärä() {
		return päivämäärä;
	}

	/**
	 * Asettaa varaukselle päivämäärän.
	 * 
	 * @param päivämäärä varaukselle asetettava päivämäärä
	 */
	public void setPäivämäärä(String päivämäärä) {
		this.päivämäärä = päivämäärä;
	}

	/**
	 * Palauttaa varauksen kellonajan.
	 * 
	 * @return kellonaika
	 */
	public String getKellonaika() {
		return kellonaika;
	}

	/**
	 * Asettaa varaukselle kellonajan.
	 * 
	 * @param kellonaika varaukselle asetettava kellonaika
	 */
	public void setKellonaika(String kellonaika) {
		this.kellonaika = kellonaika;
	}

	/**
	 * Palauttaa varauksen infon eli mitä varaus koskee.
	 * 
	 * @return info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Asettaa varaukselle infon eli mitä varaus koskee.
	 * 
	 * @param info varaukselle asetettava info
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Palauttaa varauksen asiakkaan.
	 * 
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/**
	 * Asettaa varaukselle asiakkaan.
	 * 
	 * @param asiakas varaukselle asetettava asiakas
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	/**
	 * Palauttaa varauksen henkilökunnan jäsenen.
	 * 
	 * @return henkilökunnan jäsen
	 */
	public Henkilökunta getHenkilökunta() {
		return henkilökunta;
	}

	/**
	 * Asettaa varaukselle henkilökunnan jäsenen.
	 * 
	 * @param henkilökunta varaukselle asetettava henkilökunnan jäsen
	 */
	public void setHenkilökunta(Henkilökunta henkilökunta) {
		this.henkilökunta = henkilökunta;
	}

}
