package autobuska.stanica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import autobuska.stanica.model.Linija;
import autobuska.stanica.model.Prevoznik;
import autobuska.stanica.service.LinijaService;
import autobuska.stanica.service.PrevoznikService;

@Component
public class TestData {

	@Autowired
	private PrevoznikService prevoznikService;
		
	@Autowired
	private LinijaService linijaService;
	
	@PostConstruct
	public void init() {
		
		for (int i = 1; i <= 3; i++) {
			Prevoznik prevoznik = new Prevoznik();
			prevoznik.setNaziv("Naziv " + i);
			prevoznik.setAdresa("Adresa " + i);
			prevoznik.setPib( i + i + "344562018");
			prevoznikService.save(prevoznik);

		for (int j = 1; j <= 5; j++) {
				Linija linija = new Linija();
				linija.setBrojMesta(j+j);
				linija.setCenaKarte((float)j + 500);
				linija.setVremePolaska(j + ".04.2018.");
				linija.setDestinacija("Destinacija" + j);
				prevoznik.addStand(linija);
				
				linijaService.save(linija);
			}
		}
		
		
	}
}
