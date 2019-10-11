package glescroel.escalade.repository;

import glescroel.escalade.model.Continent;
import glescroel.escalade.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Integer> {

    Optional<Pays> findByNomIgnoreCase(String nom);
    List<Pays> findAllByContinentOrderByNomAsc(Continent continent);
}
