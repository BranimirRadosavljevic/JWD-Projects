package test.utakmica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="table_igrac")
public class Igrac {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false)
	private String imeIPrezime;
	@Column(nullable=false)
	private Integer broj;
	@Column(nullable=false)
	private Integer licneGreske;
	@Column
	private Boolean vanIgre;
	@ManyToOne(fetch=FetchType.EAGER)
	private Tim tim;
	@ManyToOne(fetch=FetchType.EAGER)
	private Pozicija pozicija;
	
		
	public Igrac() {
		this.licneGreske = 0;
		this.vanIgre = false;
	}

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

	public Tim getTim() {
		return tim;
	}
	
	public void setTim(Tim tim) {
		this.tim = tim;
		
		if (tim != null && !tim.getIgraci().contains(this)) {
			tim.getIgraci().add(this);
		}
	}

	public Pozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(Pozicija pozicija) {
		this.pozicija = pozicija;
		
		if (pozicija != null && !pozicija.getIgraci().contains(this)) {
			pozicija.getIgraci().add(this);
		}
	}
	
	
	
}
