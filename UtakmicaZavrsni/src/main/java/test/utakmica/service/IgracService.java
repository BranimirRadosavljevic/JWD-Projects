package test.utakmica.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import test.utakmica.model.Igrac;

public interface IgracService {
	
	Igrac findOne(Long id);
	Page<Igrac> findAll(int pageNum);
	void save(Igrac igrac);
	void delete(Long id);
	Page<Igrac> findByTimId(Long timId, int pageNum);
	Page<Igrac> pretraga(
			@Param("imeIPrezime") String imeIPrezime,
			@Param("broj") Integer broj,
			@Param("timId") Long timId,
			int pageNum);
	Igrac faul(Long igracId);
}
