package glescroel.escalade.service;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.mapper.LocalisationMapper;
import glescroel.escalade.repository.LocalisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService {

    private static final LocalisationMapper LOCALISATION_MAPPER = LocalisationMapper.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalisationService.class);

    @Autowired
    private LocalisationRepository localisationRepository;

    public LocalisationDto save(LocalisationDto localisation) {
        return LOCALISATION_MAPPER.map(localisationRepository.save(LOCALISATION_MAPPER.map(localisation)));
    }
}