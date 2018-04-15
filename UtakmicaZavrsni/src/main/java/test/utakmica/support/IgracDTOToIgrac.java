package test.utakmica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.utakmica.model.Igrac;
import test.utakmica.model.Pozicija;
import test.utakmica.model.Tim;
import test.utakmica.service.TimService;
import test.utakmica.service.IgracService;
import test.utakmica.service.PozicijaService;
import test.utakmica.web.dto.IgracDTO;


@Component
public class IgracDTOToIgrac implements Converter<IgracDTO, Igrac> {
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private TimService timService;
	
	@Autowired
	private PozicijaService pozicijaService;

	@Override
	public Igrac convert(IgracDTO dto) {
		Igrac igrac;
		
		if(dto.getId()==null){
			Pozicija p = pozicijaService.findOne(dto.getPozicijaId());
			Tim t = timService.findOne(dto.getTimId());
			igrac = new Igrac();
			igrac.setPozicija(p);
			igrac.setTim(t);
		} else {
			igrac = igracService.findOne(dto.getId());
		}
		
		igrac.setImeIPrezime(dto.getImeIPrezime());
		igrac.setBroj(dto.getBroj());
		igrac.setLicneGreske(dto.getLicneGreske());
		
		if(dto.getLicneGreske() == null) {
			igrac.setLicneGreske(0);
		}
		
		if (dto.getLicneGreske() != null && dto.getLicneGreske() >=5) {
			igrac.setVanIgre(true);
		}
		
		return igrac;
	}
	
	public List<Igrac> convert (List<IgracDTO> dtos){
		List<Igrac> igraci = new ArrayList<>();
		
		for(IgracDTO dto : dtos){
			igraci.add(convert(dto));
		}
		
		return igraci;
	}

}
