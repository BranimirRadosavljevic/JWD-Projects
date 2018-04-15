package autobuska.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autobuska.stanica.model.Kupovina;
import autobuska.stanica.model.Linija;
import autobuska.stanica.service.KupovinaService;
import autobuska.stanica.service.LinijaService;
import autobuska.stanica.support.KupovinaToKupovinaDTO;
import autobuska.stanica.support.LinijaDTOToLinija;
import autobuska.stanica.support.LinijaToLinijaDTO;
import autobuska.stanica.web.dto.KupovinaDTO;
import autobuska.stanica.web.dto.LinijaDTO;

@RestController
@RequestMapping("/api/linije")
public class ApiLinijaController {
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private LinijaDTOToLinija toLinija;
	
	@Autowired
	private LinijaToLinijaDTO toDTO;
	
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;

	
	@GetMapping
	public ResponseEntity<List<LinijaDTO>> get(@RequestParam(defaultValue="0") int pageNum,
				@RequestParam(required=false) String destinacija,
				@RequestParam(required=false) Float maxCena,
				@RequestParam(required=false) Long prevoznikId){
		
		Page<Linija> linije;
		
		if (destinacija != null || maxCena != null || prevoznikId !=null) {
			linije = linijaService.pretraga(destinacija, maxCena, prevoznikId, pageNum);
		} else {
			linije = linijaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDTO.convert(linije.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<LinijaDTO> get(@PathVariable Long id){
		Linija linija = linijaService.getOne(id);
		
		if (linija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(linija), 
				HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<LinijaDTO> add(
			@Validated @RequestBody LinijaDTO newLinija){
		
		Linija linija = toLinija.convert(newLinija);
		linijaService.save(linija);
				
		return new ResponseEntity<>(
				toDTO.convert(linija), 
				HttpStatus.CREATED);
	}
	@PostMapping(value="/{id}/kupovine", consumes="application/json")
	public ResponseEntity<KupovinaDTO> kupiKartu(@PathVariable Long id, 
												 @RequestBody Integer brojMesta){
	
		Kupovina k = kupovinaService.kupiKartu(id, brojMesta);
				
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
							
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<LinijaDTO> edit(
		@PathVariable Long id,
		@Validated @RequestBody LinijaDTO editedLinija){
	
		if(id==null || !id.equals(editedLinija.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		Linija converted = toLinija.convert(editedLinija);
		linijaService.save(converted);
		
		return new ResponseEntity<>(
				toDTO.convert(converted), 
				HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<LinijaDTO> delete(
			@PathVariable Long id){
		
		Linija linija = linijaService.getOne(id);
		if(linija != null) {
			linijaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
