package glescroel.escalade.service;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.repository.LongueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LongueurService {

    @Autowired
    private LongueurRepository longueurRepository;

    public LongueurDto getLongueurByNom(String nom){
        Optional<Longueur> result = longueurRepository.findByNomIgnoreCase(nom);
        LongueurDto longueurDto = null;
        if (result.isPresent()){
            longueurDto = new LongueurDto(result.get());
        }
        return longueurDto;
    }

    public List<LongueurDto> getLongueursByVoie(Integer idVoie) {
        List<Longueur> longueurs = longueurRepository.getLongueursByVoie_Id(idVoie);
        return convertLongueursToDtos(longueurs);
    }

    private List<LongueurDto> convertLongueursToDtos(List<Longueur> longueurs) {
        List<LongueurDto> longueurDtos = new ArrayList();
        for (Longueur longueur : longueurs){
            longueurDtos.add(new LongueurDto(longueur));
        }
        return longueurDtos;
    }

}
