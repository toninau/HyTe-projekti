package model;

import javax.persistence.*;

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

	public Sairaus(String sairausNimi, Asiakas asiakas) {
		this.sairausNimi = sairausNimi;
		this.asiakas = asiakas;
	}

	public Sairaus() {
	}

	public int getSairausID() {
		return sairausID;
	}

	public void setSairausID(int sairausID) {
		this.sairausID = sairausID;
	}

	public String getSairausNimi() {
		return sairausNimi;
	}

	public void setSairausNimi(String sairausNimi) {
		this.sairausNimi = sairausNimi;
	}

	public Asiakas getAsiakas() {
		return asiakas;
	}

	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
}
