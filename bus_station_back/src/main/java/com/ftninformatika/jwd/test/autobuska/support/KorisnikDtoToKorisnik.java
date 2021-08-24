package com.ftninformatika.jwd.test.autobuska.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Korisnik;
import com.ftninformatika.jwd.test.autobuska.service.KorisnikService;
import com.ftninformatika.jwd.test.autobuska.web.dto.KorisnikDTO;



@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik> {

	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO korisnikDTO) {
		Korisnik korisnik = null;
		if(korisnikDTO.getId() != null) {
			korisnik = korisnikService.findOne(korisnikDTO.getId());
		}

		if(korisnik == null) {
			korisnik = new Korisnik();
		}

		korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
		korisnik.seteMail(korisnikDTO.geteMail());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());


		return korisnik;
	}



}
