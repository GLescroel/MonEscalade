package glescroel.escalade.service;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.repository.SecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecteurService {

    @Autowired
    private SecteurRepository secteurRepository;


    public SecteurDto getSecteurByNom(String nom){
        Optional<Secteur> result = secteurRepository.findByNomIgnoreCase(nom);
        SecteurDto secteurDto = null;
        if (result.isPresent()){
            secteurDto = new SecteurDto(result.get());
        }
        return secteurDto;
    }

}
