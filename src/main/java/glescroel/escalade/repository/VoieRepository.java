package glescroel.escalade.repository;

import glescroel.escalade.model.Voie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoieRepository extends JpaRepository<Voie, Integer> {
    Optional<Voie> findByNomIgnoreCase(String nom);
}
