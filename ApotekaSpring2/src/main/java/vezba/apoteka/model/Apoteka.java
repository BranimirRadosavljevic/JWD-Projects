package vezba.apoteka.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="table_apoteka")
public class Apoteka {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false, unique=true)
	private String ime;
	@Column
	private String adresa;
	
	@OneToMany(mappedBy="apoteka", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Lek> lekovi = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public List<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public void addLek(Lek lek) {
		this.lekovi.add(lek);
		
		if (!this.equals(lek.getApoteka())) {
			lek.setApoteka(this);
		}
	}
	
	
}
