package autobuska.stanica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autobuska.stanica.model.Kupovina;
import autobuska.stanica.model.Linija;
import autobuska.stanica.repository.KupovinaRepository;
import autobuska.stanica.repository.LinijaRepository;
import autobuska.stanica.service.KupovinaService;

@Service
public class JpaKupovinaService 
	implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository;
	@Autowired
	private LinijaRepository linijaRepository;

	@Override
	public Kupovina kupiKartu(Long linijaId, int brojMesta) {
		if(linijaId == null) {
			throw new IllegalArgumentException("Id of a line cannot be null!");
		}
		
		Linija linija = linijaRepository.getOne(linijaId);
		if(linija == null) {
			throw new IllegalArgumentException("There is no line with given id!");
		}
		
		if(linija.getBrojMesta() > 0 && linija.getBrojMesta() >= brojMesta) {
			
			Kupovina kupovina = new Kupovina();
			kupovina.setLinija(linija);
			kupovina.setBrojKarata(brojMesta);
			
			linija.setBrojMesta(linija.getBrojMesta() - brojMesta);
			
			kupovinaRepository.save(kupovina);
			linijaRepository.save(linija);
			
			return kupovina;
		}
		return null;
	}
	
	
}
