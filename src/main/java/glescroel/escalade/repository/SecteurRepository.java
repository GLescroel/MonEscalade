package glescroel.escalade.repository;

import glescroel.escalade.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecteurRepository extends JpaRepository<Secteur, Integer> {
    Optional<Secteur> findByNomIgnoreCase(String nom);
}
