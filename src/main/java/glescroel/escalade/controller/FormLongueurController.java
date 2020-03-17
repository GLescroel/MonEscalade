package glescroel.escalade.controller;

import glescroel.escalade.dto.LongueurDto;
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
public class FormLongueurController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormLongueurController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private VoieService voieService;
    @Autowired
    private LongueurService longueurService;

    /**
     * Affichage du formulaire de modification d'une longueur
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param idLongueur
     * @param model
     * @return la page web de formulaire de longueur
     */
    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/modifLongueur/{idLongueur}")
    public String viewFormLongueur(@PathVariable("idSite") String idSite,
                                @PathVariable("idSecteur") String idSecteur,
                                @PathVariable("idVoie") String idVoie,
                                @PathVariable("idLongueur") String idLongueur,
                                Model model) {
        LOGGER.info(">>>>> Dans FormLongueurController - GetMapping");

        model.addAttribute("suppression", false);
        model.addAttribute("longueur", longueurService.getLongueurById(Integer.valueOf(idLongueur)));
        model.addAttribute("voie", voieService.getVoieById(Integer.valueOf(idVoie)));
        model.addAttribute("secteur", secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));

        return "formLongueur";
    }

    /**
     * Mise à jour de la longueur
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param idLongueur
     * @param nomLongueur
     * @param cotation
     * @param model
     * @return la page web du formulaire de la longueur
     */
    @PostMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/modifLongueur/{idLongueur}")
    public String updateLongueur(@PathVariable("idSite") String idSite,
                                   @PathVariable("idSecteur") String idSecteur,
                                   @PathVariable("idVoie") String idVoie,
                                   @PathVariable("idLongueur") String idLongueur,
                                 @RequestParam(required = true, name = "nomLongueur") String nomLongueur,
                                 @RequestParam(required = true, name = "cotation") String cotation,
                                   Model model) {
        LOGGER.info(">>>>> Dans FormLongueurController - PostMapping");

        LongueurDto longueur = longueurService.getLongueurById(Integer.valueOf(idLongueur));
        longueur.setNom(nomLongueur);
        longueur.setCotation(cotation);
        longueur.setVoie(voieService.getVoieById(Integer.valueOf(idVoie)));
        longueur = longueurService.save(longueur);

        model.addAttribute("suppression", false);
        model.addAttribute("longueur", longueurService.getLongueurById(Integer.valueOf(idLongueur)));
        model.addAttribute("voie", voieService.getVoieById(Integer.valueOf(idVoie)));
        model.addAttribute("secteur", secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));

        return "formLongueur";
    }

    /**
     * Suppression de la longueur
     * @param idSite
     * @param idSecteur
     * @param idVoie
     * @param idLongueur
     * @param model
     * @return la page web du formulaire de la longueur
     */
    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/modifLongueur/{idLongueur}/suppression")
    public String deleteLongueur(@PathVariable("idSite") String idSite,
                                @PathVariable("idSecteur") String idSecteur,
                                @PathVariable("idVoie") String idVoie,
                                @PathVariable("idLongueur") String idLongueur,
                                Model model) {
        LOGGER.info(">>>>> Dans FormLongueurController - GetMapping - URL : /modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/modifLongueur/{idLongueur}/suppression");

        LongueurDto longueur = longueurService.getLongueurById(Integer.valueOf(idLongueur));
        longueurService.delete(longueur);

        model.addAttribute("suppression", true);
        model.addAttribute("longueur", null);
        model.addAttribute("voie", voieService.getVoieById(Integer.valueOf(idVoie)));
        model.addAttribute("secteur", secteurService.getSecteurById(Integer.valueOf(idSecteur)));
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(idSite)));

        return "formLongueur";
    }

}
