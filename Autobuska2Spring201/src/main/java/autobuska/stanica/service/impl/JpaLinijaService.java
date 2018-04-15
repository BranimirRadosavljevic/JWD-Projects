package autobuska.stanica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import autobuska.stanica.model.Linija;
import autobuska.stanica.repository.LinijaRepository;
import autobuska.stanica.service.LinijaService;

@Service
public class JpaLinijaService 
	implements LinijaService{
	
	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public Page<Linija> findAll(int pageNum) {
		return linijaRepository.findAll(new PageRequest(pageNum, 5));
		 
	}
	
	@Override
	public Linija getOne(Long id) {
		return linijaRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		linijaRepository.deleteById(id);		
	}


	@Override
	public void save(Linija linija) {
		linijaRepository.save(linija);
	}

	@Override
	public Page<Linija> pretraga(String destinacija, Float maxCena, Long prevoznikId, int pageNum) {
		
		if (destinacija != null) {
			destinacija = "%" + destinacija + "%";
		}
		
		return linijaRepository.pretraga(destinacija, maxCena, prevoznikId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Linija> findByPrevoznikId(Long prevoznikId ,int pageNum) {
		return linijaRepository.findByPrevoznikId(prevoznikId, new PageRequest(pageNum, 5));
	}

}
