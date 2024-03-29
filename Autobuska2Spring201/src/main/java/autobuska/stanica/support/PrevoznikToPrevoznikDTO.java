package autobuska.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autobuska.stanica.model.Prevoznik;
import autobuska.stanica.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDTO 
	implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik prevoznik) {
		if(prevoznik==null){
			return null;
		}
		
		PrevoznikDTO dto = new PrevoznikDTO();
		
		dto.setId(prevoznik.getId());
		dto.setNaziv(prevoznik.getNaziv());
		dto.setAdresa(prevoznik.getAdresa());
		dto.setPib(prevoznik.getPib());;
				
		return dto;
	}
	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
		List<PrevoznikDTO> ret = new ArrayList<>();
		
		for(Prevoznik a: prevoznici){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
