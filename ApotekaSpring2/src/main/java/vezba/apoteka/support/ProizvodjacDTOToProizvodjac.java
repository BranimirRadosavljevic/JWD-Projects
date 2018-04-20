package vezba.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Proizvodjac;
import vezba.apoteka.service.ProizvodjacService;
import vezba.apoteka.web.dto.ProizvodjacDTO;


@Component
public class ProizvodjacDTOToProizvodjac implements Converter<ProizvodjacDTO, Proizvodjac> {
	
	@Autowired
	private ProizvodjacService proizvodjacService;

	@Override
	public Proizvodjac convert(ProizvodjacDTO dto) {
		Proizvodjac proizvodjac;
		
		if(dto.getId()==null){
			proizvodjac = new Proizvodjac();
			
		} else {
			proizvodjac = proizvodjacService.findOne(dto.getId());
		}
		
		proizvodjac.setIme(dto.getIme());
				
		return proizvodjac;
	}
	
	public List<Proizvodjac> convert (List<ProizvodjacDTO> dtos){
		List<Proizvodjac> retVal = new ArrayList<>();
		
		for(ProizvodjacDTO dto : dtos){
			retVal.add(convert(dto));
		}
		
		return retVal;
	}

}
