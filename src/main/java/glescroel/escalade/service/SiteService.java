package glescroel.escalade.service;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Site;
import glescroel.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;



    public SiteDto getSiteByNom(String nom){
        Optional<Site> result = siteRepository.findByNomIgnoreCase(nom);
        SiteDto siteDto = null;
        if (result.isPresent()){
            siteDto = new SiteDto(result.get());
        }
        return siteDto;
    }
}
