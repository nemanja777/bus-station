package com.ftninformatika.jwd.test.autobuska.web.dto;

public class RezervacijaDTO {
	
	private Long id; 
	
	private int brojPutnika; 
	
	private LinijaDTO linija; 
	
	private KorisnikDTO korisnikDTO; 
	
	public KorisnikDTO getKorisnikDTO() {
		return korisnikDTO;
	}

	public void setKorisnikDTO(KorisnikDTO korisnikDTO) {
		this.korisnikDTO = korisnikDTO;
	}

	public RezervacijaDTO() {
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

	public LinijaDTO getLinija() {
		return linija;
	}

	public void setLinija(LinijaDTO linija) {
		this.linija = linija;
	}

}
