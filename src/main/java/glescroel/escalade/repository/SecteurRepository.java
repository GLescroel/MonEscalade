package glescroel.escalade.repository;

import glescroel.escalade.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Integer> {
    Optional<Secteur> findByNomIgnoreCase(String nom);

    List<Secteur> getSecteursBySite_Id(Integer idSite);

    void deleteSecteurById(Integer idSecteur);
}
