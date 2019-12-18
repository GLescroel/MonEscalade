package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Recherche;
import glescroel.escalade.model.Selection;
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

    private static final ContinentDto DEFAULT_CONTINENT = new ContinentDto().builder().id(0).nom("Choix du continent").build();
    private static final PaysDto DEFAULT_PAYS = new PaysDto().builder().id(0).nom("Choix du pays").continent(DEFAULT_CONTINENT).build();
    private static final String DEFAULT_REGION = "Choix de la région";

    @GetMapping(value = "/")
    public String viewHomePage(Model model) {
        LOGGER.debug(">>>>> Dans HomePageController");

        model.addAttribute("site", new SiteDto().builder().nom("").build());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", DEFAULT_CONTINENT);
        model.addAttribute("paysSelectionne", DEFAULT_PAYS);
        model.addAttribute("paysRecherche", DEFAULT_PAYS);
        model.addAttribute("paysList", null);
        model.addAttribute("region", null);
        model.addAttribute("regions", null);

        return "homepage";
    }

    @PostMapping(value = "/")
    public ModelAndView searchSite(@RequestParam(required = false, name = "siteRecherche") String nomSiteRecherche,
                                   @RequestParam(required = false, name = "continentRecherche") String continentRecherche,
                                   @RequestParam(required = false, name = "paysRecherche") String paysRecherche,
                                   @RequestParam(required = false, name = "regionRecherche") String regionRecherche) {

        ModelAndView modelAndview = new ModelAndView("homepage");

        Selection selection = new Selection()
                .builder()
                .nomSite(nomSiteRecherche)
                .nomContinent(continentRecherche)
                .nomPays(paysRecherche)
                .region(regionRecherche)
                .build();

        Recherche recherche = buildRechercheFromSelection(selection);

        updateSelection(selection, modelAndview);

        List<SiteDto> sites = findSitesFromRecherche(recherche);
        modelAndview.addObject("resultats", sites);

        updateLists(recherche, modelAndview);

        return modelAndview;

    }

    private ContinentDto getContinentFromId(String idContinent) {
        if ((idContinent != null) && (idContinent != "0")) {
            return continentService.getContinentById(Integer.valueOf(idContinent));
        } else {
            return DEFAULT_CONTINENT;
        }
    }

    private PaysDto getPaysFromId(String idPays) {
        if ((idPays != null) && (!idPays.equals("0"))) {
            return paysService.getPaysById(Integer.valueOf(idPays));
        } else {
            return DEFAULT_PAYS;
        }
    }

    private Recherche buildRechercheFromSelection(Selection selection) {

        selection.setContinent(getContinentFromId(selection.getNomContinent()));
        selection.setNomContinent(selection.getContinent().getNom());

        selection.setPays(getPaysFromId(selection.getNomPays()));
        selection.setPays(checkPaysContinentConsistency(selection.getPays(), selection.getContinent()));
        selection.setNomPays(selection.getPays().getNom());

        selection.setRegion(checkRegionPaysConsistency(selection.getRegion(), selection.getPays()));

        return new Recherche()
                .builder()
                .nomSite(selection.getNomSite())
                .continent(selection.getContinent())
                .nomPays(selection.getNomPays())
                .region(selection.getRegion())
                .pays(selection.getPays())
                .build();
    }

    private PaysDto checkPaysContinentConsistency(PaysDto paysToCheck, ContinentDto continentToCheck) {

        boolean paysContinentOk = false;
        if ((paysToCheck != null) && (paysToCheck != DEFAULT_PAYS) &&
                (continentToCheck != null) && (continentToCheck != DEFAULT_CONTINENT)) {
            for (PaysDto pays : paysService.getPaysByContinent(continentToCheck)) {
                if (paysToCheck.getNom().equals(pays.getNom())) {
                    paysContinentOk = true;
                }
            }
        }

        if (!paysContinentOk) {
            return DEFAULT_PAYS;
        }

        return paysToCheck;
    }

    private String checkRegionPaysConsistency(String region, PaysDto pays) {
        for (LocalisationDto localisation : localisationService.getLocalisationsByPays(pays.getId())) {
            if (localisation.getRegion().equalsIgnoreCase(region))
                return region;
        }
        return DEFAULT_REGION;
    }

    private List<SiteDto> findSitesFromRecherche(Recherche recherche) {

        LOGGER.info("Recherche : continent : " + recherche.getContinent().getNom() + " / pays : " + recherche.getPays().getNom() + " / region : " + recherche.getRegion());
        List<SiteDto> sites;
        if ((!recherche.getNomSite().isEmpty()) && (recherche.getPays() != DEFAULT_PAYS)) {
            LOGGER.info("recherche par nom et pays");
            sites = siteService.getSitesByNomPartielAndPays(recherche.getNomSite(), recherche.getPays().getId());
        } else if ((!recherche.getNomSite().isEmpty()) && (recherche.getContinent() != DEFAULT_CONTINENT)) {
            LOGGER.info("recherche par nom et continent");
            sites = siteService.getSitesByNomPartielAndContinent(recherche.getNomSite(), recherche.getContinent().getId());
        } else if (!recherche.getNomSite().isEmpty()) {
            LOGGER.info("recherche par nom uniquement");
            sites = siteService.getSitesByNomPartiel(recherche.getNomSite());
        } else if (recherche.getPays() != DEFAULT_PAYS) {
            LOGGER.info("recherche par pays");
            sites = siteService.getSitesByPays(recherche.getPays().getId());
        } else if (recherche.getContinent() != DEFAULT_CONTINENT) {
            LOGGER.info("recherche par continent");
            sites = siteService.getSitesByContinent(recherche.getContinent().getId());
        } else {
            LOGGER.info("else null");
            sites = null;
        }

        if ((sites != null) && (recherche.getRegion() != DEFAULT_REGION)) {
            LOGGER.info("filtre par region");
            List<Integer> deletionIndexList = new ArrayList<>();
            for (SiteDto siteDto : sites) {
                if (!siteDto.getLocalisation().getRegion().equalsIgnoreCase(recherche.getRegion())) {
                    deletionIndexList.add(sites.indexOf(siteDto));
                }
            }
            for (int j = deletionIndexList.size() - 1; j >= 0; j--) {
                sites.remove(deletionIndexList.get(j).intValue());
            }
        }
        return sites;
    }

    private void updateSelection(Selection selection, ModelAndView modelAndview) {
        modelAndview.addObject("site", new SiteDto().builder().nom(selection.getNomSite()).build());
        modelAndview.addObject("continentSelectionne", selection.getContinent());
        modelAndview.addObject("paysSelectionne", selection.getPays());
        modelAndview.addObject("regionSelectionnee", selection.getRegion());
    }

    private void updateLists(Recherche recherche, ModelAndView modelAndview) {

        modelAndview.addObject("continents", continentService.getAll());

        if (recherche.getContinent() != DEFAULT_CONTINENT) {
            modelAndview.addObject("paysList", paysService.getPaysByContinent(recherche.getContinent()));
        }

        if (recherche.getPays() != DEFAULT_PAYS) {
            List<LocalisationDto> localisationList = localisationService.getLocalisationsByPays(recherche.getPays().getId());
            List<String> regionList = new ArrayList<>();
            for (LocalisationDto localisation : localisationList) {
                if (!regionList.contains(localisation.getRegion())) {
                    regionList.add(localisation.getRegion());
                }
            }
            modelAndview.addObject("regions", regionList);
        }
    }
}