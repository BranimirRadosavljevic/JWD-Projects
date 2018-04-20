package vezba.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vezba.apoteka.model.Proizvodjac;
import vezba.apoteka.service.ProizvodjacService;
import vezba.apoteka.support.ProizvodjacToProizvodjacDTO;
import vezba.apoteka.web.dto.ProizvodjacDTO;

@RestController
@RequestMapping("/api/proizvodjaci")
public class ApiProizvodjacController {
	
	@Autowired
	private ProizvodjacService proizvodjacService;
	@Autowired
	private ProizvodjacToProizvodjacDTO toDTO;
	
	@GetMapping
	ResponseEntity<List<ProizvodjacDTO>> get() {

		List<Proizvodjac> proizvodjaci = proizvodjacService.findAll();

		if (proizvodjaci == null || proizvodjaci.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(proizvodjaci), HttpStatus.OK);

	}
	
}
