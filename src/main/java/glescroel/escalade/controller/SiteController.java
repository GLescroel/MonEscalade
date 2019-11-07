package glescroel.escalade.controller;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Voie;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SiteService siteService;

    @Autowired
    SecteurService secteurService;

    @Autowired
    VoieService voieService;

    @Autowired
    LongueurService longueurService;

    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.debug(">>>>> Dans SiteController");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);

        LOGGER.debug(">>>>> après site");

        List<SecteurDto> secteursList = secteurService.getSecteursBySite(site.getId());

        for (SecteurDto secteur : secteursList) {
            secteur.setVoies(convertDtoToVoies(voieService.getVoiesBySecteur(secteur.getId())));
            for(Voie voie : secteur.getVoies()) {
                voie.setLongueurs(convertDtoToLongueurs(longueurService.getLongueursByVoie(voie.getId())));
            }
        }

        model.addAttribute("secteurs", secteursList);

        LOGGER.debug(">>>>> après secteur");

        return "site";
    }

    List<Voie> convertDtoToVoies(List<VoieDto> voiesDto) {

        List<Voie> voies = new ArrayList<>();
        for (VoieDto voieDto : voiesDto) {
            Voie voie = new Voie();
            voie.setId(voieDto.getId());
            voie.setNom(voieDto.getNom());
            voie.setCotation(voieDto.getCotation());
            voie.setEquipee(voieDto.isEquipee());
            voie.setLongueurs(voieDto.getLongueurs());
            voie.setCommentaires(voieDto.getCommentaires());
            voies.add(voie);
        }
        return voies;
    }

    List<Longueur> convertDtoToLongueurs(List<LongueurDto> longueursDto) {

        List<Longueur> longueurs = new ArrayList<>();
        for (LongueurDto longueurDto : longueursDto) {
            Longueur longueur = new Longueur();
            longueur.setId(longueurDto.getId());
            longueur.setNom(longueurDto.getNom());
            longueur.setCotation(longueurDto.getCotation());
            longueur.setCommentaires(longueurDto.getCommentaires());
            longueurs.add(longueur);
        }
        return longueurs;
    }

}
