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
        model.addAttribute("paysRecherche", paysSelectionne);
        model.addAttribute("paysList", null);

        return "homepage";
    }

    @PostMapping(value = "/")
    public ModelAndView searchSite(@RequestParam(required = false, name = "siteRecherche") String nomSiteRecherche,
                                   @RequestParam(required = false, name = "continentRecherche") String continentRecherche,
                                   @RequestParam(required = false, name = "paysRecherche") String paysRecherche) {

        ModelAndView modelAndview = new ModelAndView("homepage");


        PaysDto paysSelectionne = checkPaysContinentConsistency(paysRecherche, continentRecherche);
        modelAndview.addObject("paysSelectionne", paysSelectionne);

        if(paysSelectionne.getId().equals(0)) {
            paysRecherche = null;
        }

        SiteDto site = new SiteDto();
        if (!nomSiteRecherche.isEmpty()) {
            site.setNom(nomSiteRecherche);
        } else {
            site.setNom("");
        }
        modelAndview.addObject("site", site);



        List<SiteDto> sites = new ArrayList<>();

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
        }

        if (!sites.isEmpty()) {
            modelAndview.addObject("resultats", sites);
        } else {
            modelAndview.addObject("resultats", null);
        }

        modelAndview.addObject("continents", continentService.getAll());
        if (!continentRecherche.equals("0")) {
            ContinentDto continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));
            modelAndview.addObject("continentSelectionne", continentSelectionne);

            modelAndview.addObject("paysList", paysService.getPaysByContinent(continentSelectionne));
        } else {
            ContinentDto continentDto = new ContinentDto();
            continentDto.setId(0);
            continentDto.setNom("Choix du continent");
            modelAndview.addObject("continentSelectionne", continentDto);
        }


        return modelAndview;

    }

    private PaysDto checkPaysContinentConsistency(String paysRecherche, String continentRecherche) {

        boolean paysContinentOk = false;
        PaysDto paysSelectionne = new PaysDto();
        if ((paysRecherche != null) && (!paysRecherche.equals("0"))) {
            paysSelectionne = paysService.getPaysById(Integer.valueOf(paysRecherche));

            ContinentDto continentSelectionne = continentService.getContinentById(Integer.valueOf(continentRecherche));

            for (int i = 0; i < paysService.getPaysByContinent(continentSelectionne).size(); i++) {
                if (paysSelectionne.getNom() == paysService.getPaysByContinent(continentSelectionne).get(i).getNom()) {
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
}
