package com.ftninformatika.jwd.test.autobuska.service;

import java.util.List;

import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;



public interface PrevoznikService {

	List<Prevoznik> findAll();

	Prevoznik findOne(Long id);

	Prevoznik save(Prevoznik prevoznik);

}
