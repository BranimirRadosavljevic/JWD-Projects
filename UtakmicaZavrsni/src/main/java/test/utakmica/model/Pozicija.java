package test.utakmica.model;

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
@Table(name="tbl_pozicija")
public class Pozicija {
 
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false, unique=true)
	private String naziv;
	@OneToMany(mappedBy="pozicija", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Igrac> igraci = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	public void addIgrac (Igrac igrac) {
		this.igraci.add(igrac);
		
		if (!this.equals(igrac.getPozicija())) {
			igrac.setPozicija(this);
		}
	}
	
	
}
