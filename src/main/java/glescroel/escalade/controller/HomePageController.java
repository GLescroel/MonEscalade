package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    SiteService siteService;
    @Autowired
    ContinentService continentService;
    @Autowired
    PaysService paysService;

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
        model.addAttribute("paysList", null);

        return "homepage";
    }

    @PostMapping(value = "/")
    public ModelAndView searchSite(@RequestParam(required = false, name = "siteRecherche") String nomSiteRecherche,
                                   @RequestParam(required = false, name = "continentRecherche") String continentRecherche,
                                   @RequestParam(required = false, name = "paysRecherche") String paysRecherche) {

        ModelAndView modelAndview = new ModelAndView("homepage");

        List<SiteDto> sites = new ArrayList<>();

        if ((!nomSiteRecherche.isEmpty()) && (!paysRecherche.equals("0"))) {
            LOGGER.info(">>>>> POST : site recherché = " + nomSiteRecherche + " et pays = " + paysRecherche);
            sites = siteService.getSitesByNomPartielAndPays(nomSiteRecherche, Integer.valueOf(paysRecherche));
            LOGGER.info(">>>>> POST : sites pour ce nom et ce pays = " + sites.size());
        } else if ((!nomSiteRecherche.isEmpty()) && (!continentRecherche.equals("0"))) {
            LOGGER.info(">>>>> POST : site recherché = " + nomSiteRecherche + " et continent = " + continentRecherche);
            sites = siteService.getSitesByNomPartielAndContinent(nomSiteRecherche, Integer.valueOf(continentRecherche));
            LOGGER.info(">>>>> POST : sites pour ce nom = " + sites.size());
        } else if (!nomSiteRecherche.isEmpty()) {
            LOGGER.info(">>>>> POST : site recherché = " + nomSiteRecherche);
            sites = siteService.getSitesByNomPartiel(nomSiteRecherche);
            LOGGER.info(">>>>> POST : sites pour ce nom = " + sites.size());
        } else if (!paysRecherche.equals("0")) {
            LOGGER.info(">>>>> POST : pays = |" + continentRecherche+"|");
            sites = siteService.getSitesByPays(Integer.valueOf(paysRecherche));
            LOGGER.info(">>>>> POST : sites pour ce pays = " + sites.size());
        } else if (!continentRecherche.equals("0")) {
            LOGGER.info(">>>>> POST : continent = |" + continentRecherche+"|");
            sites = siteService.getSitesByContinent(Integer.valueOf(continentRecherche));
            LOGGER.info(">>>>> POST : sites pour ce continent = " + sites.size());
        }

        if (!sites.isEmpty()) {
            modelAndview.addObject("resultats", sites);
            LOGGER.info(">>>>> POST : " + sites.size());
        } else {
            modelAndview.addObject("resultats", null);
        }

        modelAndview.addObject("continents", continentService.getAll());
        LOGGER.info(">>>>> POST : continent = |" + continentRecherche+"|");
        if (!continentRecherche.equals("0")) {
            ContinentDto continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));
            modelAndview.addObject("continentSelectionne", continentSelectionne);

            PaysDto paysSelectionne = new PaysDto();
            paysSelectionne.setId(0);
            paysSelectionne.setNom("Choix du pays");
            modelAndview.addObject("paysSelectionne", paysSelectionne);
            modelAndview.addObject("paysList", paysService.getPaysByContinent(continentSelectionne));
            LOGGER.info(">>>>> POST : pays : " + paysService.getPaysByContinent(continentSelectionne).size());
        } else {
            ContinentDto continentDto = new ContinentDto();
            continentDto.setId(0);
            continentDto.setNom("Choix du continent");
            modelAndview.addObject("continentSelectionne", continentDto);
        }

        LOGGER.info(">>>>> POST : continent = |" + paysRecherche+"|");
        if (!paysRecherche.equals("0")) {
            PaysDto paysSelectionne = paysService.getPaysById(Integer.valueOf(paysRecherche));
            modelAndview.addObject("paysSelectionne", paysSelectionne);

            ContinentDto continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));
            modelAndview.addObject("paysList", paysService.getPaysByContinent(continentSelectionne));
        } else {
            PaysDto paysDto = new PaysDto();
            paysDto.setId(0);
            paysDto.setNom("Choix du pays");
            modelAndview.addObject("paysSelectionne", paysDto);
        }

        SiteDto site = new SiteDto();
        if(!nomSiteRecherche.isEmpty()) {
            site.setNom(nomSiteRecherche);
        } else {
            site.setNom("");
        }
        modelAndview.addObject("site", site);
        return modelAndview;
    }
}
