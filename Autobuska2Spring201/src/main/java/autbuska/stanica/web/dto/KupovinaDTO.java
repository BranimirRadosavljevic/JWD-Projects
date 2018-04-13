package autbuska.stanica.web.dto;

public class KupovinaDTO {

	private Long id;
	private Integer brojKarata;
	private Float cena;

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

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}	
	
}
