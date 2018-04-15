package test.utakmica.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.utakmica.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long>{

	Page<Igrac> findByTimId(Long timId, Pageable pageRequest);
	
	@Query(
			"SELECT i FROM Igrac i WHERE"
			+ "(:imeIPrezime IS NULL OR i.imeIPrezime LIKE :imeIPrezime) AND"
			+ "(:broj IS NULL OR i.broj =:broj) AND"
			+ "(:timId IS NULL OR i.tim.id =:timId)"
		)
		Page<Igrac> pretraga(
				@Param("imeIPrezime") String imeIPrezime,
				@Param("broj") Integer broj,
				@Param("timId") Long timId,
				Pageable pageable);
	
}
