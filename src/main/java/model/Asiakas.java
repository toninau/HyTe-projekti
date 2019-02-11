package model;

import javax.persistence.*;

@Entity
@Table(name = "Asiakas")
public class Asiakas {

	@Id
	@Column(name = "HeTu")
	private String heTu;

	@Column(name = "Etunimi")
	private String etunimi;

	@Column(name = "Sukunimi")
	private String sukunimi;

	@Column(name = "Kotiosoite")
	private String kotiosoite;

	@Column(name = "Puhelinnumero")
	private String puhnumero;

	@Column(name = "ICENumero")
	private String icenumero;

	public Asiakas(String heTu, String etunimi, String sukunimi, String kotiosoite, String puhnumero,
			String icenumero) {
		this.heTu = heTu;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.kotiosoite = kotiosoite;
		this.puhnumero = puhnumero;
		this.icenumero = icenumero;
	}

	public Asiakas() {

	}

	public String getHeTu() {
		return heTu;
	}

	public void setHeTu(String heTu) {
		this.heTu = heTu;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getKotiosoite() {
		return kotiosoite;
	}

	public void setKotiosoite(String kotiosoite) {
		this.kotiosoite = kotiosoite;
	}

	public String getPuhnumero() {
		return puhnumero;
	}

	public void setPuhnumero(String puhnumero) {
		this.puhnumero = puhnumero;
	}

	public String getIcenumero() {
		return icenumero;
	}

	public void setIcenumero(String icenumero) {
		this.icenumero = icenumero;
	}
}
