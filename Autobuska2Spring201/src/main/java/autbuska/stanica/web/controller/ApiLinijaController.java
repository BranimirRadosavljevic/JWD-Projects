package autbuska.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autbuska.stanica.model.Kupovina;
import autbuska.stanica.model.Linija;
import autbuska.stanica.service.KupovinaService;
import autbuska.stanica.service.LinijaService;
import autbuska.stanica.support.KupovinaToKupovinaDTO;
import autbuska.stanica.support.LinijaDTOToLinija;
import autbuska.stanica.support.LinijaToLinijaDTO;
import autbuska.stanica.web.dto.KupovinaDTO;
import autbuska.stanica.web.dto.LinijaDTO;

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

	
	@RequestMapping(method=RequestMethod.GET)
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<LinijaDTO> get(@PathVariable Long id){
		Linija linija = linijaService.getOne(id);
		
		if (linija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(linija), 
				HttpStatus.OK);
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			consumes="application/json")
	public ResponseEntity<LinijaDTO> add(
			@Validated @RequestBody LinijaDTO newLinija){
		
		Linija linija = toLinija.convert(newLinija);
		linijaService.save(linija);
				
		return new ResponseEntity<>(
				toDTO.convert(linija), 
				HttpStatus.CREATED);
	}
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovine", consumes="application/json")
	public ResponseEntity<KupovinaDTO> kupiKartu(@PathVariable Long id, 
												 @RequestBody Integer brojMesta){
	
		Kupovina k = kupovinaService.kupiKartu(id, brojMesta);
				
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
							
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.PUT,
			consumes="application/json")
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
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
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
