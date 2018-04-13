package autbuska.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import autbuska.stanica.model.Linija;
import autbuska.stanica.service.PrevoznikService;
import autbuska.stanica.service.LinijaService;
import autbuska.stanica.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija 
	implements Converter<LinijaDTO, Linija> {
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Linija convert(LinijaDTO dto) {
		Linija linija;
		
		if(dto.getId()==null){
			linija = new Linija();
			linija.setPrevoznik(prevoznikService.getOne(dto.getPrevoznikId()));
			
		} else {
			linija = linijaService.getOne(dto.getId());
		}
		
		linija.setBrojMesta(dto.getBrojMesta());
		linija.setCenaKarte(dto.getCenaKarte());
		linija.setVremePolaska(dto.getVremePolaska());
		linija.setDestinacija(dto.getDestinacija());
		linija.setPrevoznik(prevoznikService.getOne(dto.getPrevoznikId()));
		
		return linija;
	}
	
	public List<Linija> convert (List<LinijaDTO> dtos){
		List<Linija> linije = new ArrayList<>();
		
		for(LinijaDTO dto : dtos){
			linije.add(convert(dto));
		}
		
		return linije;
	}

}
