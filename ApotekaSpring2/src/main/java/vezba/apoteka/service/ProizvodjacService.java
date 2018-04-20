package vezba.apoteka.service;

import java.util.List;

import vezba.apoteka.model.Proizvodjac;

public interface ProizvodjacService {

	List<Proizvodjac> findAll();
	void save(Proizvodjac proizvodjac);
	Proizvodjac findOne(Long proizvodjacId);
}
