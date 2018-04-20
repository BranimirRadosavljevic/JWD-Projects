package vezba.apoteka.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vezba.apoteka.model.Apoteka;
import vezba.apoteka.repository.ApotekaRepository;
import vezba.apoteka.service.ApotekaService;

@Service
@Transactional
public class JpaApotekaService 
	implements ApotekaService {
	
	@Autowired
	private ApotekaRepository apotekaRepository;

	@Override
	public List<Apoteka> findAll() {
		return apotekaRepository.findAll();
	}

	@Override
	public void save(Apoteka sajam) {
		apotekaRepository.save(sajam);
	}

	@Override
	public Apoteka findOne(Long apotekaId) {
		return apotekaRepository.getOne(apotekaId);
	}

	
}
