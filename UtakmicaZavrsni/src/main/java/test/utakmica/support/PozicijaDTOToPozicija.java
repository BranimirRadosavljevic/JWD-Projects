package test.utakmica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.utakmica.model.Pozicija;
import test.utakmica.service.PozicijaService;
import test.utakmica.web.dto.PozicijaDTO;


@Component
public class PozicijaDTOToPozicija implements Converter<PozicijaDTO, Pozicija> {
	
	@Autowired
	private PozicijaService pozicijaService;

	@Override
	public Pozicija convert(PozicijaDTO dto) {
		Pozicija pozicija;
		
		if(dto.getId()==null){
			pozicija = new Pozicija();
			
		} else {
			pozicija = pozicijaService.findOne(dto.getId());
		}
		
		pozicija.setNaziv(dto.getNaziv());
				
		return pozicija;
	}
	
	public List<Pozicija> convert (List<PozicijaDTO> dtos){
		List<Pozicija> retVal = new ArrayList<>();
		
		for(PozicijaDTO dto : dtos){
			retVal.add(convert(dto));
		}
		
		return retVal;
	}

}
