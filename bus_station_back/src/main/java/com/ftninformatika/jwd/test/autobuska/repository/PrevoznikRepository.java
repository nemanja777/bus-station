package com.ftninformatika.jwd.test.autobuska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.test.autobuska.model.Prevoznik;



@Repository
public interface PrevoznikRepository extends JpaRepository<Prevoznik, Long> {

}
