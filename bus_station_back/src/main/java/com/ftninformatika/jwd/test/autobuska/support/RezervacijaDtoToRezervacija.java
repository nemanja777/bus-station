package com.ftninformatika.jwd.test.autobuska.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;
import com.ftninformatika.jwd.test.autobuska.service.KorisnikService;
import com.ftninformatika.jwd.test.autobuska.service.LinijaService;
import com.ftninformatika.jwd.test.autobuska.service.RezervacijaService;
import com.ftninformatika.jwd.test.autobuska.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

	@Autowired
	private RezervacijaService rezervacijaService;

	@Autowired
	private KorisnikService korisnikService; 

	@Autowired
	private LinijaService linijaService;


	@Override
	public Rezervacija convert(RezervacijaDTO dto) {

		Rezervacija rezervacija;

		if(dto.getId() == null) {
			rezervacija = new Rezervacija();
		}else {
			rezervacija = rezervacijaService.findOne(dto.getId());
		}

		if(rezervacija != null) {
			rezervacija.setId(dto.getId());
			rezervacija.setBrojPutnika(dto.getBrojPutnika());
			rezervacija.setLinija(linijaService.findOne(dto.getLinija().getId()));
			rezervacija.setKorisnik(korisnikService.findOne(dto.getKorisnikDTO().getId()));

		}

		return rezervacija;



	}

	//	@Override
	//	public Rezervacija convert(RezervacijaDTO dto) {
	//		Rezervacija rezervacija = new Rezervacija(); 
	//
	//		rezervacija.setId(dto.getId());
	//		rezervacija.setBrojPutnika(dto.getBrojPutnika());
	//		rezervacija.setLinija(toLinija.convert(dto.getLinija()));
	//
	//		return rezervacija; 
	//	} 



}
