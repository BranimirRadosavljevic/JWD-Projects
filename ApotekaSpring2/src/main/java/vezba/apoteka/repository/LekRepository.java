package vezba.apoteka.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vezba.apoteka.model.Lek;

@Repository
public interface LekRepository extends JpaRepository<Lek, Long>{

	Page<Lek> findByApotekaId(Long apotekaId, Pageable pageRequest);
	
	@Query(
			"SELECT l FROM Lek l WHERE"
			+ "(:apotekaId IS NULL OR l.apoteka.id = :apotekaId) AND"
			+ "(:nazivGenNaziv IS NULL OR l.naziv LIKE :nazivGenNaziv OR"
			+ " :nazivGenNaziv IS NULL OR l.generickiNaziv LIKE :nazivGenNaziv)"
		)
		Page<Lek> pretraga(
				@Param("apotekaId") Long apotekaId,
				@Param("nazivGenNaziv") String nazivGenNaziv,
				Pageable pageable);
	
}
