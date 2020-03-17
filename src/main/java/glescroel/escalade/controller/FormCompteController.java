package glescroel.escalade.controller;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.service.RoleService;
import glescroel.escalade.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormCompteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormCompteController.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Affichage du formulaire de création de compte
     * @param model
     * @return la page web "formCompte"
     */
    @GetMapping(value = "/creationCompte")
    public String viewFormComptePage(Model model) {
        LOGGER.info(">>>>> Dans FormCompteController - GetMapping");
        return "formCompte";
    }

    /**
     * Création d'un compte utilisateur si inexistant
     * @param model
     * @param nom
     * @param prenom
     * @param email
     * @param password
     * @return page web du formulaire de création de compte
     */
    @PostMapping(value = "/creationCompte")
    public String createUserAccount(Model model,
                            @RequestParam(name = "nom") String nom,
                            @RequestParam(name = "prenom") String prenom,
                            @RequestParam(name = "username") String email,
                            @RequestParam(name = "password") String password) {
        LOGGER.info(">>>>> Dans FormCompteController - PostMapping");

        UtilisateurDto utilisateur = utilisateurService.getUtilisateurByEmail(email);
        if (utilisateur != null) {
            model.addAttribute("rejected", true);
            model.addAttribute("message", "cet email existe déjà dans nos bases");
        } else {
            utilisateur = new UtilisateurDto()
                    .builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .nom(nom)
                    .prenom(prenom)
                    .role(roleService.getRoleByName("Utilisateur"))
                    .build();

            utilisateurService.save(utilisateur);

            model.addAttribute("nom", utilisateur.getNom());
            model.addAttribute("prenom", utilisateur.getPrenom());
            model.addAttribute("email", email);
            model.addAttribute("rejected", false);
            model.addAttribute("created", true);
            model.addAttribute("message", "Votre compte a été créé");
        }

        return "formCompte";
    }
}
