package model;

import javax.persistence.*;

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

	public Resepti(String alkupvm, String loppupvm, String reseptiNimi, String reseptiOhje, Asiakas asiakas,
			Henkilökunta henkilökunta) {
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.reseptiNimi = reseptiNimi;
		this.reseptiOhje = reseptiOhje;
		this.asiakas = asiakas;
		this.henkilökunta = henkilökunta;
	}
	
	public Resepti() {
	}

	public int getReseptiID() {
		return reseptiID;
	}

	public void setReseptiID(int reseptiID) {
		this.reseptiID = reseptiID;
	}

	public String getAlkupvm() {
		return alkupvm;
	}

	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}

	public String getLoppupvm() {
		return loppupvm;
	}

	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}

	public String getReseptiNimi() {
		return reseptiNimi;
	}

	public void setReseptiNimi(String reseptiNimi) {
		this.reseptiNimi = reseptiNimi;
	}

	public String getReseptiOhje() {
		return reseptiOhje;
	}

	public void setReseptiOhje(String reseptiOhje) {
		this.reseptiOhje = reseptiOhje;
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
