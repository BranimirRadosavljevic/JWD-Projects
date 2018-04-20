package vezba.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Proizvodjac;
import vezba.apoteka.web.dto.ProizvodjacDTO;

@Component
public class ProizvodjacToProizvodjacDTO implements Converter<Proizvodjac, ProizvodjacDTO> {

	@Override
	public ProizvodjacDTO convert(Proizvodjac proizvodjac) {
		ProizvodjacDTO dto = new ProizvodjacDTO();
		
		dto.setId(proizvodjac.getId());
		dto.setIme(proizvodjac.getIme());
		
		return dto;
	}
	
	public List<ProizvodjacDTO> convert(List<Proizvodjac> proizvodjaci){
		List<ProizvodjacDTO> ret = new ArrayList<>();
		
		for(Proizvodjac p : proizvodjaci){
			ret.add(convert(p));
		}
		
		return ret;
	}
}
