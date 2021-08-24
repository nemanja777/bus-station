package com.ftninformatika.jwd.test.autobuska.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.test.autobuska.model.Linija;



@Repository
public interface LinijaRepository extends JpaRepository<Linija,Long>{

	Linija findOneById(Long id);


	@Query("SELECT l FROM Linija l WHERE " +
			"(:destinacija = NULL OR l.destinacija LIKE :destinacija) AND " + //LIKE(ide samo za STRINGOVE) jer koristimo procente, = se stavlja kad hoces da mecujes ceo string ili sve sto je upisano u bazi!
			"(:prevoznikId = NULL OR l.prevoznik.id = :prevoznikId) AND " + 
			"(:cenaKarte = NULL OR l.cenaKarte <= :cenaKarte)")
	Page <Linija> search(@Param("destinacija") String destinacija,
			@Param("prevoznikId") Long prevoznikId, @Param("cenaKarte") Double cenaKarte, Pageable pageable);


}
