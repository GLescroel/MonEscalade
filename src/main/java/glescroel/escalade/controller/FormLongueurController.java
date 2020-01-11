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

    @GetMapping(value = "/newSite/{idSite}/newSecteur/{idSecteur}/newVoie/{idVoie}/newLongueur")
    public String viewFormSite(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               @PathVariable("idVoie") String idVoie,
                               Model model) {
        LOGGER.info(">>>>> Dans FormLongueurController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        SecteurDto secteur = secteurService.getSecteurById(Integer.valueOf(idSecteur));
        VoieDto voie = voieService.getVoieById(Integer.valueOf(idVoie));
        model.addAttribute("site", site);
        model.addAttribute("secteur", secteur);
        model.addAttribute("voie", voie);
        model.addAttribute("longueurs", voie.getLongueurs());

        return "formLongueur";
    }

    @PostMapping(value = "/newSite/{idSite}/newSecteur/{idSecteur}/newVoie/{idVoie}/newLongueur")
    public String viewFormSite(@PathVariable("idSite") String idSite,
                               @PathVariable("idSecteur") String idSecteur,
                               @PathVariable("idVoie") String idVoie,
                               Model model,
                               @RequestParam(required = true, name = "nomLongueur") String nomLongueur,
                               @RequestParam(required = false, name = "cotation") String cotation) {
        LOGGER.info(">>>>> Dans FormLongueurController - PostMapping");

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

        return "formLongueur";
    }
}