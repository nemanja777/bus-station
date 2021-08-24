package com.ftninformatika.jwd.test.autobuska.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int brojPutnika;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Linija linija;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Korisnik korisnik;

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Rezervacija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	public Linija getLinija() {
		return linija;
	}



	public void setLinija(Linija linija) {
		this.linija = linija;
		if(linija != null && !linija.getRezervacije().contains(this)) {
			linija.getRezervacije().add(this);
		}
	}







}
