package com.ftninformatika.jwd.test.autobuska.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.test.autobuska.model.Korisnik;
import com.ftninformatika.jwd.test.autobuska.model.Linija;



@Repository
public interface KorisnikRepository  extends JpaRepository<Korisnik, Long>{


	Korisnik findOneById(Long id);

	Korisnik findFirstByKorisnickoIme(String korisnickoIme);

	Korisnik findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);

}
