package test.utakmica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.utakmica.model.Pozicija;
import test.utakmica.service.PozicijaService;
import test.utakmica.support.PozicijaToPozicijaDTO;
import test.utakmica.web.dto.PozicijaDTO;

@RestController
@RequestMapping("/api/pozicije")
public class ApiPozicijaController {
	
	@Autowired
	private PozicijaService pozicijaService;
	@Autowired
	private PozicijaToPozicijaDTO toDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<PozicijaDTO>> get() {

		List<Pozicija> pozicije = pozicijaService.findAll();

		if (pozicije == null || pozicije.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(pozicije), HttpStatus.OK);

	}
	
	
	
}
