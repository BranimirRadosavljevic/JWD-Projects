package vezba.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vezba.apoteka.model.Proizvodjac;
import vezba.apoteka.repository.ProizvodjacRepository;
import vezba.apoteka.service.ProizvodjacService;

@Service
public class JpaProizvodjacService 
	implements ProizvodjacService{
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepository;

	@Override
	public List<Proizvodjac> findAll() {
		return proizvodjacRepository.findAll();
	}

	@Override
	public void save(Proizvodjac proizvodjac) {
		proizvodjacRepository.save(proizvodjac);
		
	}

	@Override
	public Proizvodjac findOne(Long proizvodjacId) {
		return proizvodjacRepository.getOne(proizvodjacId);
	}

	
}
