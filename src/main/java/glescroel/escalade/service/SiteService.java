package glescroel.escalade.service;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.mapper.SiteMapper;
import glescroel.escalade.model.Site;
import glescroel.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {
    private static final SiteMapper SITE_MAPPER = SiteMapper.INSTANCE;

    @Autowired
    private SiteRepository siteRepository;

    public SiteDto getSiteByNom(String nom) {
        Optional<Site> result = siteRepository.findByNomIgnoreCase(nom);
        SiteDto siteDto = null;
        if (result.isPresent()) {
            siteDto = SITE_MAPPER.map(result.get());
        }
        return siteDto;
    }

    public List<SiteDto> getSitesByNomPartiel(String nomPartiel) {
        List<Site> sites = siteRepository.getSiteByNomPartiel(nomPartiel);
        return SITE_MAPPER.sitesToDtos(sites);
    }

    public List<SiteDto> getSitesByContinent(Integer continentRecherche) {
        List<Site> sites = siteRepository.getSiteByContinent(continentRecherche);
        return SITE_MAPPER.sitesToDtos(sites);
    }

    public List<SiteDto> getSitesByPays(Integer paysRecherche) {
        List<Site> sites = siteRepository.getSiteByPays(paysRecherche);
        return SITE_MAPPER.sitesToDtos(sites);
    }

    public List<SiteDto> getSitesByNomPartielAndContinent(String nomPartiel, Integer continentRecherche) {
        List<Site> sites = siteRepository.getSitesByNomPartielAndContinent(nomPartiel, continentRecherche);
        return SITE_MAPPER.sitesToDtos(sites);
    }

    public List<SiteDto> getSitesByNomPartielAndPays(String nomPartiel, Integer paysRecherche) {
        List<Site> sites = siteRepository.getSitesByNomPartielAndPays(nomPartiel, paysRecherche);
        return SITE_MAPPER.sitesToDtos(sites);
    }

    public SiteDto getSiteById(Integer id) {
        Optional<Site> result = siteRepository.findById(id);
        SiteDto siteDto = null;
        if (result.isPresent()) {
            siteDto = SITE_MAPPER.map(result.get());
        }
        return siteDto;
    }

    public SiteDto save(SiteDto siteDto) {
        return SITE_MAPPER.map(siteRepository.save(SITE_MAPPER.map(siteDto)));
    }
}
