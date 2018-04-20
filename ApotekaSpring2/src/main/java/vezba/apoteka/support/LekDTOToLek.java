package vezba.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Lek;
import vezba.apoteka.service.ApotekaService;
import vezba.apoteka.service.LekService;
import vezba.apoteka.service.ProizvodjacService;
import vezba.apoteka.web.dto.LekDTO;


@Component
public class LekDTOToLek implements Converter<LekDTO, Lek> {
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private ProizvodjacService proizvodjacService;

	@Override
	public Lek convert(LekDTO dto) {
		Lek lek;
		
		if(dto.getId()==null){
			lek = new Lek();
			lek.setProizvodjac(proizvodjacService.findOne(dto.getProizvodjacId()));
			lek.setApoteka(apotekaService.findOne(dto.getApotekaId()));
		} else {
			lek = lekService.findOne(dto.getId());
		}
		
		lek.setNaziv(dto.getNaziv());
		lek.setGenerickiNaziv(dto.getGenerickiNaziv());
		lek.setKolicinaNaStanju(dto.getKolicinaNaStanju());
		lek.setCena(dto.getCena());
		
		return lek;
	}
	
	public List<Lek> convert (List<LekDTO> dtos){
		List<Lek> lekovi = new ArrayList<>();
		
		for(LekDTO dto : dtos){
			lekovi.add(convert(dto));
		}
		
		return lekovi;
	}

}
