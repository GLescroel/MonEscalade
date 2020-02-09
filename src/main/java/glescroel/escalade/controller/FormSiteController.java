package glescroel.escalade.controller;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.CommentaireService;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.LocalisationService;
import glescroel.escalade.service.LongueurService;
import glescroel.escalade.service.PaysService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;

@Controller
public class FormSiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormSiteController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private VoieService voieService;
    @Autowired
    private LongueurService longueurService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private PaysService paysService;
    @Autowired
    private LocalisationService localisationService;
    @Autowired
    private CommentaireService commentaireService;

    @GetMapping(value = "/modifSite")
    public String viewEmptyFormSite(Model model) {
        LOGGER.info(">>>>> Dans FormSiteController - GetMapping");

        model.addAttribute("site", null);
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());
        model.addAttribute("suppression", false);

        return "formSite";
    }

    @GetMapping(value = "/modifSite/{id}")
    public String viewSiteForm(Model model, @NotNull(message = "id must be not null") @PathVariable("id") String id) {
        LOGGER.info(">>>>> Dans FormSiteController - GetMapping");
        model.addAttribute("site", siteService.getSiteById(Integer.valueOf(id)));
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("paysList", paysService.getAll());
        return "formSite";
    }

    @PostMapping(value = "/modifSite/{id}")
    public String saveSecteur(Model model,
                              @NotNull(message = "id must be not null") @PathVariable("id") String id,
                              @RequestParam(required = true, name = "nomSecteur") String nomSecteur) {
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping - saveSecteur");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        SecteurDto secteur = new SecteurDto().builder().nom(nomSecteur).site(site).build();
        secteur = secteurService.save(secteur);
        site.addSecteur(secteur);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("paysList", paysService.getAll());

        return "formSite";
    }

    @PostMapping(value = "/modifSite")
    public ModelAndView createSite(@RequestParam(required = false, name = "nomNewSite") String nomSite,
                                   @RequestParam(required = false, name = "continentSelection") String continentSelectionne,
                                   @RequestParam(required = false, name = "paysSelection") String paysSelectionne,
                                   @RequestParam(required = false, name = "region") String region,
                                   @RequestParam(required = false, name = "departement") String departement,
                                   @RequestParam(required = false, name = "ville") String ville,
                                   @RequestParam(required = false, name = "adresse") String adresse) {

        ModelAndView modelAndview = new ModelAndView("formSite");
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping");

        LocalisationDto localisation = new LocalisationDto()
                .builder()
                .continent(continentService.getContinentById(Integer.valueOf(continentSelectionne)))
                .pays(paysService.getPaysById(Integer.valueOf(paysSelectionne)))
                .region(region)
                .departement(departement)
                .ville(ville)
                .adresse(adresse)
                .build();
        localisation = localisationService.save(localisation);

        SiteDto newSite = new SiteDto()
                .builder()
                .nom(nomSite)
                .localisation(localisation)
                .build();
        newSite = siteService.save(newSite);

        modelAndview.addObject("site", newSite);
        modelAndview.addObject("suppression", false);
        modelAndview.addObject("continents", continentService.getAll());
        modelAndview.addObject("paysList", paysService.getAll());

        return modelAndview;
    }

    @PostMapping(value = "/modifSite/{id}/update")
    public String updateSite(Model model,
                              @NotNull(message = "id must be not null") @PathVariable("id") String id,
                             @RequestParam(required = true, name = "nomSite") String nomSite,
                             @RequestParam(required = true, name = "continentSelection") int idContinent,
                             @RequestParam(required = true, name = "paysSelection") int idPays,
                             @RequestParam(required = true, name = "region") String region,
                             @RequestParam(required = true, name = "departement") String departement,
                             @RequestParam(required = true, name = "ville") String ville,
                             @RequestParam(required = true, name = "adresse") String adresse) {
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping / update");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        site.setNom(nomSite);
        LocalisationDto localisation = new LocalisationDto()
                .builder()
                .continent(continentService.getContinentById(idContinent))
                .pays(paysService.getPaysById(idPays))
                .region(region)
                .departement(departement)
                .ville(ville)
                .adresse(adresse)
                .build();
        localisation = localisationService.save(localisation);
        site.setLocalisation(localisation);
        site = siteService.save(site);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("paysList", paysService.getAll());

        return "formSite";
    }

    @GetMapping(value = "/modifSite/{idSite}/deleteCommentaire/{idCommentaire}")
    public String deleteComment(Model model,
                             @NotNull(message = "id must be not null") @PathVariable("idSite") String idSite,
                             @NotNull(message = "id must be not null") @PathVariable("idCommentaire") String idCommentaire) {
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping / deleteCommentaire");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        CommentaireDto commentaire = commentaireService.getCommentaireById(Integer.valueOf(idCommentaire));

        site.removeCommentaire(commentaire);
        siteService.save(site);
        commentaireService.delete(commentaire);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("paysList", paysService.getAll());

        return "formSite";
    }

    @PostMapping(value = "/modifSite/{idSite}/updateCommentaire/{idCommentaire}")
    public String updateComment(Model model,
                             @NotNull(message = "id must be not null") @PathVariable("idSite") String idSite,
                             @NotNull(message = "id must be not null") @PathVariable("idCommentaire") String idCommentaire,
                             @RequestParam(required = true, name = "commentaire") String commentaireUpdated) {
        LOGGER.info(">>>>> Dans FormSiteController - PostMapping / updateCommentaire");

        CommentaireDto commentaire = commentaireService.getCommentaireById(Integer.valueOf(idCommentaire));
        commentaire.setCommentaire(commentaireUpdated);
        commentaireService.save(commentaire);

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("paysList", paysService.getAll());

        return "formSite";
    }

    @GetMapping(value = "/modifSite/{idSite}/suppression")
    public String deleteSite(@PathVariable("idSite") String idSite, Model model) {
        LOGGER.info(">>>>> Dans FormSiteController - GetMapping - URL : /modifSite/{id}/suppression");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        siteService.delete(site);

        model.addAttribute("suppression", true);
        model.addAttribute("site", null);
        return "formSite";
    }
}