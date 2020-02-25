package glescroel.escalade.controller;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.dto.TopoDto;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.EtatService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.TopoService;
import glescroel.escalade.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class TopoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopoController.class);

    @Autowired
    TopoService topoService;
    @Autowired
    ContinentService continentService;
    @Autowired
    PaysService paysService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    EtatService etatService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping(value = "/topo")
    public String viewTopoPage(Model model) {
        LOGGER.info(">>>>> Dans TopoController - GetMapping");

        model.addAttribute("mode", "ALL");
        model.addAttribute("topos", topoService.getAll());
        model.addAttribute("reservations", new ArrayList<>());

        return "topo";
    }

    @PostMapping(value = "/topo/reservation/{idTopo}")
    public String bookTopo(Model model, @NotNull(message = "idTopo must be not null") @PathVariable("idTopo") String topoId,
                           @RequestParam(required = true, name = "emprunteur") String email) {
        LOGGER.info(">>>>> Dans TopoController - GetMapping topo/reservation");

        TopoDto topo = topoService.getTopoById(Integer.valueOf(topoId));
        topo.setEtat(etatService.getEtatByNom("Reserve"));
        topo.setEmprunteur(utilisateurService.getUtilisateurByEmail(email));
        topoService.save(topo);

        model.addAttribute("mode", "ALL");
        model.addAttribute("topos", topoService.getAll());

        return "topo";
    }

    @PostMapping(value = "/topo/pret/{idTopo}")
    public String giveTopo(Model model, @NotNull(message = "idTopo must be not null") @PathVariable("idTopo") String topoId) {
        LOGGER.info(">>>>> Dans TopoController - GetMapping topo/pret");

        TopoDto topo = topoService.getTopoById(Integer.valueOf(topoId));
        topo.setEtat(etatService.getEtatByNom("Indisponible"));
        topoService.save(topo);

        model.addAttribute("mode", "USER");
        model.addAttribute("topos", topoService.getToposByUtilisateur(Integer.valueOf(topo.getProprietaire().getId())));
        model.addAttribute("reservations", topoService.getToposByEmprunteur(Integer.valueOf(topo.getProprietaire().getId())));
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());

        return "topo";
    }

    @PostMapping(value = "/topo/rendre/{idTopo}")
    public String giveBackTopo(Model model, @NotNull(message = "idTopo must be not null") @PathVariable("idTopo") String topoId,
                               @RequestParam(required = true, name = "emprunteur") String idEmprunteur) {
        LOGGER.info(">>>>> Dans TopoController - GetMapping topo/rendre");

        TopoDto topo = topoService.getTopoById(Integer.valueOf(topoId));
        topo.setEtat(etatService.getEtatByNom("Disponible"));
        topo.setEmprunteur(null);
        topoService.save(topo);

        model.addAttribute("mode", "USER");
        model.addAttribute("topos", topoService.getToposByUtilisateur(Integer.valueOf(idEmprunteur)));
        model.addAttribute("reservations", topoService.getToposByEmprunteur(Integer.valueOf(idEmprunteur)));
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());

        return "topo";
    }

    @GetMapping(value = "/mesTopos/{userId}")
    public String viewMyTopoPage(Model model, @NotNull(message = "userId must be not null") @PathVariable("userId") String userId) {
        LOGGER.info(">>>>> Dans TopoController - GetMapping MyTopo");

        model.addAttribute("mode", "USER");
        model.addAttribute("topos", topoService.getToposByUtilisateur(Integer.valueOf(userId)));
        model.addAttribute("reservations", topoService.getToposByEmprunteur(Integer.valueOf(userId)));
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());
        return "topo";
    }

    @PostMapping(value = "/mesTopos/{userId}/newTopo")
    public String addUserTopo(Model model, @NotNull(message = "userId must be not null") @PathVariable("userId") String userId,
                              @RequestParam(required = false, name = "nomNewTopo") String nomTopo,
                              @RequestParam(required = false, name = "continentSelection") String continentSelectionne,
                              @RequestParam(required = false, name = "paysSelection") String paysSelectionne,
                              @RequestParam(required = false, name = "lieu") String lieu,
                              @RequestParam(required = false, name = "description") String description,
                              @RequestParam(required = false, name = "dateParution") String dateParution) throws ParseException {
        LOGGER.info(">>>>> Dans TopoController - PostMapping addUserTopo");

        TopoDto topo = new TopoDto()
                .builder()
                .nom(nomTopo)
                .description(description)
                .parution(new SimpleDateFormat("yyyy-MM-dd").parse(dateParution))
                .continent(continentService.getContinentById(Integer.valueOf(continentSelectionne)))
                .pays(paysService.getPaysById(Integer.valueOf(paysSelectionne)))
                .lieu(lieu)
                .proprietaire(utilisateurService.getUtilisateurById(Integer.valueOf(userId)))
                .etat(etatService.getEtatByNom("Disponible"))
                .build();

        topoService.save(topo);

        model.addAttribute("mode", "USER");
        model.addAttribute("topos", topoService.getToposByUtilisateur(Integer.valueOf(userId)));
        model.addAttribute("reservations", topoService.getToposByEmprunteur(Integer.valueOf(userId)));
        model.addAttribute("continents", continentService.getAll());
        model.addAttribute("continentSelectionne", new ContinentDto());
        model.addAttribute("paysList", paysService.getAll());
        model.addAttribute("paysSelectionne", new PaysDto());
        model.addAttribute("localisation", new LocalisationDto());

        return "topo";
    }

}
