package model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "asiakas")
public class Asiakas extends DAOManager {

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
	
	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Resepti> reseptit = new HashSet<Resepti>();
	
	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Veriarvo> veriarvo = new HashSet<Veriarvo>();
	
	@OneToMany(mappedBy = "asiakas", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Ilmoitus> ilmoitus = new HashSet<Ilmoitus>();
	
	@ManyToMany
	@JoinTable(name="asiakkaanhenkilökunta", 
		joinColumns= {@JoinColumn(name="asiakasID")},
		inverseJoinColumns= {@JoinColumn(name="henkilökuntaID")})
	private Set<Henkilökunta> henkilökunnanjäsenet = new HashSet<Henkilökunta>();
	
	
	public Asiakas() {

	}
	
	public void createAsiakas(Asiakas asiakas) {
		AsiakasAccessObject asiakasDAO = getAsiakasDAO();
		asiakasDAO.createAsiakas(asiakas);	
	}
	
	public Asiakas[] readAll() {
		AsiakasAccessObject asiakasDAO = getAsiakasDAO();
		return asiakasDAO.readAsiakkaat();
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
