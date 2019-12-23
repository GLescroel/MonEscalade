package glescroel.escalade.controller;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.CommentaireService;
import glescroel.escalade.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/site", params = {"id"})
    public String viewSitePage(Model model, @NotNull(message = "id must be not null") @RequestParam("id") String id) {
        LOGGER.info(">>>>> Dans SiteController - GetMapping");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));
        model.addAttribute("site", site);

        model.addAttribute("secteurs", site.getSecteurs());

        CommentaireDto commentaire = new CommentaireDto();
        commentaire.setCommentaire("");
        model.addAttribute("commentaire", commentaire);
        model.addAttribute("commentaires", site.getCommentaires());

        return "site";
    }

    @PostMapping(value = "/site", params = {"id"})
    public ModelAndView addComment(@NotNull(message = "id must be not null") @RequestParam("id") String id,
                                   @RequestParam(required = true, name = "commentaireSite") String commentaire) {
        LOGGER.info(">>>>> Dans SiteController - PostMapping");

        ModelAndView modelAndview = new ModelAndView("site");

        SiteDto site = siteService.getSiteById(Integer.valueOf(id));

        CommentaireDto commentaireDto = new CommentaireDto().builder()
                .commentaire(commentaire)
                .build();
        commentaireDto = commentaireService.save(commentaireDto);
        site.addComment(commentaireDto);
        site = siteService.save(site);

        modelAndview.addObject("site", site);
        modelAndview.addObject("commentaires", site.getCommentaires());
        modelAndview.addObject("commentaire", new CommentaireDto());

        modelAndview.addObject("secteurs", site.getSecteurs());

        return modelAndview;
    }
}
