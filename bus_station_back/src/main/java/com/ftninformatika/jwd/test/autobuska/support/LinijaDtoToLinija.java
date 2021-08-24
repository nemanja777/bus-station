package com.ftninformatika.jwd.test.autobuska.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;
import com.ftninformatika.jwd.test.autobuska.service.LinijaService;
import com.ftninformatika.jwd.test.autobuska.web.dto.LinijaDTO;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO, Linija> {

	@Autowired
	private LinijaService linijaService; 

	@Autowired
	private PrevoznikDtoToPrevoznik toPrevoznik;

	@Override
	public Linija convert(LinijaDTO dto) {

		Linija linija;

		if(dto.getId() == null) {
			linija = new Linija();
		}else {
			linija = linijaService.findOneById(dto.getId());
		}

		if(linija != null) {
			linija.setId(dto.getId());
			linija.setBrojMesta(dto.getBrojMesta());
			linija.setCenaKarte(dto.getCenaKarte());
			linija.setVremePolaska(dto.getVremePolaska());
			linija.setDestinacija(dto.getDestinacija());

			Prevoznik prevoznik = toPrevoznik.convert(dto.getPrevoznik());

			linija.setPrevoznik(prevoznik);
		}
		return linija; 
	}

}
