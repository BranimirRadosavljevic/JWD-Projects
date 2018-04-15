package test.utakmica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.utakmica.model.Igrac;
import test.utakmica.model.Pozicija;
import test.utakmica.model.Tim;
import test.utakmica.service.IgracService;
import test.utakmica.service.PozicijaService;
import test.utakmica.service.TimService;

@Component
public class TestData {

	@Autowired
	private TimService timService;
	
	@Autowired
	private PozicijaService pozicijaService;
		
	@Autowired
	private IgracService igracService;
	
	@PostConstruct
	public void init() {
		
				
		Tim t1 = new Tim();
		t1.setIme("Fenerbahce");
		t1.setTrener("Obradovic");
		t1.setBoje("žuto-plavi");
		timService.save(t1);
		
		Tim t2 = new Tim();
		t2.setIme("Barselona");
		t2.setTrener("Pešić");
		t2.setBoje("crveno-plavi");
		timService.save(t2);
		
		Pozicija p1 = new Pozicija();
		p1.setNaziv("plej");
		pozicijaService.save(p1);
		
		Pozicija p2 = new Pozicija();
		p2.setNaziv("bek");
		pozicijaService.save(p2);
		
		Pozicija p3 = new Pozicija();
		p3.setNaziv("centar");
		pozicijaService.save(p3);
		
		Igrac i1 = new Igrac();
		i1.setBroj(2);
		i1.setImeIPrezime("Pera Peric");
		i1.setPozicija(p1);
		i1.setTim(t1);
		igracService.save(i1);
		
		Igrac i2 = new Igrac();
		i2.setBroj(2);
		i2.setImeIPrezime("Mika Mikic");
		i2.setPozicija(p2);
		i2.setTim(t2);
		igracService.save(i2);
		
		Igrac i3 = new Igrac();
		i3.setBroj(2);
		i3.setImeIPrezime("John Doe");
		i3.setPozicija(p2);
		i3.setTim(t2);
		igracService.save(i3);
		
		Igrac i4 = new Igrac();
		i4.setBroj(4);
		i4.setImeIPrezime("M M");
		i4.setPozicija(p2);
		i4.setTim(t2);
		igracService.save(i4);
		
		Igrac i5 = new Igrac();
		i5.setBroj(5);
		i5.setImeIPrezime("T T");
		i5.setPozicija(p2);
		i5.setTim(t2);
		igracService.save(i5);
		
		Igrac i6 = new Igrac();
		i6.setBroj(21);
		i6.setImeIPrezime("G G");
		i6.setPozicija(p2);
		i6.setTim(t2);
		igracService.save(i6);
		
		Igrac i7 = new Igrac();
		i7.setBroj(32);
		i7.setImeIPrezime("N N");
		i7.setPozicija(p2);
		i7.setTim(t2);
		igracService.save(i7);
		
		Igrac i8 = new Igrac();
		i8.setBroj(56);
		i8.setImeIPrezime("B B");
		i8.setPozicija(p2);
		i8.setTim(t2);
		igracService.save(i8);
		
		Igrac i9 = new Igrac();
		i9.setBroj(12);
		i9.setImeIPrezime("J L");
		i9.setPozicija(p2);
		i9.setTim(t2);
		igracService.save(i9);
	}
}
