package com.ftninformatika.jwd.test.autobuska.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;
import com.ftninformatika.jwd.test.autobuska.repository.RezervacijaRepository;
import com.ftninformatika.jwd.test.autobuska.service.LinijaService;
import com.ftninformatika.jwd.test.autobuska.service.RezervacijaService;



@Service
public class JpaRezervacijaService implements RezervacijaService {

	@Autowired
	private RezervacijaRepository rezervacijaRepository; 

	@Autowired
	private LinijaService linijaService;


	@Override
	public Rezervacija rezervisi(Rezervacija rezervacija) {
		Linija linija = linijaService.findOneById(rezervacija.getLinija().getId()); 
		Rezervacija rezervisana;
		if(linija.getBrojMesta() > 0) {
			rezervisana = rezervacijaRepository.save(rezervacija);
			linija.setBrojMesta(linija.getBrojMesta() - 1);
			linijaService.update(linija);
			return rezervisana;
		}
		return null;
	}



	@Override
	public List<Rezervacija> getAll() {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findAll();
	}

	@Override
	public Rezervacija findOne(Long id) {

		return rezervacijaRepository.findOneById(id);
	}



	@Override
	public Rezervacija save(Rezervacija rezervacija) {

		return rezervacijaRepository.save(rezervacija);
	}


}
