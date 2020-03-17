package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
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
import java.util.Collections;
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

    public HomePageController(ContinentService continentService, PaysService paysService, SiteService siteService, LocalisationService localisationService) {
        this.continentService = continentService;
        this.paysService = paysService;
        this.siteService = siteService;
        this.localisationService = localisationService;
    }

    /**
     * Affichage de l'écran d'accueil
     * @param model
     * @return l'écran d'accueil
     */
    @GetMapping(value = "/")
    public String viewHomePage(Model model) {
        LOGGER.debug(">>>>> Dans HomePageController - GetMapping");

        model.addAttribute("site", new SiteDto().builder().nom("").build());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", DEFAULT_CONTINENT);
        model.addAttribute("paysSelectionne", DEFAULT_PAYS);
        model.addAttribute("paysRecherche", DEFAULT_PAYS);
        model.addAttribute("paysList", null);
        model.addAttribute("region", null);
        model.addAttribute("regions", null);
        model.addAttribute("cotationMin", null);
        model.addAttribute("cotationMax", null);

        return "homepage";
    }

    /**
     * Recherche de sites selon les critères saisis
     * @param nomSiteRecherche
     * @param continentRecherche
     * @param paysRecherche
     * @param regionRecherche
     * @param cotationMinRecherche
     * @param cotationMaxRecherche
     * @return l'écran d'accueil
     */
    @PostMapping(value = "/")
    public ModelAndView searchSite(@RequestParam(required = false, name = "siteRecherche") String nomSiteRecherche,
                                   @RequestParam(required = false, name = "continentRecherche") String continentRecherche,
                                   @RequestParam(required = false, name = "paysRecherche") String paysRecherche,
                                   @RequestParam(required = false, name = "regionRecherche") String regionRecherche,
                                   @RequestParam(required = false, name = "cotationMin") String cotationMinRecherche,
                                   @RequestParam(required = false, name = "cotationMax") String cotationMaxRecherche) {
        LOGGER.debug(">>>>> Dans HomePageController - PostMapping");

        ModelAndView modelAndview = new ModelAndView("homepage");

        Selection selection = new Selection()
                .builder()
                .nomSite(nomSiteRecherche)
                .nomContinent(continentRecherche)
                .nomPays(paysRecherche)
                .region(regionRecherche)
                .cotationMin(cotationMinRecherche)
                .cotationMax(cotationMaxRecherche)
                .build();

        Recherche recherche = buildRechercheFromSelection(selection);

        updateSelection(selection, modelAndview);

        List<SiteDto> sites = findSitesFromRecherche(recherche);
        modelAndview.addObject("resultats", sites);

        updateLists(recherche, modelAndview);

        return modelAndview;

    }

    /**
     * Retourne le Continent à partir de son id
     * @param idContinent
     * @return Continent
     */
    private ContinentDto getContinentFromId(String idContinent) {
        if ((idContinent != null) && (!idContinent.equals("0"))) {
            return continentService.getContinentById(Integer.valueOf(idContinent));
        } else {
            return DEFAULT_CONTINENT;
        }
    }

    /**
     * Retourne le Pays à partir de son id
     * @param idPays
     * @return Pays
     */
    private PaysDto getPaysFromId(String idPays) {
        if ((idPays != null) && (!idPays.equals("0"))) {
            return paysService.getPaysById(Integer.valueOf(idPays));
        } else {
            return DEFAULT_PAYS;
        }
    }

    /**
     * Construit l'objet de recherche en fonction de la sélection de l'utilisateur
     * @param selection
     * @return Recherche
     */
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
                .cotationMin(selection.getCotationMin())
                .cotationMax(selection.getCotationMax())
                .build();
    }

    /**
     * vérifie la cohérence Continent/Pays
     * @param paysToCheck
     * @param continentToCheck
     * @return le pays si ok, pays par défaut sinon
     */
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

    /**
     * vérifie la cohérence Région/Pays
     * @param region
     * @param pays
     * @return la région si ok, région par défaut sinon
     */
    private String checkRegionPaysConsistency(String region, PaysDto pays) {
        for (LocalisationDto localisation : localisationService.getLocalisationsByPays(pays.getId())) {
            if (localisation.getRegion().equalsIgnoreCase(region)) {
                return region;
            }
        }
        return DEFAULT_REGION;
    }

    /**
     * Recherche des sites en fonction des critères saisis par l'utiliateur
     * @param recherche
     * @return la liste des sites qui répondent aux critères
     */
    private List<SiteDto> findSitesFromRecherche(Recherche recherche) {
        LOGGER.info(">>>>>>> Recherche des sites correspondants à la recherche");

        List<SiteDto> sites;
        if ((!recherche.getNomSite().isEmpty()) && (recherche.getPays() != DEFAULT_PAYS)) {
            sites = siteService.getSitesByNomPartielAndPays(recherche.getNomSite(), recherche.getPays().getId());
        } else if ((!recherche.getNomSite().isEmpty()) && (recherche.getContinent() != DEFAULT_CONTINENT)) {
            sites = siteService.getSitesByNomPartielAndContinent(recherche.getNomSite(), recherche.getContinent().getId());
        } else if (!recherche.getNomSite().isEmpty()) {
            sites = siteService.getSitesByNomPartiel(recherche.getNomSite());
        } else if (recherche.getPays() != DEFAULT_PAYS) {
            sites = siteService.getSitesByPays(recherche.getPays().getId());
        } else if (recherche.getContinent() != DEFAULT_CONTINENT) {
            sites = siteService.getSitesByContinent(recherche.getContinent().getId());
        } else {
            sites = null;
        }

        if ((sites != null) && (recherche.getRegion() != DEFAULT_REGION)) {
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

        if (sites != null) {
            sites = filterCotations(sites, recherche);
        }

        return sites;
    }

    /**
     * Mise à jour des critères sélectionnés suite aux controles appliqués
     * @param selection
     * @param modelAndview
     */
    private static void updateSelection(Selection selection, ModelAndView modelAndview) {
        modelAndview.addObject("site", new SiteDto().builder().nom(selection.getNomSite()).build());
        modelAndview.addObject("continentSelectionne", selection.getContinent());
        modelAndview.addObject("paysSelectionne", selection.getPays());
        modelAndview.addObject("regionSelectionnee", selection.getRegion());
        modelAndview.addObject("cotationMin", selection.getCotationMin());
        modelAndview.addObject("cotationMax", selection.getCotationMax());
    }

    /**
     * Mise à jur des listes Continents / pays / régions à afficher dans les menus déroulants
     * @param recherche
     * @param modelAndview
     */
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

    /**
     * Filtre sur les sites pré-sélectionnés en fonction des cotations saisies par l'utilisateur
     * @param siteList
     * @param recherche
     * @return la liste des sites qui répondent aux critères de sélection
     */
    private List<SiteDto> filterCotations(List<SiteDto> siteList, Recherche recherche) {

        for (SiteDto site : siteList) {
            site = getSiteCotationsMinMax(site);
        }

        siteList = filterSitesCotations(siteList, recherche);

        return siteList;
    }

    /**
     * Récupération des cotations min et max d'un site
     * @param siteDto
     * @return le DTO du site avec ses cotations min et max
     */
    private SiteDto getSiteCotationsMinMax(SiteDto siteDto) {
        List<String> cotations = new ArrayList<>();
        if (!siteDto.getSecteurs().isEmpty()) {
            for (SecteurDto secteur : siteDto.getSecteurs()) {
                if (!secteur.getVoies().isEmpty()) {
                    for (VoieDto voie : secteur.getVoies()) {
                        cotations.add(voie.getCotation());
                        if (!voie.getLongueurs().isEmpty()) {
                            for (LongueurDto longueur : voie.getLongueurs()) {
                                cotations.add(longueur.getCotation());
                            }
                        }
                    }
                }
            }
        }

        if(!cotations.isEmpty()) {
            Collections.sort(cotations);
            siteDto.setCotationsMin(cotations.get(0));
            siteDto.setCotationsMax(cotations.get(cotations.size() - 1));
        } else {
            siteDto.setCotationsMin("");
            siteDto.setCotationsMax("");
        }
        return siteDto;
    }

    /**
     * Application des cotations min et max recherchées pour filtrer les sites
     * @param siteList
     * @param recherche
     * @return liste des sites dont les cotations répondent aux critères
     */
    private static List<SiteDto> filterSitesCotations(List<SiteDto> siteList, Recherche recherche) {

        if(recherche.getCotationMin().isEmpty() && recherche.getCotationMax().isEmpty()) {
            return siteList;
        }

        int rechercheCotationMin = 0;
        if(!recherche.getCotationMin().isEmpty()) {
            rechercheCotationMin = convertCotationToInt(recherche.getCotationMin());
        }
        int rechercheCotationMax = 100;
        if(!recherche.getCotationMax().isEmpty()) {
            rechercheCotationMax = convertCotationToInt(recherche.getCotationMax());
        }
        return getSitesWithCotationOk(siteList, rechercheCotationMin, rechercheCotationMax);
    }

    /**
     * Filtre des sites sur le critère des cotations
     * @param siteList
     * @param rechercheCotationMin
     * @param rechercheCotationMax
     * @return liste des sites dont les cotations correspondent aux critères
     */
    private static List<SiteDto> getSitesWithCotationOk(List<SiteDto> siteList, int rechercheCotationMin, int rechercheCotationMax) {

        List<SiteDto> sitesWithCotationOk = new ArrayList<>();
        for (SiteDto site : siteList) {
            boolean cotationOk = false;
            boolean cotationFound = false;
            for (SecteurDto secteur : site.getSecteurs()) {
                for (VoieDto voie : secteur.getVoies()) {
                    if(!voie.getCotation().isEmpty()) {
                        cotationFound = true;
                        if ((convertCotationToInt(voie.getCotation()) >= rechercheCotationMin) &&
                                (convertCotationToInt(voie.getCotation()) <= rechercheCotationMax)) {
                            cotationOk = true;
                        }
                    }
                    for (LongueurDto longueur : voie.getLongueurs()) {
                        if(!longueur.getCotation().isEmpty()) {
                            cotationFound = true;
                            if ((convertCotationToInt(longueur.getCotation()) >= rechercheCotationMin) &&
                                    (convertCotationToInt(longueur.getCotation()) <= rechercheCotationMax)) {
                                cotationOk = true;
                            }
                        }
                    }
                }
            }
            if (!cotationFound || cotationOk) {
                sitesWithCotationOk.add(site);
            }
        }
        return sitesWithCotationOk;
    }

    /**
     * Conversion des cotations de String etn entiers pour faciliter les comparaisons
     * @param cotation
     * @return cotation en int
     */
    private static int convertCotationToInt(String cotation) {
        if(cotation.matches("[0-9]{1}")){
            return Integer.valueOf(cotation) * 10;
        }

        int cotationInt = 0;
        if(cotation.matches("[0-9]{1}[abcABC]{1}")){
            cotationInt = Integer.valueOf(String.valueOf(cotation.charAt(0))) * 10;
            if("A".equalsIgnoreCase(String.valueOf(cotation.charAt(1)))) {
                cotationInt += 1;
            } else if("B".equalsIgnoreCase(String.valueOf(cotation.charAt(1)))) {
                cotationInt += 2;
            } else if("C".equalsIgnoreCase(String.valueOf(cotation.charAt(1)))) {
                cotationInt += 3;
            }
            return cotationInt;
        }
        return 0;
    }
}
