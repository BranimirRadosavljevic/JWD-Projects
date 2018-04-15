package autobuska.stanica.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import autobuska.stanica.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija, Long>{

	Page<Linija> findByPrevoznikId(Long prevoznikId, Pageable pageRequest);
	
	@Query(
		"SELECT l FROM Linija l WHERE"
		+ "(:destinacija IS NULL OR l.destinacija LIKE :destinacija) AND"
		+ "(:maxCena IS NULL OR l.cenaKarte <= :maxCena) AND"
		+ "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId)"
	)
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija,
			@Param("maxCena") Float maxCena,
			@Param("prevoznikId") Long prevoznikId,
			Pageable pageable);
		
}
