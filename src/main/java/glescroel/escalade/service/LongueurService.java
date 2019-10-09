package glescroel.escalade.service;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.repository.LongueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
