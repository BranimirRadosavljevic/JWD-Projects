package test.utakmica.web.controller;

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

import test.utakmica.model.Igrac;
import test.utakmica.service.IgracService;
import test.utakmica.support.IgracDTOToIgrac;
import test.utakmica.support.IgracToIgracDTO;
import test.utakmica.web.dto.IgracDTO;

@RestController
@RequestMapping("/api/igraci")
public class ApiIgracController {
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private IgracDTOToIgrac toIgrac;
	
	@Autowired
	private IgracToIgracDTO toDTO;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<IgracDTO>> get(
			@RequestParam(required=false) String imeIPrezime,
			@RequestParam(required=false) Integer broj,
			@RequestParam(required=false) Long timId,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Igrac> igraci; 
		
		if (imeIPrezime != null || broj != null || timId !=null) {
			igraci = igracService.pretraga(imeIPrezime, broj, timId, pageNum);
		} else {
			igraci = igracService.findAll(pageNum);
		}
				
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(igraci.getTotalPages()));
		
		return new ResponseEntity<>(
				toDTO.convert(igraci.getContent()), 
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<IgracDTO> get(@PathVariable Long id){
		Igrac igrac = igracService.findOne(id);
		
		if (igrac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(igrac), 
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<IgracDTO> add(@Validated @RequestBody IgracDTO noviIgrac){
		
		Igrac igrac = toIgrac.convert(noviIgrac);
		try {
			igracService.save(igrac);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
				
		return new ResponseEntity<>(
				toDTO.convert(igrac), 
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{igracId}/faul", method=RequestMethod.POST)
	public ResponseEntity<IgracDTO> faul(@PathVariable Long igracId){
		
		Igrac igrac = igracService.faul(igracId);
		
		if (igrac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(igrac), 
				HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value="/{id}",
			method=RequestMethod.PUT,
			consumes="application/json")
	public ResponseEntity<IgracDTO> edit(
		@PathVariable Long id,
		@Validated @RequestBody IgracDTO editedIgrac){
	
		if(id==null || !id.equals(editedIgrac.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		Igrac converted = toIgrac.convert(editedIgrac);
		
		igracService.save(converted);
		
		return new ResponseEntity<>(
				toDTO.convert(converted), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<IgracDTO> delete(
			@PathVariable Long id){
		
		Igrac igrac = igracService.findOne(id);
		if(igrac != null) {
			igracService.delete(id);
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
