package autbuska.stanica.service;

import autbuska.stanica.model.Kupovina;

public interface KupovinaService {
	
	Kupovina kupiKartu(Long id, int brojMesta);
}
