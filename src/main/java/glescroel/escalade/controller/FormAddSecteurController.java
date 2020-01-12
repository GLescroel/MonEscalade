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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormAddSecteurController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormAddSecteurController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;

    @GetMapping(value = "/modifSite/{id}/newSecteur")
    public String viewFormSite(@PathVariable("id") String id, Model model) {
        LOGGER.info(">>>>> Dans FormSecteurController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());

        return "formAddSecteur";
    }

    @PostMapping(value = "/modifSite/{id}/newSecteur")
    public String saveSecteur(@PathVariable("id") String id, Model model,
                              @RequestParam(required = true, name = "nomSecteur") String nomSecteur) {
        LOGGER.info(">>>>> Dans FormSecteurController - PostMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        SecteurDto secteur = new SecteurDto().builder().nom(nomSecteur).site(site).build();
        secteur = secteurService.save(secteur);
        site.addSecteur(secteur);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());

        return "formAddSecteur";
    }
}
