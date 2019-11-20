package glescroel.escalade.service;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.mapper.LongueurMapper;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.repository.LongueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LongueurService {

    private static final LongueurMapper LONGUEUR_MAPPER = LongueurMapper.INSTANCE;

    @Autowired
    private LongueurRepository longueurRepository;

    public LongueurDto getLongueurByNom(String nom){
        Optional<Longueur> result = longueurRepository.findByNomIgnoreCase(nom);
        LongueurDto longueurDto = null;
        if (result.isPresent()){
            longueurDto = LONGUEUR_MAPPER.map(result.get());
        }
        return longueurDto;
    }

    public List<LongueurDto> getLongueursByVoie(Integer idVoie) {
        List<Longueur> longueurs = longueurRepository.getLongueursByVoie_Id(idVoie);
        return LONGUEUR_MAPPER.longueursToDtos(longueurs);
    }
}
