package autobuska.stanica.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import autobuska.stanica.model.Linija;

public interface LinijaService {
	
	Linija getOne(Long id);
	Page<Linija> findAll(int pageNum);
	void save(Linija stand);
	void delete(Long id);
	Page<Linija> findByPrevoznikId(Long prevoznikId, int pageNum);
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija,
			@Param("maxCena") Float maxCena,
			@Param("prevoznikId") Long prevoznikId,
			int pageNum);
		
}
