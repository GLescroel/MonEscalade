package glescroel.escalade.service;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.mapper.SecteurMapper;
import glescroel.escalade.mapper.VoieMapper;
import glescroel.escalade.model.Voie;
import glescroel.escalade.repository.SecteurRepository;
import glescroel.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoieService {

    private static final VoieMapper VOIE_MAPPER = VoieMapper.INSTANCE;
    private static final SecteurMapper SECTEUR_MAPPER = SecteurMapper.INSTANCE;

    @Autowired
    private VoieRepository voieRepository;
    @Autowired
    private SecteurRepository secteurRepository;

    public VoieDto getVoieByNom(String nom){
        Optional<Voie> result = voieRepository.findByNomIgnoreCase(nom);
        VoieDto voieDto = null;
        if (result.isPresent()){
            voieDto = VOIE_MAPPER.map(result.get());
        }
        return voieDto;
    }

    public List<VoieDto> getVoiesBySecteur(Integer idSecteur) {
        List<Voie> voies = voieRepository.getVoiesBySecteur_Id(idSecteur);
        return VOIE_MAPPER.voiesToDtos(voies);
    }

    public VoieDto save(VoieDto voieDto) {
        Voie voie = VOIE_MAPPER.map(voieDto);
        voie.setSecteur(secteurRepository.getOne(voieDto.getSecteur().getId()));
        return VOIE_MAPPER.map(voieRepository.save(voie));
    }
}
