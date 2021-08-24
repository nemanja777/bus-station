package com.ftninformatika.jwd.test.autobuska.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;
import com.ftninformatika.jwd.test.autobuska.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		
		PrevoznikDTO dto = new PrevoznikDTO(); 
		dto.setId(prevoznik.getId());
		dto.setNaziv(prevoznik.getNaziv());
		dto.setAdresa(prevoznik.getAdresa());
		dto.setPIB(prevoznik.getPIB());
		
		// TODO dodaj listu linija 
		return dto; 
	}
	
	 public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
	        List<PrevoznikDTO> prevozniciDTO = new ArrayList<>();

	        for(Prevoznik prevoznik: prevoznici) {
	            prevozniciDTO.add(convert(prevoznik));
	        }

	        return prevozniciDTO;
	    }

}
