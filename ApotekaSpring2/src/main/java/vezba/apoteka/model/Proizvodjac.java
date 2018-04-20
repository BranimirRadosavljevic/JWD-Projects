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
@Table(name="tbl_proizvodjac")
public class Proizvodjac {
 
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false, unique=true)
	private String ime;
	@OneToMany(mappedBy="proizvodjac", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
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

	public List<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public void addLek (Lek lek) {
		this.lekovi.add(lek);
		
		if (!this.equals(lek.getProizvodjac())) {
			lek.setProizvodjac(this);
		}
	}
	
	
}
