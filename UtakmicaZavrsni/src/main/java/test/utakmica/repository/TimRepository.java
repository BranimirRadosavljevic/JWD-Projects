package test.utakmica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.utakmica.model.Tim;

@Repository
public interface TimRepository 
	extends JpaRepository<Tim, Long> {
		
}
