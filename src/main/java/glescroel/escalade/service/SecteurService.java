package glescroel.escalade.service;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.mapper.SecteurMapper;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.repository.SecteurRepository;
import glescroel.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecteurService {

    private static final SecteurMapper SECTEUR_MAPPER = SecteurMapper.INSTANCE;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SecteurRepository secteurRepository;


    public SecteurDto getSecteurByNom(String nom){
        Optional<Secteur> result = secteurRepository.findByNomIgnoreCase(nom);
        SecteurDto secteurDto = null;
        if (result.isPresent()){
            secteurDto = SECTEUR_MAPPER.map(result.get());
        }
        return secteurDto;
    }

    public List<SecteurDto> getSecteursBySite(Integer idSite) {
        List<Secteur> secteurs = secteurRepository.getSecteursBySite_Id(idSite);
        return SECTEUR_MAPPER.secteursToDtos(secteurs);
    }

    public SecteurDto save(SecteurDto secteurDto) {
        Secteur secteur = SECTEUR_MAPPER.map(secteurDto);
        secteur.setSite(siteRepository.getOne(secteurDto.getSite().getId()));
        return SECTEUR_MAPPER.map(secteurRepository.saveAndFlush(secteur));
    }

    public SecteurDto getSecteurById(Integer secteurId) {
        Optional<Secteur> result = secteurRepository.findById(secteurId);
        SecteurDto secteurDto = null;
        if (result.isPresent()){
            secteurDto = SECTEUR_MAPPER.map(result.get());
        }
        return secteurDto;
    }
}
