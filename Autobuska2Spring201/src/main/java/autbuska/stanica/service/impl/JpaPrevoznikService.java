package autbuska.stanica.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autbuska.stanica.model.Prevoznik;
import autbuska.stanica.repository.PrevoznikRepository;
import autbuska.stanica.service.PrevoznikService;

@Service
@Transactional
public class JpaPrevoznikService 
	implements PrevoznikService {
	
	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public void save(Prevoznik prevoznik) {
		prevoznikRepository.save(prevoznik);
	}

	@Override
	public Prevoznik getOne(Long id) {
		return prevoznikRepository.getOne(id);
	}

	
}
