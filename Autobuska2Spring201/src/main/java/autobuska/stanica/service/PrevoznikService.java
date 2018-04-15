package autobuska.stanica.service;

import java.util.List;

import autobuska.stanica.model.Prevoznik;

public interface PrevoznikService {
	Prevoznik getOne(Long id);
	List<Prevoznik> findAll();
	void save(Prevoznik sajam);
	
}
