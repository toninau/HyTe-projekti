package model;

import javax.persistence.*;

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

	public Veriarvo() {
		this.verenpaine = null;
		this.verensokeri = 0.0;
	}

	public int getVeriarvoID() {
		return veriarvoID;
	}

	public void setVeriarvoID(int veriarvoID) {
		this.veriarvoID = veriarvoID;
	}

	public String getPvm() {
		return pvm;
	}

	public void setPvm(String pvm) {
		this.pvm = pvm;
	}
	
	public String getAika() {
		return aika;
	}

	public void setAika(String aika) {
		this.aika = aika;
	}

	public double getVerensokeri() {
		return verensokeri;
	}

	public void setVerensokeri(double verensokeri) {
		this.verensokeri = verensokeri;
	}

	public String getVerenpaine() {
		return verenpaine;
	}

	public void setVerenpaine(String verenpaine) {
		this.verenpaine = verenpaine;
	}

	public Asiakas getAsiakas() {
		return asiakas;
	}

	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
}
