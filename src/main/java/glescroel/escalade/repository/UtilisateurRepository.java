package glescroel.escalade.repository;

import glescroel.escalade.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByNomIgnoreCase(String nom);

    Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
