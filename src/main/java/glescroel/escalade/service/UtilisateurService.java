package glescroel.escalade.service;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.model.Utilisateur;
import glescroel.escalade.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public UtilisateurDto getUtilisateurByNom(String nom){
        Optional<Utilisateur> result = utilisateurRepository.findByNomIgnoreCase(nom);
        UtilisateurDto utilisateurDto = null;
        if (result.isPresent()){
            utilisateurDto = new UtilisateurDto(result.get());
        }
        return utilisateurDto;
    }

    public UtilisateurDto getUtilisateurByEmail(String email){
        Optional<Utilisateur> result = utilisateurRepository.findByEmailIgnoreCase(email);
        UtilisateurDto utilisateurDto = null;
        if (result.isPresent()){
            utilisateurDto = new UtilisateurDto(result.get());
        }
        return utilisateurDto;
    }

}
