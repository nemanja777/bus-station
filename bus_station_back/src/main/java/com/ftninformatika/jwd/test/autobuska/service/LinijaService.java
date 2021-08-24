package com.ftninformatika.jwd.test.autobuska.service;




import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.test.autobuska.model.Linija;

public interface LinijaService {

	Linija save(Linija linija);

	Linija findOneById(Long id);

	Linija findOne(Long id);

	Linija update(Linija linija);

	Linija delete(Long id);

	Page <Linija> search(String destinacija, Long prevoznikId, Double cenaKarte, int pageNum); 

	Page <Linija> all(int page);

	Linija rezervisi(Long id,Long korisnikId);
}
