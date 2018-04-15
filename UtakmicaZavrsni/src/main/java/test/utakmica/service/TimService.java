package test.utakmica.service;

import java.util.List;

import test.utakmica.model.Tim;

public interface TimService {
	List<Tim> findAll();
	void save(Tim tim);
	Tim findOne(Long timId);
	
}
