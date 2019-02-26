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
	
	@ManyToMany(mappedBy="henkilökunnanjäsenet")
	private Set<Asiakas> asiakkaat = new HashSet<Asiakas>();
	
	public Henkilökunta() {
		
	}

	public int getHenkilökuntaID() {
		return henkilökuntaID;
	}

	public void setHenkilökuntaID(int henkilökuntaID) {
		this.henkilökuntaID = henkilökuntaID;
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

	public String getPuhnumero() {
		return puhnumero;
	}

	public void setPuhnumero(String puhnumero) {
		this.puhnumero = puhnumero;
	}

	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
	}

	public String getOikeus() {
		return oikeus;
	}

	public void setOikeus(String oikeus) {
		this.oikeus = oikeus;
	}

	public Set<Asiakas> getAsiakkaat() {
		return asiakkaat;
	}

	public void setAsiakkaat(Set<Asiakas> asiakkaat) {
		this.asiakkaat = asiakkaat;
	}
}
