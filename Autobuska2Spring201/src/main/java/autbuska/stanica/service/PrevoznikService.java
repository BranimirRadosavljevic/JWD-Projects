package autbuska.stanica.service;

import java.util.List;

import autbuska.stanica.model.Prevoznik;

public interface PrevoznikService {
	Prevoznik getOne(Long id);
	List<Prevoznik> findAll();
	void save(Prevoznik sajam);
	
}
