package com.ftninformatika.jwd.test.autobuska.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;
import com.ftninformatika.jwd.test.autobuska.repository.PrevoznikRepository;
import com.ftninformatika.jwd.test.autobuska.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {


	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik findOne(Long id) {
		// TODO Auto-generated method stub
		Optional <Prevoznik> prevoznik =  prevoznikRepository.findById(id);
		if(prevoznik.isPresent()) {
			return prevoznik.get(); 
		}else {
			return null; 
		}
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}


}
