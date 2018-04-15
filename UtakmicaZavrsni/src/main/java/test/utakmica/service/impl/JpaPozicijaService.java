package test.utakmica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.utakmica.model.Pozicija;
import test.utakmica.repository.PozicijaRepository;
import test.utakmica.service.PozicijaService;

@Service
public class JpaPozicijaService 
	implements PozicijaService{
	
	@Autowired
	private PozicijaRepository pozicijaRepository;

	@Override
	public List<Pozicija> findAll() {
		return pozicijaRepository.findAll();
	}

	@Override
	public void save(Pozicija pozicija) {
		pozicijaRepository.save(pozicija);
		
	}

	@Override
	public Pozicija findOne(Long pozicijaId) {
		return pozicijaRepository.findOne(pozicijaId);
	}

	
}
