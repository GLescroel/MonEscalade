package glescroel.escalade.service;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.mapper.LocalisationMapper;
import glescroel.escalade.repository.LocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalisationService {

    private static final LocalisationMapper LOCALISATION_MAPPER = LocalisationMapper.INSTANCE;

    @Autowired
    private LocalisationRepository localisationRepository;

    public LocalisationDto save(LocalisationDto localisation) {
        return LOCALISATION_MAPPER.map(localisationRepository.save(LOCALISATION_MAPPER.map(localisation)));
    }

    public List<LocalisationDto> getLocalisationsByPays(Integer idPays) {
        return LOCALISATION_MAPPER.localisationsToDtos(localisationRepository.findAllByPays_Id(idPays));
    }
}