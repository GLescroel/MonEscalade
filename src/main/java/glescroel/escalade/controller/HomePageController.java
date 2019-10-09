package glescroel.escalade.controller;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Site;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    SiteService siteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomePage(Model model) {
        LOGGER.debug(">>>>> Dans HomePageController");

        Site site = new Site();
        site.setNom("Nom du site");

        model.addAttribute("site", site);

        return "homepage";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView searchSite(@RequestParam(name = "recherche") String nomSiteRecherche) {

        SiteDto siteDto = siteService.getSiteByNom(nomSiteRecherche);

        ModelAndView modelAndview = new ModelAndView("homepage");
        modelAndview.addObject("resultat", siteDto);
        return modelAndview;
    }


}
