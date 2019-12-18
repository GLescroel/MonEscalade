package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.LocalisationService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private PaysService paysService;
    @Autowired
    private LocalisationService localisationService;

    @GetMapping(value = "/")
    public String viewHomePage(Model model) {
        LOGGER.debug(">>>>> Dans HomePageController");

        SiteDto site = new SiteDto();
        site.setNom("");

        model.addAttribute("site", site);
        model.addAttribute("continents", continentService.getAll());
        ContinentDto continentDto = new ContinentDto();
        continentDto.setId(0);
        continentDto.setNom("Choix du continent");
        model.addAttribute("continentSelectionne", continentDto);
        PaysDto paysSelectionne = new PaysDto();
        paysSelectionne.setId(0);
        paysSelectionne.setNom("Choix du pays");
        model.addAttribute("paysSelectionne", paysSelectionne);
        model.addAttribute("paysRecherche", paysSelectionne);
        model.addAttribute("paysList", null);
        model.addAttribute("region", null);
        model.addAttribute("regionList", null);

        return "homepage";
    }

    @PostMapping(value = "/")
    public ModelAndView searchSite(@RequestParam(required = false, name = "siteRecherche") String nomSiteRecherche,
                                   @RequestParam(required = false, name = "continentRecherche") String continentRecherche,
                                   @RequestParam(required = false, name = "paysRecherche") String paysRecherche,
                                   @RequestParam(required = false, name = "regionRecherche") String regionRecherche) {

        ModelAndView modelAndview = new ModelAndView("homepage");


        PaysDto paysSelectionne = checkPaysContinentConsistency(paysRecherche, continentRecherche);
        modelAndview.addObject("paysSelectionne", paysSelectionne);

        if(paysSelectionne.getId().equals(0)) {
            paysRecherche = null;
        }

        String regionSelectionnee = checkRegionPaysConsistency(regionRecherche, paysSelectionne);
        modelAndview.addObject("regionSelectionnee", regionSelectionnee);

        if(regionSelectionnee.equalsIgnoreCase("Choix de la région")) {
            regionRecherche = null;
        }

        SiteDto site = new SiteDto();
        if (!nomSiteRecherche.isEmpty()) {
            site.setNom(nomSiteRecherche);
        } else {
            site.setNom("");
        }
        modelAndview.addObject("site", site);


        List<SiteDto> sites;
        if ((!nomSiteRecherche.isEmpty()) && (paysRecherche != null) && (!paysRecherche.equals("0"))) {
            sites = siteService.getSitesByNomPartielAndPays(nomSiteRecherche, Integer.valueOf(paysRecherche));
        } else if ((!nomSiteRecherche.isEmpty()) && (!continentRecherche.equals("0"))) {
            sites = siteService.getSitesByNomPartielAndContinent(nomSiteRecherche, Integer.valueOf(continentRecherche));
        } else if (!nomSiteRecherche.isEmpty()) {
            sites = siteService.getSitesByNomPartiel(nomSiteRecherche);
        } else if ((paysRecherche != null) && (!paysRecherche.equals("0"))) {
            sites = siteService.getSitesByPays(Integer.valueOf(paysRecherche));
        } else if (!continentRecherche.equals("0")) {
            sites = siteService.getSitesByContinent(Integer.valueOf(continentRecherche));
        } else {
            sites = null;
        }
        if ((sites != null) && (regionRecherche != null) && (!regionRecherche.isEmpty())) {
            List<Integer> deletionIndexList = new ArrayList<>();
            for (SiteDto siteDto : sites) {
                if (!siteDto.getLocalisation().getRegion().equalsIgnoreCase(regionRecherche)) {
                    deletionIndexList.add(sites.indexOf(siteDto));
                }
            }
            for (int j = deletionIndexList.size() -1 ; j >= 0 ; j--) {
                sites.remove(deletionIndexList.get(j).intValue());
            }
        }
        modelAndview.addObject("resultats", sites);

        modelAndview.addObject("continents", continentService.getAll());
        ContinentDto continentSelectionne = new ContinentDto();
        if (!continentRecherche.equals("0")) {
            continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));
            modelAndview.addObject("paysList", paysService.getPaysByContinent(continentSelectionne));
        } else {
            continentSelectionne.setId(0);
            continentSelectionne.setNom("Choix du continent");
        }
        modelAndview.addObject("continentSelectionne", continentSelectionne);

        modelAndview.addObject("regionList", null);
        if (paysSelectionne.getId() != 0) {
            List<LocalisationDto> localisationList = localisationService.getLocalisationsByPays(paysSelectionne.getId());
            List<String> regionList = new ArrayList<>();
            for (LocalisationDto localisation : localisationList) {
                if(!regionList.contains(localisation.getRegion())) {
                    regionList.add(localisation.getRegion());
                }
            }
            modelAndview.addObject("regionList", regionList);
        }

        return modelAndview;

    }

    private PaysDto checkPaysContinentConsistency(String paysRecherche, String continentRecherche) {

        boolean paysContinentOk = false;
        PaysDto paysSelectionne = new PaysDto();
        if ((paysRecherche != null) && (!paysRecherche.equals("0"))) {
            paysSelectionne = paysService.getPaysById(Integer.valueOf(paysRecherche));

            ContinentDto continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));

            for (PaysDto pays : paysService.getPaysByContinent(continentSelectionne)) {
                if (paysSelectionne.getNom().equals(pays.getNom())) {
                    paysContinentOk = true;
                }
            }
        }

        if(!paysContinentOk) {
            paysSelectionne.setId(0);
            paysSelectionne.setNom("Choix du pays");
        }

        return paysSelectionne;
    }

    private String checkRegionPaysConsistency(String region, PaysDto pays) {
        for (LocalisationDto localisation : localisationService.getLocalisationsByPays(pays.getId())) {
            if(localisation.getRegion().equalsIgnoreCase(region))
                return region;
        }
        return "Choix de la région";
    }
}
