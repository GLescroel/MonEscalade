package glescroel.escalade.repository;

import glescroel.escalade.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    Optional<Continent> findByNomIgnoreCase(String nom);
}
