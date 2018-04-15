package autobuska.stanica.web.dto;

public class LinijaDTO {
	
	private Long id;
	private Integer brojMesta;
	private Float cenaKarte;
	private String vremePolaska;
	private String destinacija;
	private Long prevoznikId;
	private String prevoznikNaziv;
	
	public Long getId() {
		return id;
	}
	
	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}
	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}
	public Float getCenaKarte() {
		return cenaKarte;
	}
	public void setCenaKarte(Float cenaKarte) {
		this.cenaKarte = cenaKarte;
	}
	public String getVremePolaska() {
		return vremePolaska;
	}
	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}
	public String getDestinacija() {
		return destinacija;
	}
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
		

}
