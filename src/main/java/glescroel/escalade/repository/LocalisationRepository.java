package glescroel.escalade.repository;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {

    List<LocalisationDto> findLocalisationsByContinent(Integer continentRecherche);
}
