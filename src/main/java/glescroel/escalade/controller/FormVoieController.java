package glescroel.escalade.controller;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormVoieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormVoieController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private VoieService voieService;
    @Autowired
    private LongueurService longueurService;

    /**
     * Affichage du forulaire de modification de la voie
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param model
     * @return la page web du formulaire de la voie
     */
    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}")
    public String viewFormVoie(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               @PathVariable("idVoie") String idVoie,
                               Model model) {
        LOGGER.info(">>>>> Dans FormVoieController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));
        VoieDto voie = voieService.getVoieById(Integer.valueOf(idVoie));
        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voie", voie);
        model.addAttribute("longueurs", voie.getLongueurs());
        model.addAttribute("suppression", false);

        return "formVoie";
    }

    /**
     * Mise à jour de la voie
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param model
     * @param nomVoie
     * @param voieEquipee
     * @param cotation
     * @return la page web du formulaire de la voie
     */
    @PostMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/update")
    public String viewUpdateVoie(@PathVariable("idSite") String idSite,
                                 @PathVariable("idSecteur") String idSecteur,
                                 @PathVariable("idVoie") String idVoie,
                                 Model model,
                                 @RequestParam(required = true, name = "nomVoie") String nomVoie,
                                 @RequestParam(required = false, name = "voieEquipee") boolean voieEquipee,
                                 @RequestParam(required = false, name = "cotationVoie") String cotation) {
        LOGGER.info(">>>>> Dans FormVoieController - PostMapping update");

        VoieDto voie = voieService.getVoieById(Integer.valueOf(idVoie));
        voie.setNom(nomVoie);
        voie.setCotation(cotation);
        voie.setEquipee(voieEquipee);
        voie.setSecteur(secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        voie = voieService.save(voie);

        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));
        model.addAttribute("secteur", secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        model.addAttribute("voie", voie);
        model.addAttribute("longueurs", voie.getLongueurs());
        model.addAttribute("suppression", false);

        return "formVoie";
    }

    /**
     * Ajout d'une longueur dans la voie
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param model
     * @param nomLongueur
     * @param cotation
     * @return la page web du formulaire de la voie
     */
    @PostMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}")
    public String viewAddLongueur(@PathVariable("idSite") String idSite,
                                 @PathVariable("idSecteur") String idSecteur,
                                 @PathVariable("idVoie") String idVoie,
                                 Model model,
                                 @RequestParam(required = true, name = "nomLongueur") String nomLongueur,
                                 @RequestParam(required = false, name = "cotation") String cotation) {
        LOGGER.info(">>>>> Dans FormVoieController - PostMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));
        VoieDto voie = voieService.getVoieById(Integer.valueOf(idVoie));

        LongueurDto longueur = new LongueurDto()
                .builder()
                .nom(nomLongueur)
                .voie(voie)
                .cotation(cotation)
                .build();
        longueur = longueurService.save(longueur);
        voie.addLongueur(longueur);

        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voie", voie);
        model.addAttribute("longueurs", voie.getLongueurs());
        model.addAttribute("suppression", false);

        return "formVoie";
    }

    /**
     * Suppression de la voie
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param model
     * @return la page web du formulaire de la voie
     */
    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/suppression")
    public String deleteSecteur(@PathVariable("idSite") String idSite,
                                @PathVariable("idSecteur") String idSecteur,
                                @PathVariable("idVoie") String idVoie,
                                Model model) {
        LOGGER.info(">>>>> Dans FormVoieController - GetMapping - URL : /modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/suppression");

        VoieDto voie = voieService.getVoieById(Integer.valueOf(idVoie));
        voieService.delete(voie);

        model.addAttribute("suppression", true);
        model.addAttribute("voie", null);
        model.addAttribute("secteur", secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));

        return "formVoie";
    }
}