package glescroel.escalade.controller;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.mapper.LongueurMapper;
import glescroel.escalade.mapper.SecteurMapper;
import glescroel.escalade.mapper.SiteMapper;
import glescroel.escalade.mapper.VoieMapper;
import glescroel.escalade.service.LongueurService;
import glescroel.escalade.service.SecteurService;
import glescroel.escalade.service.SiteService;
import glescroel.escalade.service.VoieService;
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
    private static final SiteMapper SITE_MAPPER = SiteMapper.INSTANCE;
    private static final SecteurMapper SECTEUR_MAPPER = SecteurMapper.INSTANCE;
    private static final VoieMapper VOIE_MAPPER = VoieMapper.INSTANCE;
    private static final LongueurMapper LONGUEUR_MAPPER = LongueurMapper.INSTANCE;

    @Autowired
    private SiteService siteService;

    @Autowired
    private SecteurService secteurService;

    @Autowired
    private VoieService voieService;

    @Autowired
    private LongueurService longueurService;

    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.debug(">>>>> Dans SiteController");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);

        LOGGER.debug(">>>>> après site");

        List<SecteurDto> secteursList = secteurService.getSecteursBySite(site.getId());

        for (SecteurDto secteur : secteursList) {
            secteur.setVoies(voieService.getVoiesBySecteur(secteur.getId()));
            for(VoieDto voie : secteur.getVoies()) {
                voie.setLongueurs(longueurService.getLongueursByVoie(voie.getId()));
            }
        }

        model.addAttribute("secteurs", secteursList);

        LOGGER.debug(">>>>> après secteur");

        return "site";
    }
}
