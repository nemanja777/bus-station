package com.ftninformatika.jwd.test.autobuska.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;
import com.ftninformatika.jwd.test.autobuska.service.PrevoznikService;
import com.ftninformatika.jwd.test.autobuska.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter <PrevoznikDTO, Prevoznik> {

	@Autowired
	private PrevoznikService prevoznikService; 

	@Override
	public Prevoznik convert(PrevoznikDTO dto) {

		Prevoznik entity;

		if(dto.getId() == null) {
			entity = new Prevoznik();
		}else {
			entity = prevoznikService.findOne(dto.getId()); 
		}

		if(entity != null) {
			entity.setId(dto.getId());
			entity.setNaziv(dto.getNaziv());
			entity.setPIB(dto.getPIB());
			entity.setAdresa(dto.getAdresa());

		}

		return entity;
	}

}
