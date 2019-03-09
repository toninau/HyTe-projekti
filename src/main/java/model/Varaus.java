package model;

import javax.persistence.*;

@Entity
@Table(name = "varaus")
public class Varaus extends DAOManager {

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

	public Varaus(String päivämäärä, String kellonaika, String info, Asiakas asiakas, Henkilökunta henkilökunta) {
		this.päivämäärä = päivämäärä;
		this.kellonaika = kellonaika;
		this.info = info;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}

	public Varaus() {
	}

	public int getVarausID() {
		return varausID;
	}

	public void setVarausID(int varausID) {
		this.varausID = varausID;
	}

	public String getPäivämäärä() {
		return päivämäärä;
	}

	public void setPäivämäärä(String päivämäärä) {
		this.päivämäärä = päivämäärä;
	}

	public String getKellonaika() {
		return kellonaika;
	}

	public void setKellonaika(String kellonaika) {
		this.kellonaika = kellonaika;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Asiakas getAsiakas() {
		return asiakas;
	}

	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}

	public Henkilökunta getHenkilökunta() {
		return henkilökunta;
	}

	public void setHenkilökunta(Henkilökunta henkilökunta) {
		this.henkilökunta = henkilökunta;
	}

}
