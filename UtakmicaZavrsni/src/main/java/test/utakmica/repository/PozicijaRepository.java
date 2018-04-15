package test.utakmica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.utakmica.model.Pozicija;

@Repository
public interface PozicijaRepository extends JpaRepository<Pozicija, Long> {

}
