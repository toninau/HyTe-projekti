package model;

import javax.persistence.*;
import java.util.*;

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
	
	@ManyToMany
	@JoinTable(name="asiakkaanHenkilökunta", 
		joinColumns= {@JoinColumn(name="AsiakasID")},
		inverseJoinColumns= {@JoinColumn(name="HenkilökuntaID")})
	private Set<Henkilökunta> henkilökunnanjäsenet = new HashSet<Henkilökunta>();

	public Asiakas() {

	}

	public int getAsiakasID() {
		return asiakasID;
	}

	public void setAsiakasID(int asiakasID) {
		this.asiakasID = asiakasID;
	}

	public String getHetu() {
		return hetu;
	}

	public void setHetu(String hetu) {
		this.hetu = hetu;
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

	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
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
