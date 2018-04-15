package autobuska.stanica.service;

import autobuska.stanica.model.Kupovina;

public interface KupovinaService {
	
	Kupovina kupiKartu(Long id, int brojMesta);
}
