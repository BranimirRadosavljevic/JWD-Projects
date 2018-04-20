package vezba.apoteka;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vezba.apoteka.model.Apoteka;
import vezba.apoteka.model.Lek;
import vezba.apoteka.model.Proizvodjac;
import vezba.apoteka.service.ApotekaService;
import vezba.apoteka.service.LekService;
import vezba.apoteka.service.ProizvodjacService;

@Component
public class TestData {

	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private ProizvodjacService proizvodjacService;
		
	@Autowired
	private LekService lekService;
	
	@PostConstruct
	public void init() {
		
		List<Proizvodjac> proizvodjaci = new ArrayList<>();
		
		for (int i = 0; i <= 3; i++) {
			Proizvodjac pr = new Proizvodjac();
			pr.setIme("Proizvodjac " + i);
			proizvodjacService.save(pr);
			proizvodjaci.add(pr);
		}
		
		for (int i = 0; i <= 3; i++) {
			Apoteka apoteka = new Apoteka();
			apoteka.setIme("Naziv " + i);
			apoteka.setAdresa("Adresa " + i);
			apotekaService.save(apoteka);
			Proizvodjac proizvodjac = proizvodjaci.get(i);

			for (int j = 0; j <= 5; j++) {
				Lek lek = new Lek();
				lek.setNaziv("Naziv " + j);
				lek.setGenerickiNaziv(j + " GenNaz");
				lek.setKolicinaNaStanju(j + 12);
				lek.setCena((float)j + 500);
				apoteka.addLek(lek);
				proizvodjac.addLek(lek);
				lekService.save(lek);
			}
		}
		
		
	}
}
