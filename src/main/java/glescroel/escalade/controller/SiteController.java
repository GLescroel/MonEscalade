package glescroel.escalade.controller;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.SecteurService;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SiteService siteService;

    @Autowired
    SecteurService secteurService;

    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.debug(">>>>> Dans SiteController");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);

        LOGGER.debug(">>>>> après site");

        List<SecteurDto> secteursList = secteurService.getSecteursBySite(site.getId());
        model.addAttribute("secteurs", secteursList);

        LOGGER.debug(">>>>> après secteur");

        return "site";
    }
}
