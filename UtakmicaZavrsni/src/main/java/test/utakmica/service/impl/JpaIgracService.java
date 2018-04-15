package test.utakmica.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import test.utakmica.model.Igrac;
import test.utakmica.repository.IgracRepository;
import test.utakmica.service.IgracService;

@Service
@Transactional
public class JpaIgracService implements IgracService {
	
	@Autowired
	private IgracRepository igracRepository;

	@Override
	public Igrac findOne(Long id) {
		return igracRepository.findOne(id);
	}

	@Override
	public Page<Igrac> findAll(int pageNum) {
		return igracRepository.findAll(new PageRequest(pageNum, 6));
	}

	@Override
	public void save(Igrac igrac){
		if(igrac.getTim().getIgraci().size() <= 10) {
			igracRepository.save(igrac);
		} else {
			throw new IndexOutOfBoundsException("Tim može imati maksimalno 12 igrača.");
		}
	}

	@Override
	public void delete(Long id) {
		igracRepository.delete(id);
	}

	@Override
	public Page<Igrac> findByTimId(Long timId, int pageNum) {
		return igracRepository.findByTimId(timId, new PageRequest(pageNum, 6));
		 
	}

	@Override
	public Page<Igrac> pretraga(String imeIPrezime, Integer broj, Long timId, int pageNum) {
		if (imeIPrezime != null) {
			imeIPrezime = "%" + imeIPrezime + "%";
		}
				
		return igracRepository.pretraga(imeIPrezime, broj, timId, new PageRequest(pageNum, 6));
	}

	@Override
	public Igrac faul(Long igracId) {
		Igrac igracFaul = igracRepository.findOne(igracId);
		if (igracFaul.getLicneGreske() < 5) {
			igracFaul.setLicneGreske(igracFaul.getLicneGreske() + 1);
			igracFaul.setVanIgre(true);
		} else {
			return null;
		}
		
		igracRepository.save(igracFaul);
		
		return igracFaul;
	}


}
