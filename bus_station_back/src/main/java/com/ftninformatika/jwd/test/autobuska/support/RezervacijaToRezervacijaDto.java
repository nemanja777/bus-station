package com.ftninformatika.jwd.test.autobuska.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;
import com.ftninformatika.jwd.test.autobuska.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

	@Autowired
	private LinijaToLinijaDto toLinijaDto;

	@Autowired
	private KorisnikToKorisnikDto toKorisnikDto; 

	@Override
	public RezervacijaDTO convert(Rezervacija source) {
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(source.getId());
		dto.setBrojPutnika(source.getBrojPutnika());
		dto.setLinija(toLinijaDto.convert(source.getLinija()));
		dto.setKorisnikDTO(toKorisnikDto.convert(source.getKorisnik()));
		return dto;
	} 

	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
		List<RezervacijaDTO> dto = new ArrayList<>(); 

		for(Rezervacija rezervacija: rezervacije) {
			dto.add(convert(rezervacija)); 
		}
		return dto; 
	}



}
