package glescroel.escalade.repository;

import glescroel.escalade.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Integer> {
    Etat findByEtat(String etat);
}
