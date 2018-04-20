package vezba.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vezba.apoteka.model.Lek;
import vezba.apoteka.service.LekService;
import vezba.apoteka.support.LekDTOToLek;
import vezba.apoteka.support.LekToLekDTO;
import vezba.apoteka.web.dto.LekDTO;

@Controller
@RequestMapping("/api/lekovi")
public class ApiLekController {
	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekDTOToLek toLek;
	
	@Autowired
	private LekToLekDTO toDTO;

	@GetMapping
	public ResponseEntity<List<LekDTO>> get(
			@RequestParam(required=false) Long apotekaId,
			@RequestParam(required=false) String nazivGenNaziv,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Lek> lekovi; 
		
		if (nazivGenNaziv != null || apotekaId !=null) {
			lekovi = lekService.pretraga(apotekaId, nazivGenNaziv, pageNum);
		} else {
			lekovi = lekService.findAll(pageNum);
		}
				
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(lekovi.getTotalPages()));
		
		return new ResponseEntity<>(
				toDTO.convert(lekovi.getContent()), 
				headers,
				HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<LekDTO> get(@PathVariable Long id){
		Lek lek = lekService.findOne(id);
		
		if (lek == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(lek), 
				HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<LekDTO> add(@Validated @RequestBody LekDTO newLek){
		
		Lek lek = toLek.convert(newLek);
		lekService.save(lek);
				
		return new ResponseEntity<>(
				toDTO.convert(lek), 
				HttpStatus.CREATED);
	}
	
	@PostMapping(value="/{lekId}/kupovina", consumes="application/json")
	public ResponseEntity<LekDTO> kupi(@PathVariable Long lekId,
									   @RequestBody Integer brojKomada){
		
		Lek lek = lekService.kupiLek(lekId, brojKomada);
		
		if (lek == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(lek), 
				HttpStatus.CREATED);
	}
	
	@PutMapping(
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<LekDTO> edit(
		@PathVariable Long id,
		@Validated @RequestBody LekDTO editedLek){
	
		if(id==null || !id.equals(editedLek.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		Lek converted = toLek.convert(editedLek);
		lekService.save(converted);
		
		return new ResponseEntity<>(
				toDTO.convert(converted), 
				HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<LekDTO> delete(
			@PathVariable Long id){
		
		Lek lek = lekService.findOne(id);
		if(lek != null) {
			lekService.delete(id);
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
