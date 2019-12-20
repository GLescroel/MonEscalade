package glescroel.escalade.repository;

import glescroel.escalade.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    Optional<Continent> findByNomIgnoreCase(String nom);
}
