package model;

import javax.persistence.*;

/**
 * Resepti entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "resepti")
public class Resepti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reseptiID")
	private int reseptiID;

	@Column(name = "alkupvm")
	private String alkupvm;

	@Column(name = "loppupvm")
	private String loppupvm;

	@Column(name = "reseptinimi")
	private String reseptiNimi;

	@Column(name = "reseptiohje")
	private String reseptiOhje;

	@ManyToOne
	@JoinColumn(name = "asiakasID", nullable = false)
	private Asiakas asiakas;

	@ManyToOne
	@JoinColumn(name = "henkilökuntaID", nullable = false)
	private Henkilökunta henkilökunta;

	/**
	 * Vakio resepti konstruktori.
	 * 
	 * @param alkupvm      Päivämäärä, jolloin resepti on määrätty
	 * @param loppupvm     Päivämäärä, johon saakka resepti on voimassa
	 * @param reseptiNimi  Reseptin nimi, esimerkiksi lääke <i>Testilääke 600mg</i>
	 * @param reseptiOhje  Reseptin käyttöohjeet
	 * @param asiakas      Asiakas, jolle resepti on määrätty
	 * @param henkilökunta Henkilökunnan jäsen, joka on määrännyt reseptin
	 * @see #Resepti()
	 */
	public Resepti(String alkupvm, String loppupvm, String reseptiNimi, String reseptiOhje, Asiakas asiakas,
			Henkilökunta henkilökunta) {
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.reseptiNimi = reseptiNimi;
		this.reseptiOhje = reseptiOhje;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}

	/**
	 * Tyhjä resepti konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Resepti()
	 */
	public Resepti() {
	}

	/**
	 * Palauttaa reseptin reseptiID:n. ReseptiID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return reseptiID
	 */
	public int getReseptiID() {
		return reseptiID;
	}

	/**
	 * Asettaa reseptin reseptiID:n. ReseptiID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden reseptin luomisessa.
	 * 
	 * @param reseptiID reseptille annettava reseptiID
	 */
	public void setReseptiID(int reseptiID) {
		this.reseptiID = reseptiID;
	}

	/**
	 * Palauttaa reseptin alkamispäivämäärän.
	 * 
	 * @return alkamispäivämäärä
	 */
	public String getAlkupvm() {
		return alkupvm;
	}

	/**
	 * Asettaa reseptille alkamispäivämäärän.
	 * 
	 * @param alkupvm reseptille asetettava alkamispäivämäärä
	 */
	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}

	/**
	 * Palauttaa reseptin loppupäivämäärän.
	 * 
	 * @return loppupäivämäärä
	 */
	public String getLoppupvm() {
		return loppupvm;
	}

	/**
	 * Asettaa reseptille loppupäivämäärän.
	 * 
	 * @param loppupvm reseptille asetettava loppupäivämäärä
	 */
	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}

	/**
	 * Palauttaa reseptin nimen.
	 * 
	 * @return reseptinimi
	 */
	public String getReseptiNimi() {
		return reseptiNimi;
	}

	/**
	 * Asettaa reseptille nimen.
	 * 
	 * @param reseptiNimi reseptille asetettava nimi
	 */
	public void setReseptiNimi(String reseptiNimi) {
		this.reseptiNimi = reseptiNimi;
	}

	/**
	 * Palauttaa reseptin käyttöohjeen.
	 * 
	 * @return käyttöohje
	 */
	public String getReseptiOhje() {
		return reseptiOhje;
	}

	/**
	 * Asettaa reseptille käyttöohjeen.
	 * 
	 * @param reseptiOhje reseptille asetettava käyttöohje
	 */
	public void setReseptiOhje(String reseptiOhje) {
		this.reseptiOhje = reseptiOhje;
	}

	/**
	 * Palauttaa reseptin asiakkaan.
	 * 
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/**
	 * Asettaa reseptin asiakkaan.
	 * 
	 * @param asiakas reseptille asetettava asiakas
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	/**
	 * Palauttaa reseptin henkilökunnan jäsenen.
	 * 
	 * @return henkilökunnan jäsen
	 */
	public Henkilökunta getHenkilökunta() {
		return henkilökunta;
	}

	/**
	 * Asettaa reseptin henkilökunnan jäsenen.
	 * 
	 * @param henkilökunta reseptille asetettava henkilökunnan jäsen
	 */
	public void setHenkilökunta(Henkilökunta henkilökunta) {
		this.henkilökunta = henkilökunta;
	}
}
