package test.utakmica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.utakmica.model.Pozicija;
import test.utakmica.web.dto.PozicijaDTO;

@Component
public class PozicijaToPozicijaDTO implements Converter<Pozicija, PozicijaDTO> {

	@Override
	public PozicijaDTO convert(Pozicija pozicija) {
		PozicijaDTO dto = new PozicijaDTO();
		
		dto.setId(pozicija.getId());
		dto.setNaziv(pozicija.getNaziv());
		
		return dto;
	}
	
	public List<PozicijaDTO> convert(List<Pozicija> pozicije){
		List<PozicijaDTO> ret = new ArrayList<>();
		
		for(Pozicija p : pozicije){
			ret.add(convert(p));
		}
		
		return ret;
	}
}
