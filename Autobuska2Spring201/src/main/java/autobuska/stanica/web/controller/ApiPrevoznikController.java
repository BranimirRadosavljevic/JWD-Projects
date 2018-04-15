package autobuska.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autobuska.stanica.model.Linija;
import autobuska.stanica.model.Prevoznik;
import autobuska.stanica.service.LinijaService;
import autobuska.stanica.service.PrevoznikService;
import autobuska.stanica.support.LinijaToLinijaDTO;
import autobuska.stanica.support.PrevoznikDTOToPrevoznik;
import autobuska.stanica.support.PrevoznikToPrevoznikDTO;
import autobuska.stanica.web.dto.LinijaDTO;
import autobuska.stanica.web.dto.PrevoznikDTO;

@RestController
@RequestMapping("/api/prevoznici")
public class ApiPrevoznikController {
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;

	@Autowired
	private PrevoznikToPrevoznikDTO toPrevoznikDTO;

	@Autowired
	private PrevoznikDTOToPrevoznik toPrevoznik;
	
	@Autowired
	private LinijaToLinijaDTO toLinijaDTO;

	@GetMapping
	ResponseEntity<List<PrevoznikDTO>> getPrevoznici() {

		List<Prevoznik> prevoznici = prevoznikService.findAll();

		if (prevoznici == null || prevoznici.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toPrevoznikDTO.convert(prevoznici), HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	ResponseEntity<PrevoznikDTO> getPrevoznik(@PathVariable Long id) {
		Prevoznik prevoznik = prevoznikService.getOne(id);
		if (prevoznik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toPrevoznikDTO.convert(prevoznik), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{prevoznikId}/linije")
	ResponseEntity<List<LinijaDTO>> getLinije(@RequestParam(defaultValue = "0") int pageNum, 
			@PathVariable Long prevoznikId) {
		
		Page<Linija>linije = linijaService.findByPrevoznikId(prevoznikId, pageNum);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()));
		return new ResponseEntity<>(toLinijaDTO.convert(linije.getContent()), headers, HttpStatus.OK);
	}

	@PostMapping(consumes="application/json")
	public ResponseEntity<PrevoznikDTO> add(
			@RequestBody PrevoznikDTO noviPrevoznik){
		
		Prevoznik prevoznik = toPrevoznik.convert(noviPrevoznik);
		prevoznikService.save(prevoznik);
				
		return new ResponseEntity<>(
				toPrevoznikDTO.convert(prevoznik), 
				HttpStatus.CREATED);
	}

}
