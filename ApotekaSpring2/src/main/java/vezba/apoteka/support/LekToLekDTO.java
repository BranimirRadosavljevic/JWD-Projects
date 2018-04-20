package vezba.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Lek;
import vezba.apoteka.web.dto.LekDTO;

@Component
public class LekToLekDTO implements Converter<Lek, LekDTO> {

	@Override
	public LekDTO convert(Lek lek) {
		if(lek==null){
			return null;
		}
		
		LekDTO dto = new LekDTO();
		
		dto.setId(lek.getId());
		dto.setNaziv(lek.getNaziv());
		dto.setGenerickiNaziv(lek.getGenerickiNaziv());
		dto.setKolicinaNaStanju(lek.getKolicinaNaStanju());
		dto.setCena(lek.getCena());
		dto.setProizvodjacId(lek.getProizvodjac().getId());
		dto.setProizvodjacIme(lek.getProizvodjac().getIme());
		dto.setApotekaId(lek.getApoteka().getId());
		dto.setApotekaIme(lek.getApoteka().getIme());
		
		return dto;
	}
	
	public List<LekDTO> convert(List<Lek> lekovi){
		List<LekDTO> ret = new ArrayList<>();
		
		for(Lek lek: lekovi){
			ret.add(convert(lek));
		}
		
		return ret;
	}
}
