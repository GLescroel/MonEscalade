package glescroel.escalade.service;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Site;
import glescroel.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public SiteDto getSiteByNom(String nom) {
        Optional<Site> result = siteRepository.findByNomIgnoreCase(nom);
        SiteDto siteDto = null;
        if (result.isPresent()) {
            siteDto = new SiteDto(result.get());
        }
        return siteDto;
    }

    public List<SiteDto> getSitesByNomPartiel(String nomPartiel) {
        List<Site> sites = siteRepository.getSiteByNomPartiel(nomPartiel);
        return convertSitesToDtos(sites);
    }

    public List<SiteDto> getSitesByContinent(Integer continentRecherche) {
        List<Site> sites = siteRepository.getSiteByContinent(continentRecherche);
        return convertSitesToDtos(sites);
    }

    public List<SiteDto> getSitesByPays(Integer paysRecherche) {
        List<Site> sites = siteRepository.getSiteByPays(paysRecherche);
        return convertSitesToDtos(sites);
    }

    public List<SiteDto> getSitesByNomPartielAndContinent(String nomPartiel, Integer continentRecherche) {
        List<Site> sites = siteRepository.getSitesByNomPartielAndContinent(nomPartiel, continentRecherche);
        return convertSitesToDtos(sites);
    }

    public List<SiteDto> getSitesByNomPartielAndPays(String nomPartiel, Integer paysRecherche) {
        List<Site> sites = siteRepository.getSitesByNomPartielAndPays(nomPartiel, paysRecherche);
        return convertSitesToDtos(sites);
    }

    private List<SiteDto> convertSitesToDtos(List<Site> sites) {
        List<SiteDto> siteDtos = new ArrayList();
        for (Site site : sites){
            siteDtos.add(new SiteDto(site));
        }
        return siteDtos;
    }
}
