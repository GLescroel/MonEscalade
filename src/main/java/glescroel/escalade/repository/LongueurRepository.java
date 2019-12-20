package glescroel.escalade.repository;

import glescroel.escalade.model.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Integer> {
    Optional<Longueur> findByNomIgnoreCase(String nom);

    List<Longueur> getLongueursByVoie_Id(int voieId);
}
