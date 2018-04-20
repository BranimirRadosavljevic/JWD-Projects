package vezba.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vezba.apoteka.model.Apoteka;

@Repository
public interface ApotekaRepository 
	extends JpaRepository<Apoteka, Long> {
		
}
