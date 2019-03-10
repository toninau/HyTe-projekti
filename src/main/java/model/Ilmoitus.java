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

	public Ilmoitus(String pvm, String teksti, Asiakas asiakas, Henkilökunta henkilökunta) {
		this.pvm = pvm;
		this.teksti = teksti;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}

	public Ilmoitus() {

	}

	public int getIlmoitusID() {
		return ilmoitusID;
	}

	public void setIlmoitusID(int ilmoitusID) {
		this.ilmoitusID = ilmoitusID;
	}

	public String getPvm() {
		return pvm;
	}

	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	public String getTeksti() {
		return teksti;
	}

	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	public boolean isLuettu() {
		return luettu;
	}

	public void setLuettu(boolean luettu) {
		this.luettu = luettu;
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
