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

@Controller
public class FormLongueurController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormLongueurController.class);

    @Autowired
    SiteService siteService;
    @Autowired
    SecteurService secteurService;
    @Autowired
    VoieService voieService;
    @Autowired
    LongueurService longueurService;

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

    @GetMapping(value = "/modifSite/{idSite}/modifSecteur/{idSecteur}/modifVoie/{idVoie}/modifLongueur/{idLongueur}/suppression")
    public String deleteSecteur(@PathVariable("idSite") String idSite,
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
