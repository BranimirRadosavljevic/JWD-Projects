package test.utakmica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.utakmica.model.Igrac;
import test.utakmica.model.Tim;
import test.utakmica.service.IgracService;
import test.utakmica.service.TimService;
import test.utakmica.support.IgracToIgracDTO;
import test.utakmica.support.TimToTimDTO;
import test.utakmica.web.dto.IgracDTO;
import test.utakmica.web.dto.TimDTO;

@RestController
@RequestMapping("/api/timovi")
public class ApiTimController {
	@Autowired
	private TimService timService;
	
	@Autowired
	private IgracService igracService;

	@Autowired
	private TimToTimDTO toDTO;
	
	@Autowired
	private IgracToIgracDTO toIgracDTO;
	

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<TimDTO>> get() {

		List<Tim> timovi = timService.findAll();

		if (timovi == null || timovi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(timovi), HttpStatus.OK);

	}

	@RequestMapping(value="/{timId}/igraci", method = RequestMethod.GET)
	ResponseEntity<List<IgracDTO>> getIgraci(@RequestParam(defaultValue = "0") int pageNum, 
			@PathVariable Long timId) {
		
		Page<Igrac>igraci = igracService.findByTimId(timId, pageNum);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(igraci.getTotalPages()));
		return new ResponseEntity<>(toIgracDTO.convert(igraci.getContent()), headers, HttpStatus.OK);
	}
	
}
