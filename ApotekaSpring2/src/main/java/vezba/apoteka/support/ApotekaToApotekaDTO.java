package vezba.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Apoteka;
import vezba.apoteka.web.dto.ApotekaDTO;

@Component
public class ApotekaToApotekaDTO 
	implements Converter<Apoteka, ApotekaDTO> {

	@Override
	public ApotekaDTO convert(Apoteka apoteka) {
		if(apoteka==null){
			return null;
		}
		
		ApotekaDTO dto = new ApotekaDTO();
		
		dto.setId(apoteka.getId());
		dto.setIme(apoteka.getIme());
		dto.setAdresa(apoteka.getAdresa());
		
		return dto;
	}
	
	public List<ApotekaDTO> convert(List<Apoteka> apoteke){
		List<ApotekaDTO> ret = new ArrayList<>();
		
		for(Apoteka a: apoteke){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
