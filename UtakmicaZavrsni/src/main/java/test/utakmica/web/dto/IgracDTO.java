package test.utakmica.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class IgracDTO {
	
	private Long id;
	@NotNull
	@NotBlank
	private String imeIPrezime;
	@Min(1)
	@Max(99)
	private Integer broj;
	@Max(5)
	private Integer licneGreske;
	private Boolean vanIgre;
	private Long timId;
	private String timIme;
	private Long pozicijaId;
	private String pozicijaNaziv;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
	public Integer getBroj() {
		return broj;
	}
	public void setBroj(Integer broj) {
		this.broj = broj;
	}
	public Integer getLicneGreske() {
		return licneGreske;
	}
	public void setLicneGreske(Integer licneGreske) {
		this.licneGreske = licneGreske;
	}
	public Boolean getVanIgre() {
		return vanIgre;
	}
	public void setVanIgre(Boolean vanIgre) {
		this.vanIgre = vanIgre;
	}
	public Long getTimId() {
		return timId;
	}
	public void setTimId(Long timId) {
		this.timId = timId;
	}
	public String getTimIme() {
		return timIme;
	}
	public void setTimIme(String timIme) {
		this.timIme = timIme;
	}
	public Long getPozicijaId() {
		return pozicijaId;
	}
	public void setPozicijaId(Long pozicijaId) {
		this.pozicijaId = pozicijaId;
	}
	public String getPozicijaNaziv() {
		return pozicijaNaziv;
	}
	public void setPozicijaNaziv(String pozicijaNaziv) {
		this.pozicijaNaziv = pozicijaNaziv;
	}
	

}
