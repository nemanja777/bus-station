package com.ftninformatika.jwd.test.autobuska.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;
import com.ftninformatika.jwd.test.autobuska.service.PrevoznikService;
import com.ftninformatika.jwd.test.autobuska.support.PrevoznikDtoToPrevoznik;
import com.ftninformatika.jwd.test.autobuska.support.PrevoznikToPrevoznikDto;
import com.ftninformatika.jwd.test.autobuska.web.dto.PrevoznikDTO;



@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE )

public class PrevoznikController {
	
	@Autowired
	private PrevoznikToPrevoznikDto toPrevoznikDTO; 
	
	@Autowired
	private PrevoznikService prevoznikService; 
	
	@Autowired
	private PrevoznikDtoToPrevoznik toPrevoznik; 
	
//	 @GetMapping
//	    public ResponseEntity<List<PrevoznikDTO>> getAll(){
//	        List<Prevoznik> svi = prevoznikService.findAll();
//	        return new ResponseEntity<>(toPrevoznikDTO.convert(svi), HttpStatus.OK);
//	    }
	
	//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping
	    public ResponseEntity<List<PrevoznikDTO>> getAll(){
	   System.out.println("AAAAAAAAAAAAAAAAAAA!!!!!");     
		 List<Prevoznik> prevoznici = prevoznikService.findAll();
		 List<PrevoznikDTO> prevozniciDTO = toPrevoznikDTO.convert(prevoznici); 
		 
		 

	        if(prevozniciDTO.isEmpty()) {
	        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	           
	        }
	        else {
	        	 return new ResponseEntity<>(prevozniciDTO, HttpStatus.OK);
	        }
	    }
	 
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<PrevoznikDTO> create(@Valid @RequestBody PrevoznikDTO prevoznikDTO){
		 Prevoznik prevoznik = toPrevoznik.convert(prevoznikDTO);
		 Prevoznik sacuvan = prevoznikService.save(prevoznik); 
		 
		 return new ResponseEntity<>(toPrevoznikDTO.convert(sacuvan), HttpStatus.CREATED); 
		 
	 }
	 
	 
	 
	 

}
