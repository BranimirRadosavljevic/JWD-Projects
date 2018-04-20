package vezba.apoteka.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import vezba.apoteka.model.Lek;

public interface LekService {
	
	Lek findOne(Long id);
	Page<Lek> findAll(int pageNum);
	void save(Lek lek);
	void delete(Long id);
	Page<Lek> findByApotekaId(Long apotekaId, int pageNum);
	Page<Lek> pretraga(
			@Param("apotekaId") Long apotekaId,
			@Param("nazivGenNaziv") String nazivGenNaziv,
			int pageNum);
	Lek kupiLek(Long id, Integer brojKomada);
}
