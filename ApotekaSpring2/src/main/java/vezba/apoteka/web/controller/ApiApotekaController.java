package vezba.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vezba.apoteka.model.Apoteka;
import vezba.apoteka.service.ApotekaService;
import vezba.apoteka.support.ApotekaToApotekaDTO;
import vezba.apoteka.web.dto.ApotekaDTO;

@RestController
@RequestMapping("/api/apoteke")
public class ApiApotekaController {
	@Autowired
	private ApotekaService apotekaService;

	@Autowired
	private ApotekaToApotekaDTO toDTO;

	@GetMapping
	ResponseEntity<List<ApotekaDTO>> get() {

		List<Apoteka> apoteke = apotekaService.findAll();

		if (apoteke == null || apoteke.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(apoteke), HttpStatus.OK);

	}

}
