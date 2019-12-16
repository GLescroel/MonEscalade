package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.LocalisationService;
import glescroel.escalade.service.LongueurService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.SecteurService;
import glescroel.escalade.service.SiteService;
import glescroel.escalade.service.VoieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormSiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormSiteController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private VoieService voieService;
    @Autowired
    private LongueurService longueurService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private PaysService paysService;
    @Autowired
    private LocalisationService localisationService;

    @GetMapping(value = "/newSite")
    public String viewFormSite(Model model) {
        LOGGER.debug(">>>>> Dans FormSiteController - GetMapping");

        model.addAttribute("site", null);
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());

        return "formSite";
    }

    @PostMapping(value = "/newSite")
    public ModelAndView searchSite(@RequestParam(required = false, name = "nomNewSite") String nomSite,
                                   @RequestParam(required = false, name = "continentSelection") String continentSelectionne,
                                   @RequestParam(required = false, name = "paysSelection") String paysSelectionne,
                                   @RequestParam(required = false, name = "region") String region,
                                   @RequestParam(required = false, name = "departement") String departement,
                                   @RequestParam(required = false, name = "ville") String ville,
                                   @RequestParam(required = false, name = "adresse") String adresse) {

        ModelAndView modelAndview = new ModelAndView("formSite");
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping");

        LOGGER.info(">>>>> continenet : " + continentSelectionne + " / pays : " + paysSelectionne);

        LocalisationDto localisation = new LocalisationDto()
                .builder()
                .continent(continentService.getContinentById(Integer.valueOf(continentSelectionne)))
                .pays(paysService.getPaysById(Integer.valueOf(paysSelectionne)))
                .region(region)
                .departement(departement)
                .ville(ville)
                .adresse(adresse)
                .build();
        localisation = localisationService.save(localisation);

        SiteDto newSite = new SiteDto()
                .builder()
                .nom(nomSite)
                .localisation(localisation)
                .build();
        newSite = siteService.save(newSite);

        modelAndview.addObject("site", newSite);

        return modelAndview;
    }
}