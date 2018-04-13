package autbuska.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autbuska.stanica.model.Prevoznik;
import autbuska.stanica.service.PrevoznikService;
import autbuska.stanica.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		Prevoznik prevoznik;
		
		if(dto.getId()==null){
			prevoznik = new Prevoznik();
						
		} else {
			prevoznik = prevoznikService.getOne(dto.getId());
		}
		
		prevoznik.setNaziv(dto.getNaziv());
		prevoznik.setAdresa(dto.getAdresa());
		prevoznik.setPib(dto.getPib());
		
		return prevoznik;
	}
	
	public List<Prevoznik> convert (List<PrevoznikDTO> dtos){
		List<Prevoznik> prevoznici = new ArrayList<>();
		
		for(PrevoznikDTO dto : dtos){
			prevoznici.add(convert(dto));
		}
		
		return prevoznici;
	}
}
