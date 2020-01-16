package glescroel.escalade.controller;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.service.SecteurService;
import glescroel.escalade.service.SiteService;
import glescroel.escalade.service.VoieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormSecteurController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormSecteurController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private VoieService voieService;

    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}")
    public String viewFormSecteur(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               Model model) {
        LOGGER.info(">>>>> Dans FormSecteurController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));
        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voies", secteur.getVoies());
        model.addAttribute("suppression", false);

        return "formSecteur";
    }

    @PostMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}")
    public String addVoie(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               Model model,
                               @RequestParam(required = true, name = "nomVoie") String nomVoie,
                               @RequestParam(required = false, name = "cotation") String cotation,
                               @RequestParam(required = false, name = "voieEquipee") boolean isEquipee) {
        LOGGER.info(">>>>> Dans FormSecteurController - PostMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));

        VoieDto voie = new VoieDto()
                .builder()
                .nom(nomVoie)
                .secteur(secteur)
                .cotation(cotation)
                .equipee(isEquipee)
                .build();
        voie = voieService.save(voie);
        secteur.addVoie(voie);

        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voies", secteur.getVoies());
        model.addAttribute("suppression", false);

        return "formSecteur";
    }

    @PostMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/update")
    public String updateNomSecteur(@PathVariable("idSite") String idSite,
                                    @PathVariable("idSecteur") String idSecteur,
                                    @RequestParam("nomSecteur") String nomSecteur,
                                    Model model) {
        LOGGER.info(">>>>> Dans FormSecteurController - PostMapping / update");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));
        secteur.setNom(nomSecteur);
        secteur.setSite(site);
        secteurService.save(secteur);

        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voies", secteur.getVoies());
        model.addAttribute("suppression", false);

        return "formSecteur";
    }

    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/suppression")
    public String deleteSecteur(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               Model model) {
        LOGGER.info(">>>>> Dans FormSecteurController - PostMapping - URL : /modifSite/{idSite}/modifSecteur/{idSecteur}/suppression");

        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));

        LOGGER.info("avant delete");
        secteurService.delete(secteur);
        LOGGER.info("après delete");

        model.addAttribute("suppression", true);
        model.addAttribute("secteur", null);
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));

        return "formSecteur";
    }
}