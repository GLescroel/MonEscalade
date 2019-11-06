package glescroel.escalade.service;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Voie;
import glescroel.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoieService {

    @Autowired
    private VoieRepository voieRepository;

    public VoieDto getVoieByNom(String nom){
        Optional<Voie> result = voieRepository.findByNomIgnoreCase(nom);
        VoieDto voieDto = null;
        if (result.isPresent()){
            voieDto = new VoieDto(result.get());
        }
        return voieDto;
    }

    public List<VoieDto> getVoiesBySecteur(Integer idSecteur) {
        List<Voie> voies = voieRepository.getVoiesBySecteur_Id(idSecteur);
        return convertVoiesToDtos(voies);
    }

    private List<VoieDto> convertVoiesToDtos(List<Voie> voies) {
        List<VoieDto> voieDtos = new ArrayList();
        for (Voie voie : voies){
            voieDtos.add(new VoieDto(voie));
        }
        return voieDtos;
    }

}
