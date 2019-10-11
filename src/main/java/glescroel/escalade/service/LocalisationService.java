package glescroel.escalade.service;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.repository.LocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    public List<LocalisationDto> getLocalisationsByContinent(Integer continentRecherche) {
        return localisationRepository.findLocalisationsByContinent(continentRecherche);

    }
}