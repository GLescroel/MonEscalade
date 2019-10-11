package glescroel.escalade.service;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Voie;
import glescroel.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
