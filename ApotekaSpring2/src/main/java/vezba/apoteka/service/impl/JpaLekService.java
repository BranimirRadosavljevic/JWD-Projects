package vezba.apoteka.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vezba.apoteka.model.Lek;
import vezba.apoteka.repository.LekRepository;
import vezba.apoteka.service.LekService;

@Service
@Transactional
public class JpaLekService implements LekService {
	
	@Autowired
	private LekRepository lekRepository;

	@Override
	public Lek findOne(Long id) {
		return lekRepository.getOne(id);
	}

	@Override
	public Page<Lek> findAll(int pageNum) {
		return lekRepository.findAll(PageRequest.of(pageNum, 5));
	}

	@Override
	public void save(Lek lek) {
		lekRepository.save(lek);
	}

	@Override
	public void delete(Long id) {
		lekRepository.deleteById(id);
	}

	@Override
	public Page<Lek> findByApotekaId(Long apotekaId, int pageNum) {
		return lekRepository.findByApotekaId(apotekaId, PageRequest.of(pageNum, 5));
		 
	}

	@Override
	public Page<Lek> pretraga(Long apotekaId, String nazivGenNaziv, int pageNum) {
		if (nazivGenNaziv != null) {
			nazivGenNaziv = "%" + nazivGenNaziv + "%";
		}
				
		return lekRepository.pretraga(apotekaId, nazivGenNaziv, PageRequest.of(pageNum, 5));
	}

	@Override
	public Lek kupiLek(Long id, Integer brojKomada) {
		Lek lekZaKupovinu = lekRepository.getOne(id);
			if(lekZaKupovinu.getKolicinaNaStanju() > 0 && lekZaKupovinu.getKolicinaNaStanju() >= brojKomada) {
			
			lekZaKupovinu.setKolicinaNaStanju(lekZaKupovinu.getKolicinaNaStanju() - brojKomada);
						
			lekRepository.save(lekZaKupovinu);
			
			return lekZaKupovinu;
		}
		return null;
		
	}

}
