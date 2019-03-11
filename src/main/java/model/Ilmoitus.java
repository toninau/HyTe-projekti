package model;

import javax.persistence.*;

@Entity
@Table(name = "ilmoitus")
public class Ilmoitus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ilmoitusID")
	private int ilmoitusID;

	@Column(name = "pvm")
	private String pvm;

	@Column(name = "teksti")
	private String teksti;

	@Column(name = "luettu")
	private boolean luettu = false;

	@ManyToOne
	@JoinColumn(name = "asiakasID", nullable = false)
	private Asiakas asiakas;

	@ManyToOne
	@JoinColumn(name = "henkilökuntaID", nullable = false)
	private Henkilökunta henkilökunta;

	/**
	 * Vakio ilmoitus konstruktori.
	 * 
	 * @param pvm          ilmoituksen luonti päivämäärä
	 * @param teksti       ilmoitukseen liitetty teksti
	 * @param asiakas      asiakas, jolle ilmoitus lähetetään
	 * @param henkilökunta ilmoituksen tekijä
	 * @see #Ilmoitus()
	 */
	public Ilmoitus(String pvm, String teksti, Asiakas asiakas, Henkilökunta henkilökunta) {
		this.pvm = pvm;
		this.teksti = teksti;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}

	/**
	 * Tyhjä ilmoitus konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Ilmoitus(String, String, Asiakas, Henkilökunta)
	 */
	public Ilmoitus() {

	}

	/**
	 * Palauttaa ilmoituksen ilmoitusID:n. IlmoitusID-arvo määritellään
	 * automaattisesti tietokannassa.
	 * 
	 * @return ilmoitusID
	 */
	public int getIlmoitusID() {
		return ilmoitusID;
	}

	/**
	 * Asettaa ilmoituksen ilmoitusID:n. IlmoitusID-arvo määritellään
	 * automaattisesti tietokannassa. Ei käytetä uuden ilmoituksen luomisessa.
	 * 
	 * @param ilmoitusID ilmoitukselle annettava ilmoitusID
	 */
	public void setIlmoitusID(int ilmoitusID) {
		this.ilmoitusID = ilmoitusID;
	}

	/**
	 * Palauttaa ilmoituksen päivämäärän.
	 * 
	 * @return päivämäärä
	 */
	public String getPvm() {
		return pvm;
	}

	/**
	 * Asettaa ilmoitukselle päivämäärän.
	 * 
	 * @param pvm ilmoitukselle asetettava päivämäärä
	 */
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	/**
	 * Palauttaa ilmoituksen tekstin.
	 * 
	 * @return teksti
	 */
	public String getTeksti() {
		return teksti;
	}

	/**
	 * Asettaa ilmoitukselle tekstin.
	 * 
	 * @param teksti ilmoitukselle asetettava teksti
	 */
	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	/**
	 * Palauttaa tiedon siitä onko ilmoitus luettu.
	 * 
	 * @return <code>true</code>, jos ilmoitus on luettu <br>
	 *         <code>false</code>, jos ilmoitusta ei ole luettu
	 */
	public boolean isLuettu() {
		return luettu;
	}

	/**
	 * Asettaa ilmoituksen luettu tilan.
	 * 
	 * @param luettu <code>true</code>, jos ilmoitus halutaan asettaa luetuksi ja
	 *               <code>false</code>, jos ilmoitus halutaan asettaa
	 *               lukemattomaksi
	 */
	public void setLuettu(boolean luettu) {
		this.luettu = luettu;
	}

	/**
	 * Palauttaa asiakkaan, jolle ilmoitus lähetetään.
	 * 
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/**
	 * Asettaa ilmoitukselle asiakkaan.
	 * 
	 * @param asiakas asiakas, jolle ilmoitus lähetetään
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	/**
	 * Palauttaa henkilökunnan jäsenen, joka on ilmoituksen lähettäjä
	 * 
	 * @return henkilökunnan jäsen
	 */
	public Henkilökunta getHenkilökunta() {
		return henkilökunta;
	}

	/**
	 * Asettaa ilmoitukselle henkilökunnan jäsenen.
	 * 
	 * @param henkilökunta henkilökunnan jäsen, joka on ilmoituksen lähettäjä.
	 */
	public void setHenkilökunta(Henkilökunta henkilökunta) {
		this.henkilökunta = henkilökunta;
	}
}
