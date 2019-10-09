package glescroel.escalade.repository;

import glescroel.escalade.model.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LongueurRepository extends JpaRepository<Longueur, Integer> {
    Optional<Longueur> findByNomIgnoreCase(String nom);
}
