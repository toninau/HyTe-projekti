package model;

import javax.persistence.*;
import java.util.*;

/**
 * Asiakas entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "asiakas")
public class Asiakas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asiakasID")
	private int asiakasID;

	@Column(name = "etunimi")
	private String etunimi;

	@Column(name = "sukunimi")
	private String sukunimi;

	@Column(name = "hetu")
	private String hetu;

	@Column(name = "kotiosoite")
	private String kotiosoite;

	@Column(name = "sposti")
	private String sposti;

	@Column(name = "puhelinnumero")
	private String puhnumero;

	@Column(name = "icenumero")
	private String icenumero;

	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Sairaus> sairaudet = new HashSet<Sairaus>();

	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Varaus> varaukset = new HashSet<Varaus>();

	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Resepti> reseptit = new HashSet<Resepti>();

	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Veriarvo> veriarvo = new HashSet<Veriarvo>();

	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Ilmoitus> ilmoitus = new HashSet<Ilmoitus>();

	@ManyToMany
	@JoinTable(name = "asiakkaanhenkilökunta", joinColumns = { @JoinColumn(name = "asiakasID") }, inverseJoinColumns = {
			@JoinColumn(name = "henkilökuntaID") })
	private Set<Henkilökunta> henkilökunnanjäsenet = new HashSet<Henkilökunta>();

	/**
	 * Tyhjä asiakas konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Asiakas(String, String, String, String, String, String, String)
	 */
	public Asiakas() {

	}

	/**
	 * Vakio asiakas konstruktori.
	 * 
	 * @param etunimi    Asiakkaan etunimi
	 * @param sukunimi   Asiakkaan sukunimi
	 * @param hetu       Asiakkaan henkilötunnus
	 * @param kotiosoite Asiakkaan kotiosoite
	 * @param sposti     Asiakkaan sähköpostiosoite
	 * @param puhnumero  Asiakkaan puhelinnumero
	 * @param icenumero  Asiakkaan "in case of emergency" puhelinnumero
	 * @see #Asiakas()
	 */
	public Asiakas(String etunimi, String sukunimi, String hetu, String kotiosoite, String sposti, String puhnumero,
			String icenumero) {
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.hetu = hetu;
		this.kotiosoite = kotiosoite;
		this.sposti = sposti;
		this.puhnumero = puhnumero;
		this.icenumero = icenumero;
	}

	/**
	 * Palauttaa asiakkaan asiakasID:n. AsiakasID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return asiakasID
	 */
	public int getAsiakasID() {
		return asiakasID;
	}

	/**
	 * Asettaa asiakkaan asiakasID:n. AsiakasID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden asiakkaan luomisessa.
	 * 
	 * @param asiakasID asiakkaalle annettava asiakasID
	 */
	public void setAsiakasID(int asiakasID) {
		this.asiakasID = asiakasID;
	}

	/**
	 * Palauttaa asiakkaan henkilötunnuksen.
	 * 
	 * @return henkilötunnus
	 */
	public String getHetu() {
		return hetu;
	}

	/**
	 * Asettaa asiakkaalle henkilötunnuksen.
	 * 
	 * @param hetu asiakkaalle asetettava henkilötunnus
	 */
	public void setHetu(String hetu) {
		this.hetu = hetu;
	}

	/**
	 * Palauttaa asiakkaan etunimen.
	 * 
	 * @return etunimi
	 */
	public String getEtunimi() {
		return etunimi;
	}

	/**
	 * Asettaa asiakkaalle etunimen.
	 * 
	 * @param etunimi asiakkaalle asetettava etunimi
	 */
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	/**
	 * Palauttaa asiakkaan sukunimen.
	 * 
	 * @return sukunimi
	 */
	public String getSukunimi() {
		return sukunimi;
	}

	/**
	 * Asettaa asiakkaalle sukunimen.
	 * 
	 * @param sukunimi asiakkaalle asetettava sukunimi
	 */
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	/**
	 * Palauttaa asiakkaan kotiosoitteen.
	 * 
	 * @return kotiosoite
	 */
	public String getKotiosoite() {
		return kotiosoite;
	}

	/**
	 * Asettaa asiakkaalle kotiosoitteen.
	 * 
	 * @param kotiosoite asiakkaalle asetettava kotiosoite
	 */
	public void setKotiosoite(String kotiosoite) {
		this.kotiosoite = kotiosoite;
	}

	/**
	 * Palauttaa asiakkaan sähköpostiosoitteen.
	 * 
	 * @return sähköpostiosoite
	 */
	public String getSposti() {
		return sposti;
	}

	/**
	 * Asettaa asiakkaalle sähköpostiosoitteen.
	 * 
	 * @param sposti asiakkaalle asetettava sähköpostiosoite
	 */
	public void setSposti(String sposti) {
		this.sposti = sposti;
	}

	/**
	 * Palauttaa asiakkaan puhelinnumeron.
	 * 
	 * @return puhelinnumero
	 */
	public String getPuhnumero() {
		return puhnumero;
	}

	/**
	 * Asettaa asiakkaalle sähköpostiosoitteen.
	 * 
	 * @param puhnumero asiakkaalle asetettava puhelinnumero
	 */
	public void setPuhnumero(String puhnumero) {
		this.puhnumero = puhnumero;
	}

	/**
	 * Palauttaa asiakkaan ICEnumeron ("in case of emergency" puhelinnumero).
	 * 
	 * @return ICEnumero
	 */
	public String getIcenumero() {
		return icenumero;
	}

	/**
	 * Asettaa asiakkaalle ICEnumeron ("in case of emergency" puhelinnumero).
	 * 
	 * @param icenumero asiakkaalle asetettava ICEnumero
	 */
	public void setIcenumero(String icenumero) {
		this.icenumero = icenumero;
	}
}
