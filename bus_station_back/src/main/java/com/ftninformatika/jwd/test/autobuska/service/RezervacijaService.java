package com.ftninformatika.jwd.test.autobuska.service;

import java.util.List;


import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;



public interface RezervacijaService {

	Rezervacija rezervisi(Rezervacija rezervacija);

	List<Rezervacija> getAll();

	Rezervacija save(Rezervacija rezervacija);

	Rezervacija findOne(Long id);

}
