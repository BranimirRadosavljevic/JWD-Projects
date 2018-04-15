package test.utakmica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.utakmica.model.Tim;
import test.utakmica.web.dto.TimDTO;

@Component
public class TimToTimDTO 
	implements Converter<Tim, TimDTO> {

	@Override
	public TimDTO convert(Tim tim) {
		if(tim==null){
			return null;
		}
		
		TimDTO dto = new TimDTO();
		
		dto.setId(tim.getId());
		dto.setIme(tim.getIme());
		dto.setTrener(tim.getTrener());
		dto.setBoje(tim.getBoje());
		
		return dto;
	}
	
	public List<TimDTO> convert(List<Tim> timovi){
		List<TimDTO> ret = new ArrayList<>();
		
		for(Tim t: timovi){
			ret.add(convert(t));
		}
		
		return ret;
	}

}
