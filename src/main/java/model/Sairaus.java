package model;

import javax.persistence.*;

/**
 * Sairaus entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "sairaus")
public class Sairaus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sairausID")
	private int sairausID;

	@Column(name = "sairausNimi")
	private String sairausNimi;

	@ManyToOne
	@JoinColumn(name = "asiakasID", nullable = false)
	private Asiakas asiakas;

	/**
	 * Vakio sairaus konstruktori.
	 * 
	 * @param sairausNimi sairauden nimi
	 * @param asiakas     sairas asiakas
	 * @see #Sairaus()
	 */
	public Sairaus(String sairausNimi, Asiakas asiakas) {
		this.sairausNimi = sairausNimi;
		this.asiakas = asiakas;
	}

	/**
	 * Tyhjä sairaus konstruktori.
	 * 
	 * @see #Sairaus(String, Asiakas)
	 */
	public Sairaus() {
	}

	/**
	 * Palauttaa sairauden sairausID:n. SairausID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return sairausID
	 */
	public int getSairausID() {
		return sairausID;
	}

	/**
	 * Asettaa sairauden sairausID:n. SairausID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden sairauden luomisessa.
	 * 
	 * @param sairausID sairaudelle annettava sairausID
	 */
	public void setSairausID(int sairausID) {
		this.sairausID = sairausID;
	}

	/**
	 * Palauttaa sairauden nimen.
	 * 
	 * @return sairauden nimi
	 */
	public String getSairausNimi() {
		return sairausNimi;
	}

	/**
	 * Asettaa sairaudelle nimen.
	 * 
	 * @param sairausNimi sairaudelle asetettava nimi
	 */
	public void setSairausNimi(String sairausNimi) {
		this.sairausNimi = sairausNimi;
	}

	/**
	 * Palauttaa sairastavan henkilön (asiakkaan).
	 * 
	 * @return asiakas
	 */
	public Asiakas getAsiakas() {
		return asiakas;
	}

	/**
	 * Asettaa sairastavan asiakkaan sairaudelle.
	 * 
	 * @param asiakas sairaudelle asetettava asiakas
	 */
	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
}
