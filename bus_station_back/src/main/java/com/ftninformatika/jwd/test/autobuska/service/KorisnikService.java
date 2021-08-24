package com.ftninformatika.jwd.test.autobuska.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.test.autobuska.model.Korisnik;
import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.web.dto.KorisnikPromenaLozinkeDTO;

;

public interface KorisnikService {

	Korisnik findOne(Long id);

	Korisnik findOneById(Long id);

	List<Korisnik> findAll();

	Page<Korisnik> findAll(int brojStranice);

	Korisnik save(Korisnik korisnik);

	void delete(Long id);

	Korisnik findbyKorisnickoIme(String korisnickoIme);

	boolean changePassword(Long id, KorisnikPromenaLozinkeDTO korisnikPromenaLozinkeDto);
}
