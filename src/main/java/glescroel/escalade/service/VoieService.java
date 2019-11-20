package glescroel.escalade.service;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.mapper.VoieMapper;
import glescroel.escalade.model.Voie;
import glescroel.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoieService {

    private static final VoieMapper VOIE_MAPPER = VoieMapper.INSTANCE;

    @Autowired
    private VoieRepository voieRepository;

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
}
