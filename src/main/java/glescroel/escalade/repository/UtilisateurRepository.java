package glescroel.escalade.repository;

import glescroel.escalade.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByNomIgnoreCase(String nom);

    Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
