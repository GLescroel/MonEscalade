package glescroel.escalade.repository;

import glescroel.escalade.model.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Integer> {
    Optional<Voie> findByNomIgnoreCase(String nom);

    List<Voie> getVoiesBySecteur_Id(int secteurId);
}
