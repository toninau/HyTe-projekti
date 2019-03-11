package model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "henkilökunta")
public class Henkilökunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "henkilökuntaID")
	private int henkilökuntaID;

	@Column(name = "etunimi")
	private String etunimi;

	@Column(name = "sukunimi")
	private String sukunimi;

	@Column(name = "puhelinnumero")
	private String puhnumero;

	@Column(name = "sposti")
	private String sposti;

	@Column(name = "oikeus")
	private String oikeus;

	@OneToMany(mappedBy = "henkilökunta", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Varaus> varaukset = new HashSet<Varaus>();

	@OneToMany(mappedBy = "henkilökunta", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Resepti> reseptit = new HashSet<Resepti>();

	@OneToMany(mappedBy = "henkilökunta", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Ilmoitus> ilmoitus = new HashSet<Ilmoitus>();

	@ManyToMany(mappedBy = "henkilökunnanjäsenet")
	private Set<Asiakas> asiakkaat = new HashSet<Asiakas>();

	/**
	 * Tyhjä henkilökunta konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Henkilökunta(String, String, String, String, String)
	 */
	public Henkilökunta() {
	}

	/**
	 * Vakio henkilökunta konstruktori.
	 * 
	 * @param etunimi   Henkilökunnan jäsenen etunimi
	 * @param sukunimi  Henkilökunnan jäsenen sukunimi
	 * @param puhnumero Henkilökunnan jäsenen puhelinnumero
	 * @param sposti    Henkilökunnan jäsenen sähköpostiosoite
	 * @param oikeus    Henkilökunnan jäsenen oikeus. Määrittää jäsenen oikeudet
	 *                  esimerkiksi resepteiden määrämäämiseen asiakkaille.
	 * @see #Henkilökunta()
	 */
	public Henkilökunta(String etunimi, String sukunimi, String puhnumero, String sposti, String oikeus) {
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puhnumero = puhnumero;
		this.sposti = sposti;
		this.oikeus = oikeus;
	}

	/**
	 * Palauttaa henkilökunnan jäsenen henkilökuntaID:n. henkilökuntaID-arvo
	 * määritellään automaattisesti tietokannassa.
	 * 
	 * @return henkilökuntaID
	 */
	public int getHenkilökuntaID() {
		return henkilökuntaID;
	}

	/**
	 * Asettaa henkilökunnan jäsenen henkilökuntaID:n. henkilökuntaID-arvo
	 * määritellään automaattisesti tietokannassa. Ei käytetä uuden henkilökunnan
	 * jäsenen luomisessa.
	 * 
	 * @param henkilökuntaID henkilökunnan jäsenelle annettava henkilökuntaID
	 */
	public void setHenkilökuntaID(int henkilökuntaID) {
		this.henkilökuntaID = henkilökuntaID;
	}

	/**
	 * Palauttaa henkilökunnan jäsenen etunimen.
	 * 
	 * @return etunimi
	 */
	public String getEtunimi() {
		return etunimi;
	}

	/**
	 * Asettaa henkilökunnan jäsenelle etunimen.
	 * 
	 * @param etunimi henkilökunnan jäsenelle asetettava etunimi
	 */
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	
	/**
	 * Palauttaa henkilökunnan jäsenen sukunimen.
	 * @return sukunimi
	 */
	public String getSukunimi() {
		return sukunimi;
	}
	
	/**
	 * Asettaa henkilökunnan jäsenelle sukunimen.
	 * @param sukunimi henkilökunnan jäsenelle asetettava sukunimi
	 */
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	
	/**
	 * Palauttaa henkilökunnan jäsenen puhelinnumeron.
	 * @return puhelinnumero
	 */
	public String getPuhnumero() {
		return puhnumero;
	}
	
	/**
	 * Asettaa henkilökunnan jäsenelle puhelinnumeron.
	 * @param puhnumero henkilökunnan jäsenelle asetettava puhelinnumero
	 */
	public void setPuhnumero(String puhnumero) {
		this.puhnumero = puhnumero;
	}
	
	/**
	 * Palauttaa henkilökunnan jäsenen sähköpostiosoitteen.
	 * @return sähköpostiosoite
	 */
	public String getSposti() {
		return sposti;
	}
	
	/**
	 * Asettaa henkilökunnan jäsenelle sähköpostiosoitteen.
	 * @param sposti henkilökunnan jäsenelle asetettava sähköpostiosoite
	 */
	public void setSposti(String sposti) {
		this.sposti = sposti;
	}
	
	/**
	 * Palauttaa henkilökunnan jäsenen oikeuden.
	 * @return oikeus
	 */
	public String getOikeus() {
		return oikeus;
	}
	
	/**
	 * Asettaa henkilökunnan jäsenelle oikeuden.
	 * @param oikeus henkilökunnan jäsenelle asetettava oikeus
	 */
	public void setOikeus(String oikeus) {
		this.oikeus = oikeus;
	}
}