package glescroel.escalade.repository;

import glescroel.escalade.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {

    Localisation save(Localisation localisation);

    List<Localisation> findAllByPays_Id(Integer idPays);
}
