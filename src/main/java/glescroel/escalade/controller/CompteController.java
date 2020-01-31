package glescroel.escalade.controller;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@Controller
public class CompteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompteController.class);

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping(value = "/compte")
    public String viewComptePage(Model model/*, @RequestParam(name = "email") String email*/) {
        LOGGER.info(">>>>> Dans CompteController - GetMapping");

        model.addAttribute("deleted", false);
        return "Compte";
    }

    @GetMapping(value = "/compte/suppression/{idUtilisateur}")
    public String suppressionCompteUtilisateur(Model model,
                                               @NotNull(message = "id must be not null") @PathVariable("idUtilisateur") String id) {
        LOGGER.info(">>>>> Dans CompteController - GetMapping suppression compte");

        UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(Integer.valueOf(id));
        utilisateurService.remove(utilisateur);

        model.addAttribute("deleted", true);
        return "Compte";
    }
}
