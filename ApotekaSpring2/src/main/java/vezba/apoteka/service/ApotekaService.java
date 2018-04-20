package vezba.apoteka.service;

import java.util.List;

import vezba.apoteka.model.Apoteka;

public interface ApotekaService {
	List<Apoteka> findAll();
	void save(Apoteka apoteka);
	Apoteka findOne(Long apotekaId);
	
}
