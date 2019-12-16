package glescroel.escalade.repository;

import glescroel.escalade.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {

    Localisation save(Localisation localisation);
}
