package glescroel.escalade.service;

import glescroel.escalade.model.Etat;
import glescroel.escalade.repository.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtatService {

    @Autowired
    EtatRepository etatRepository;

    public Etat getEtatByNom(String etat) {
        return etatRepository.findByEtat(etat);
    }
}
