package glescroel.escalade.service;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.mapper.LongueurMapper;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.repository.LongueurRepository;
import glescroel.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LongueurService {

    private static final LongueurMapper LONGUEUR_MAPPER = LongueurMapper.INSTANCE;

    @Autowired
    private LongueurRepository longueurRepository;
    @Autowired
    private VoieRepository voieRepository;

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

    public LongueurDto save(LongueurDto longueurDto) {
        Longueur longueur = LONGUEUR_MAPPER.map(longueurDto);
        longueur.setVoie(voieRepository.getOne(longueurDto.getVoie().getId()));
        return LONGUEUR_MAPPER.map(longueurRepository.save(longueur));
    }

    public LongueurDto getLongueurById(Integer idLongueur) {
        return LONGUEUR_MAPPER.map(longueurRepository.getOne(idLongueur));
    }

    @Transactional
    public void delete(LongueurDto longueur) {
        longueurRepository.deleteLongueurById(longueur.getId());
    }
}
