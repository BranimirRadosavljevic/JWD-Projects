package autobuska.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Kupovina {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private Integer brojKarata;
	@ManyToOne(fetch=FetchType.EAGER)
	private Linija linija;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getBrojKarata() {
		return brojKarata;
	}

	public void setBrojKarata(Integer brojKarata) {
		this.brojKarata = brojKarata;
	}

	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
		
		if (linija != null && !linija.getKupovine().contains(this)) {
			linija.getKupovine().add(this);
		}
	}
	
	
}
