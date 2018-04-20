package vezba.apoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="table_lek")
public class Lek {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String naziv;
	@Column
	private String generickiNaziv;
	@Column
	private Integer kolicinaNaStanju;
	@Column
	private Float cena;
	@ManyToOne(fetch=FetchType.EAGER)
	private Apoteka apoteka;
	@ManyToOne(fetch=FetchType.EAGER)
	private Proizvodjac proizvodjac;
	
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

	public String getGenerickiNaziv() {
		return generickiNaziv;
	}

	public void setGenerickiNaziv(String generickiNaziv) {
		this.generickiNaziv = generickiNaziv;
	}

	public Integer getKolicinaNaStanju() {
		return kolicinaNaStanju;
	}

	public void setKolicinaNaStanju(Integer kolicinaNaStanju) {
		this.kolicinaNaStanju = kolicinaNaStanju;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}
	
	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
		
		if (apoteka != null && !apoteka.getLekovi().contains(this)) {
			apoteka.getLekovi().add(this);
		}
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
		
		if (proizvodjac != null && !proizvodjac.getLekovi().contains(this)) {
			proizvodjac.getLekovi().add(this);
		}
	}
	
	
	
}
