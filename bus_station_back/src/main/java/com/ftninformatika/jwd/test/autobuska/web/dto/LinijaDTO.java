package com.ftninformatika.jwd.test.autobuska.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LinijaDTO {
	
	private Long id; 
	
	@Positive(message = "Broj mesta mora biti pozitivan broj")
	private int brojMesta; 
	
	private double cenaKarte; 
	
	private String vremePolaska; 
	
	@NotEmpty(message = "Destinacija ne moze biti prazna!")
	@NotNull(message = "Destinacija ne moze biti prazna!")
	private String destinacija;
	
	
	private PrevoznikDTO prevoznik;


	public LinijaDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getBrojMesta() {
		return brojMesta;
	}


	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}


	public double getCenaKarte() {
		return cenaKarte;
	}


	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}


	public String getVremePolaska() {
		return vremePolaska;
	}


	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}


	public String getDestinacija() {
		return destinacija;
	}


	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}


	public PrevoznikDTO getPrevoznik() {
		return prevoznik;
	}


	public void setPrevoznik(PrevoznikDTO prevoznik) {
		this.prevoznik = prevoznik;
	} 
	
	
	
	

}
