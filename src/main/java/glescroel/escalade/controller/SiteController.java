package glescroel.escalade.controller;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.service.CommentaireService;
import glescroel.escalade.service.SiteService;
import glescroel.escalade.service.UtilisateurService;
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
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private SiteService siteService;
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Affichage de la page du site
     * @param model
     * @param id
     * @return la page du site
     */
    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.info(">>>>> Dans SiteController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);

        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("commentaire", new CommentaireDto().builder().commentaire("").build());
        model.addAttribute("commentaires", site.getCommentaires());

        return "site";
    }

    /**
     * Ajout d"un commentaire sur le site
     * @param id
     * @param texte
     * @param email
     * @return la page web du site
     */
    @PostMapping(value = "/site", params = {"id"})
    public ModelAndView addComment(@NotNull(message = "id must be not null") @RequestParam("id") String id,
                                   @RequestParam(required = true, name = "commentaireSite") String texte,
                                   @RequestParam(required = true, name = "utilisateurCommentaire") String email) {
        LOGGER.info(">>>>> Dans SiteController - PostMapping");

        ModelAndView modelAndview = new ModelAndView("site");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        UtilisateurDto utilisateur = utilisateurService.getUtilisateurByEmail(email);

        CommentaireDto commentaire = new CommentaireDto().builder()
                .commentaire(texte)
                .utilisateur(utilisateur)
                .build();
        commentaire = commentaireService.save(commentaire);
        utilisateur.addCommentaire(commentaire);
        utilisateurService.save(utilisateur);
        site.addComment(commentaire);
        site = siteService.save(site);

        modelAndview.addObject("site", site);
        modelAndview.addObject("commentaires", site.getCommentaires());
        modelAndview.addObject("commentaire", commentaire);

        modelAndview.addObject("secteurs", site.getSecteurs());

        return modelAndview;
    }

    /**
     * Tag d'un site
     * @param model
     * @param idSite
     * @return la page web du site
     */
    @GetMapping(value = "/site/{idSite}/tag")
    public String tagSite(Model model, @NotNull(message = "id must be not null") @PathVariable("idSite") String idSite) {
        LOGGER.info(">>>>> Dans SiteController - PostMapping Tag");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        site.setTag(true);
        siteService.save(site);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("commentaires", site.getCommentaires());
        model.addAttribute("commentaire", new CommentaireDto().builder().commentaire("").build());

        return "redirect:/site?id=" + site.getId();
    }

    /**
     * Détag d'un site
     * @param model
     * @param idSite
     * @return la page web du site
     */
    @GetMapping(value = "/site/{idSite}/untag")
    public String untagSite(Model model, @NotNull(message = "id must be not null") @PathVariable("idSite") String idSite) {
        LOGGER.info(">>>>> Dans SiteController - PostMapping unTag");

        SiteDto site = siteService.getSiteById(Integer.valueOf(idSite));
        site.setTag(false);
        siteService.save(site);

        model.addAttribute("site", site);
        model.addAttribute("secteurs", site.getSecteurs());
        model.addAttribute("commentaires", site.getCommentaires());
        model.addAttribute("commentaire", new CommentaireDto().builder().commentaire("").build());

        return "redirect:/site?id=" + site.getId();
    }
}
