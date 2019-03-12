package model;

import javax.persistence.*;

/**
 * Veriarvo entity. Verenpaine ja verensokeri yhdistetty samaan luokkaan, mutta
 * molempia ei ole pakko antaa.
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "veriarvo")
public class Veriarvo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "veriarvoID")
	private int veriarvoID;

	@Column(name = "pvm")
	private String pvm;

	@Column(name = "aika")
	private String aika;

	@Column(name = "verensokeri")
	private double verensokeri;

	@Column(name = "verenpaine")
	private String verenpaine;

	@ManyToOne
	@JoinColumn(name = "asiakasID", nullable = false)
	private Asiakas asiakas;

	/**
	 * Vakio veriarvo konstruktori.
	 * 
	 * @param pvm         päivämäärä, jolloin veriarvot on mitattu
	 * @param aika        veriarvojen mittaus aika
	 * @param verensokeri verensokeri arvo
	 * @param verenpaine  verenpaine arvot
	 * @see #Veriarvo(String, String, double)
	 * @see #Veriarvo(String, String, String)
	 * @see #Veriarvo()
	 */
	public Veriarvo(String pvm, String aika, double verensokeri, String verenpaine) {
		this.pvm = pvm;
		this.aika = aika;
		this.verensokeri = verensokeri;
		this.verenpaine = verenpaine;
	}

	/**
	 * Veriarvo konstruktori, jos halutaan asettaa vain verensokeri.
	 * 
	 * @param pvm         päivämäärä, jolloin veriarvot on mitattu
	 * @param aika        veriarvojen mittaus aika
	 * @param verensokeri verensokeri arvo
	 * @see #Veriarvo(String, String, double, String)
	 * @see #Veriarvo(String, String, String)
	 * @see #Veriarvo()
	 */
	public Veriarvo(String pvm, String aika, double verensokeri) {
		this.verenpaine = null;
		this.pvm = pvm;
		this.aika = aika;
		this.verensokeri = verensokeri;
	}

	/**
	 * Veriarvo konstruktori, jos halutaan asettaa vain verenpaine.
	 * 
	 * @param pvm        päivämäärä, jolloin veriarvot on mitattu
	 * @param aika       veriarvojen mittaus aika
	 * @param verenpaine verenpaine arvot
	 * @see #Veriarvo(String, String, double, String)
	 * @see #Veriarvo(String, String, double)
	 * @see #Veriarvo()
	 */
	public Veriarvo(String pvm, String aika, String verenpaine) {
		this.verensokeri = 0;
		this.pvm = pvm;
		this.aika = aika;
		this.verenpaine = verenpaine;
	}

	/**
	 * Tyhjä veriarvo konstruktori.
	 * 
	 * @see #Veriarvo(String, String, double, String)
	 * @see #Veriarvo(String, String, double)
	 * @see #Veriarvo(String, String, String)
	 */
	public Veriarvo() {
		this.verenpaine = null;
		this.verensokeri = 0.0;
	}

	/**
	 * Palauttaa veriarvon veriarvoID:n. VeriarvoID-arvo määritellään
	 * automaattisesti tietokannassa.
	 * 
	 * @return veriarvoID
	 */
	public int getVeriarvoID() {
		return veriarvoID;
	}

	/**
	 * Asettaa veriarvon veriarvoID:n. VeriarvoID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden veriarvon luomisessa.
	 * 
	 * @param veriarvoID
	 */
	public void setVeriarvoID(int veriarvoID) {
		this.veriarvoID = veriarvoID;
	}

	/**
	 * Palauttaa veriarvon mittauspäivän (päivämäärä).
	 * 
	 * @return mittauspäivä
	 */
	public String getPvm() {
		return pvm;
	}

	/**
	 * Asettaa veriarvolle mittauspäivän (päivämäärä).
	 * 
	 * @param pvm veriarvolle asetettava mittauspäivä
	 */
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	/**
	 * Palauttaa veriarvon mittausajan (kellonaika).
	 * 
	 * @return mittausaika
	 */
	public String getAika() {
		return aika;
	}

	/**
	 * Asettaa veriarvolle mittausajan (kellonaika).
	 * 
	 * @param aika veriarvolle asetettava mittausaika
	 */
	public void setAika(String aika) {
		this.aika = aika;
	}

	/**
	 * Palauttaa verensokeriarvon.
	 * 
	 * @return verensokeri
	 */
	public double getVerensokeri() {
		return verensokeri;
	}

	/**
	 * Asettaa verensokeriarvon.
	 * 
	 * @param verensokeri asetettava verensokeriarvo
	 */
	public void setVerensokeri(double verensokeri) {
		this.verensokeri = verensokeri;
	}

	/**
	 * Palauttaa verenpainearvot.
	 * 
	 * @return verenpaine
	 */
	public String getVerenpaine() {
		return verenpaine;
	}

	/**
	 * Asettaa verenpainearvot.
	 * 
	 * @param verenpaine asetettava verepaine
	 */
	public void setVerenpaine(String verenpaine) {
		this.verenpaine = verenpaine;
	}

	/**
	 * Palauttaa veriarvon asiakkaan.
	 * 
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/**
	 * Asettaa veriarvon asiakkaan.
	 * 
	 * @param asiakas veriarvolle asetettava asiakas
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
}
