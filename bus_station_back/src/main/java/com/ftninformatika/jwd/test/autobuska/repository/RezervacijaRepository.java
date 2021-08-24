package com.ftninformatika.jwd.test.autobuska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;



@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {

	Rezervacija findOneById(Long id);

}
