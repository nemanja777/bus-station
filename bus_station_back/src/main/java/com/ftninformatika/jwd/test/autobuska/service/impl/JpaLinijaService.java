package com.ftninformatika.jwd.test.autobuska.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.test.autobuska.model.Korisnik;
import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;
import com.ftninformatika.jwd.test.autobuska.repository.KorisnikRepository;
import com.ftninformatika.jwd.test.autobuska.repository.LinijaRepository;
import com.ftninformatika.jwd.test.autobuska.repository.RezervacijaRepository;
import com.ftninformatika.jwd.test.autobuska.service.LinijaService;
import com.ftninformatika.jwd.test.autobuska.service.RezervacijaService;



@Service
public class JpaLinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private RezervacijaService rezervacijaService; 

	@Autowired
	private KorisnikRepository korisnikRepository; 



	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija); 
	}

	@Override
	public Linija findOneById(Long id) {

		return linijaRepository.findOneById(id);
	}

	//	@Override
	//	public Page<Linija> findAll(int pageNo) {
	//		
	//		return linijaRepository.findAll(PageRequest.of(pageNo, 3));
	//	}

	@Override
	public Linija findOne(Long id) {

		return linijaRepository.findOneById(id);
	}

	@Override
	public Linija update(Linija linija) {

		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {

		Optional<Linija> linija = linijaRepository.findById(id);
		if(linija.isPresent()){
			linijaRepository.deleteById(id);
			return linija.get();
		}
		return null;
	}

	//	@Override
	//	public List<Linija> find(String destinacija, Long prevoznikId, Double maxCenaKarte) {
	//		if(destinacija.equals("")) {
	//			System.out.println("TEEEEEEEEEEEEEEEEEEEEST TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEST !!!");
	//
	//		//	return linijaRepository.findByPrevoznikIdAndCenaKarteLessThanEqual(prevoznikId, maxCenaKarte); 
	//			return linijaRepository.findByDestinacija(destinacija); 
	//		}else if(prevoznikId == 0) {
	//			return linijaRepository.findByDestinacijaAndCenaKarteLessThanEqual(destinacija, maxCenaKarte);
	//		}else if(maxCenaKarte == 0) {
	//			return linijaRepository.findByDestinacijaAndPrevoznikId(destinacija, prevoznikId);
	//		}
	//		return linijaRepository.findByDestinacijaAndPrevoznikIdAndCenaKarteLessThanEqual(destinacija, prevoznikId, maxCenaKarte);
	//	}

	@Override
	public Linija rezervisi(Long linijaId, Long korisnikId) {
		Linija linija = linijaRepository.getOne(linijaId);

		if(linija.getBrojMesta() > 0) {
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setBrojPutnika(1);

			Korisnik korisnik = korisnikRepository.getOne(korisnikId);
			rezervacija.setKorisnik(korisnik);
			rezervacija.setLinija(linija);
			rezervacijaService.save(rezervacija);


			linija.setBrojMesta(linija.getBrojMesta() - 1);
			linijaService.update(linija);

			return linijaRepository.save(linija);
		}
		return null;
	}

	//	@Override
	//	public List<Linija> findByDestinacija(String destinacija) {
	//	
	//		return linijaRepository.findByDestinacija(destinacija);
	//	}

	@Override
	public Page<Linija> all(int page) {
		return linijaRepository.findAll(PageRequest.of(page, 2));
	}

	@Override
	public Page <Linija> search(String destinacija, Long prevoznikId, Double cenaKarte, int page){

		if(destinacija != null) {
			destinacija = "%" + destinacija + "%"; 
		}
		System.out.println("Prevoznik: " + prevoznikId + " - cenaKarte: " + cenaKarte + " -destinacija: " + destinacija);
		return linijaRepository.search(destinacija, prevoznikId, cenaKarte, PageRequest.of(page, 2)); 
	}

	//	@Override
	//	public Rezervacija rezervisi(Rezervacija rezervacija) {
	//		Linija linija = linijaService.findOneById(rezervacija.getLinija().getId()); 
	//		Rezervacija rezervisana;
	//		if(linija.getBrojMesta() > 0) {
	//			rezervisana = rezervacijaRepository.save(rezervacija);
	//			linija.setBrojMesta(linija.getBrojMesta() - 1);
	//			linijaService.update(linija);
	//			return rezervisana;
	//		}
	//		return null;
	//	}
}
