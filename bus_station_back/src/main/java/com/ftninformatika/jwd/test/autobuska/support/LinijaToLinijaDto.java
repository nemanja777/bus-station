package com.ftninformatika.jwd.test.autobuska.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.web.dto.LinijaDTO;
import com.ftninformatika.jwd.test.autobuska.web.dto.PrevoznikDTO;



@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO>{

	@Autowired
	private PrevoznikToPrevoznikDto toPrevoznikDTO; 

	@Override
	public LinijaDTO convert(Linija linija) {
		LinijaDTO dto = new LinijaDTO(); 
		dto.setId(linija.getId());
		dto.setBrojMesta(linija.getBrojMesta());
		dto.setCenaKarte(linija.getCenaKarte());
		dto.setVremePolaska(linija.getVremePolaska());
		dto.setDestinacija(linija.getDestinacija());

		PrevoznikDTO prevoznik = toPrevoznikDTO.convert(linija.getPrevoznik());

		dto.setPrevoznik(prevoznik);

		return dto; 
	}

	public List<LinijaDTO> convert(List<Linija> linije){
		List<LinijaDTO> linijeDTO = new ArrayList<>(); 

		for(Linija linija: linije) {
			linijeDTO.add(convert(linija));
		}
		return linijeDTO; 
	}


}
