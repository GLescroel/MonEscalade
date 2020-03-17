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

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class CompteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompteController.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private SiteService siteService;

    /**
     * Affichage de la page du compte utilisateur
     * @param model
     * @return la page web "compte"
     */
    @GetMapping(value = "/compte")
    public String viewComptePage(Model model) {
        LOGGER.info(">>>>> Dans CompteController - GetMapping");

        model.addAttribute("deleted", false);
        return "compte";
    }

    /**
     * Suppression du compte de l'utilisateur
     * @param model
     * @param id
     * @return la page web "compte"
     */
    @GetMapping(value = "/compte/suppression/{idUtilisateur}")
    public String suppressionCompteUtilisateur(Model model,
                                               @NotNull(message = "id must be not null") @PathVariable("idUtilisateur") String id) {
        LOGGER.info(">>>>> Dans CompteController - GetMapping suppression compte");

        UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(Integer.valueOf(id));
        utilisateur.removeAllCommentaires();
        utilisateurService.save(utilisateur);

        List<CommentaireDto> commentaireList= commentaireService.getCommentairesUtilisateur(Integer.valueOf(id));
        for (CommentaireDto commentaire : commentaireList) {
            SiteDto site = siteService.findSiteForCommentaire(commentaire);
            site.removeCommentaire(commentaire);
            siteService.save(site);
            commentaireService.delete(commentaire);
        }

        utilisateurService.remove(utilisateur);

        model.addAttribute("deleted", true);
        return "compte";
    }
}
