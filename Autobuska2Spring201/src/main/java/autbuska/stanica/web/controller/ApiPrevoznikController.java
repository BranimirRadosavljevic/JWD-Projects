package autbuska.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autbuska.stanica.model.Linija;
import autbuska.stanica.model.Prevoznik;
import autbuska.stanica.service.LinijaService;
import autbuska.stanica.service.PrevoznikService;
import autbuska.stanica.support.LinijaToLinijaDTO;
import autbuska.stanica.support.PrevoznikDTOToPrevoznik;
import autbuska.stanica.support.PrevoznikToPrevoznikDTO;
import autbuska.stanica.web.dto.LinijaDTO;
import autbuska.stanica.web.dto.PrevoznikDTO;

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

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<PrevoznikDTO>> getPrevoznici() {

		List<Prevoznik> prevoznici = prevoznikService.findAll();

		if (prevoznici == null || prevoznici.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toPrevoznikDTO.convert(prevoznici), HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<PrevoznikDTO> getPrevoznik(@PathVariable Long id) {
		Prevoznik prevoznik = prevoznikService.getOne(id);
		if (prevoznik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toPrevoznikDTO.convert(prevoznik), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{prevoznikId}/linije", method = RequestMethod.GET)
	ResponseEntity<List<LinijaDTO>> getLinije(@RequestParam(defaultValue = "0") int pageNum, 
			@PathVariable Long prevoznikId) {
		
		Page<Linija>linije = linijaService.findByPrevoznikId(prevoznikId, pageNum);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()));
		return new ResponseEntity<>(toLinijaDTO.convert(linije.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(
			method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<PrevoznikDTO> add(
			@RequestBody PrevoznikDTO noviPrevoznik){
		
		Prevoznik prevoznik = toPrevoznik.convert(noviPrevoznik);
		prevoznikService.save(prevoznik);
				
		return new ResponseEntity<>(
				toPrevoznikDTO.convert(prevoznik), 
				HttpStatus.CREATED);
	}

}
