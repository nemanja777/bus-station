package com.ftninformatika.jwd.test.autobuska.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.test.autobuska.model.Linija;
import com.ftninformatika.jwd.test.autobuska.service.LinijaService;
import com.ftninformatika.jwd.test.autobuska.support.LinijaDtoToLinija;
import com.ftninformatika.jwd.test.autobuska.support.LinijaToLinijaDto;
import com.ftninformatika.jwd.test.autobuska.web.dto.LinijaDTO;




@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)

public class LinijaController {

	@Autowired
	private LinijaService linijaService; 

	@Autowired
	private LinijaToLinijaDto toLinijaDTO; 

	@Autowired
	private LinijaDtoToLinija toLinija; 


	// PAGINACIJA PRIKAZ SVIH I PRETRAGA
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	ResponseEntity<List<LinijaDTO>> get(@RequestParam(value = "destinacija", required = false)String destinacija,
			@RequestParam(value = "prevoznikId", required = false) Long prevoznikId,
			@RequestParam(value = "cenaKarte", required = false) Double cenaKarte,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		System.out.println("Prevoznik: " + prevoznikId + " - cenaKarte: " + cenaKarte + " -destinacija: " + destinacija);
		Page <Linija> page = null; 

		if(destinacija != null || prevoznikId != null || cenaKarte != null) {
			page = linijaService.search(destinacija, prevoznikId, cenaKarte, pageNo);

		}else {
			page = linijaService.all(pageNo);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toLinijaDTO.convert(page.getContent()), headers, HttpStatus.OK);
	}




	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<LinijaDTO> get(@PathVariable Long id){
		Linija linija = linijaService.findOne(id);

		if(linija != null) {
			return new ResponseEntity<>(toLinijaDTO.convert(linija), HttpStatus.OK); 
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDTO){
		Linija linija = toLinija.convert(linijaDTO);

		Linija sacuvana = linijaService.save(linija);


		return new ResponseEntity<>(toLinijaDTO.convert(sacuvana), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){
		if(!id.equals(linijaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija linija = toLinija.convert(linijaDTO);

		if(linija.getPrevoznik() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija izmenjena = linijaService.update(linija);

		return new ResponseEntity<> (toLinijaDTO.convert(izmenjena), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Linija obrisana = linijaService.delete(id);

		if(obrisana != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}


	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@RequestMapping(value = "/{id}/{korisnikId}/rezervisi", method = RequestMethod.POST)
	public ResponseEntity <List<LinijaDTO>> rezervisi(@PathVariable Long id, @PathVariable Long korisnikId) {


		Linija linija = linijaService.rezervisi(id,korisnikId);
		if(linija != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


}
