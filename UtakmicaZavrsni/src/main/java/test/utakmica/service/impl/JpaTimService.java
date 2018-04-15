package test.utakmica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.utakmica.model.Tim;
import test.utakmica.repository.TimRepository;
import test.utakmica.service.TimService;

@Service
@Transactional
public class JpaTimService 
	implements TimService {
	
	@Autowired
	private TimRepository timRepository;

	@Override
	public List<Tim> findAll() {
		return timRepository.findAll();
	}

	@Override
	public void save(Tim tim) {
		timRepository.save(tim);
	}

	@Override
	public Tim findOne(Long timId) {
		return timRepository.findOne(timId);
	}

	
}
