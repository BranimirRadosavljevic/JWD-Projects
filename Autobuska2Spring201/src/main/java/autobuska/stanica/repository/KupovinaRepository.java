package autobuska.stanica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autobuska.stanica.model.Kupovina;

@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Long>{

		
}
