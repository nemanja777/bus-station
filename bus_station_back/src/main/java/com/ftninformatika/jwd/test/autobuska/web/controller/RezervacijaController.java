package com.ftninformatika.jwd.test.autobuska.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.test.autobuska.model.Rezervacija;
import com.ftninformatika.jwd.test.autobuska.service.RezervacijaService;
import com.ftninformatika.jwd.test.autobuska.support.RezervacijaDtoToRezervacija;
import com.ftninformatika.jwd.test.autobuska.support.RezervacijaToRezervacijaDto;
import com.ftninformatika.jwd.test.autobuska.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)

public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService; 
	
	@Autowired
	private RezervacijaToRezervacijaDto toRezervacijaDto;
	
	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija; 
	
	@GetMapping
	public ResponseEntity<List<RezervacijaDTO>> getAll(){
		List<Rezervacija> rezervacije = rezervacijaService.getAll();
		
		return new ResponseEntity<>(toRezervacijaDto.convert(rezervacije), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rezervacijaDto){
		Rezervacija rezervacija = toRezervacija.convert(rezervacijaDto); 
		
		Rezervacija sacuvana = rezervacijaService.rezervisi(rezervacija);
		
		if(sacuvana == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toRezervacijaDto.convert(sacuvana),HttpStatus.CREATED);
		}
		
	}
	
	
	

}
