package test.utakmica.service;

import java.util.List;

import test.utakmica.model.Pozicija;

public interface PozicijaService {

	List<Pozicija> findAll();
	void save(Pozicija pozicija);
	Pozicija findOne(Long pozicijaId);
}
