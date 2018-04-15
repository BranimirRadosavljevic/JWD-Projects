package test.utakmica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.utakmica.model.Igrac;
import test.utakmica.web.dto.IgracDTO;

@Component
public class IgracToIgracDTO implements Converter<Igrac, IgracDTO> {

	@Override
	public IgracDTO convert(Igrac igrac) {
		if(igrac==null){
			return null;
		}
		
		IgracDTO dto = new IgracDTO();
		
		dto.setId(igrac.getId());
		dto.setImeIPrezime(igrac.getImeIPrezime());
		dto.setBroj(igrac.getBroj());
		dto.setLicneGreske(igrac.getLicneGreske());
		dto.setVanIgre(igrac.getVanIgre());
		dto.setPozicijaId(igrac.getPozicija().getId());
		dto.setPozicijaNaziv(igrac.getPozicija().getNaziv());
		dto.setTimId(igrac.getTim().getId());
		dto.setTimIme(igrac.getTim().getIme());
		
		return dto;
	}
	
	public List<IgracDTO> convert(List<Igrac> igraci){
		List<IgracDTO> ret = new ArrayList<>();
		
		for(Igrac igrac: igraci){
			ret.add(convert(igrac));
		}
		
		return ret;
	}
}
