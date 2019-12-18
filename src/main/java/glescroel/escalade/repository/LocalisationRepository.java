package glescroel.escalade.repository;

import glescroel.escalade.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {

    Localisation save(Localisation localisation);

    List<Localisation> findAllByPays_Id(Integer idPays);
}
