package vezba.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vezba.apoteka.model.Proizvodjac;

@Repository
public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Long> {

}
