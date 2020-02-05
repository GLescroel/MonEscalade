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

    @PostMapping(value = "/site", params = {"id"})
    public ModelAndView addComment(@NotNull(message = "id must be not null") @RequestParam("id") String id,
                                   @RequestParam(required = true, name = "commentaireSite") String texte,
                                   @RequestParam(required = true, name = "utilisateurCommentaire") String email) {
        LOGGER.info(">>>>> Dans SiteController - PostMapping");

        ModelAndView modelAndview = new ModelAndView("site");

        LOGGER.info("avant recup site");
        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        LOGGER.info("avant recup utilisateur");
        UtilisateurDto utilisateur = utilisateurService.getUtilisateurByEmail(email);

        LOGGER.info("avant builder commentaire");
        CommentaireDto commentaire = new CommentaireDto().builder()
                .commentaire(texte)
                .utilisateur(utilisateur)
                .build();
        LOGGER.info("avant save commentaire");
        commentaire = commentaireService.save(commentaire);
        LOGGER.info("avant utilisateur add commentaire");
        utilisateur.addCommentaire(commentaire);
        LOGGER.info("avant utilisateur save");
        utilisateurService.save(utilisateur);
        LOGGER.info("avant site add commentaire");
        site.addComment(commentaire);
        LOGGER.info("avant site save");
        site = siteService.save(site);

        modelAndview.addObject("site", site);
        modelAndview.addObject("commentaires", site.getCommentaires());
        modelAndview.addObject("commentaire", commentaire);

        modelAndview.addObject("secteurs", site.getSecteurs());

        return modelAndview;
    }

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
}
