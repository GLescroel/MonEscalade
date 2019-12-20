package glescroel.escalade.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glescroel.escalade.controller.config.StepDefs;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.LocalisationService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.SiteService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePageControllerSteps extends StepDefs {

    private String nomSiteRecherche;
    private String continentRecherche;
    private String paysRecherche;
    private String regionRecherche;
    private ModelAndView modelAndView;
    private String cotationMinRecherche;
    private String cotationMaxRecherche;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SiteService siteService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private PaysService paysService;
    @Autowired
    private LocalisationService localisationService;

    private static final int SITE_COLUMN = 0;
    private static final int CONTINENT_COLUMN = 1;
    private static final int PAYS_COLUMN = 2;
    private static final int REGION_COLUMN = 3;
    private static final int COTATION_MIN_COLUMN = 4;
    private static final int COTATION_MAX_COLUMN = 5;

    @Given("je saisis les paramètres de recherche suivants")
    public void selectSearchCriteria(DataTable searchCriteria) {
        List<List<String>> data = searchCriteria.raw();
        nomSiteRecherche = data.get(1).get(SITE_COLUMN);
        continentRecherche = data.get(1).get(CONTINENT_COLUMN);
        paysRecherche = data.get(1).get(PAYS_COLUMN);
        regionRecherche = data.get(1).get(REGION_COLUMN);
        cotationMinRecherche = data.get(1).get(COTATION_MIN_COLUMN);
        cotationMaxRecherche = data.get(1).get(COTATION_MAX_COLUMN);
    }

    @When("je clique sur le bouton Rechercher")
    @Transactional
    public void searchRequest() {
        HomePageController homePageController = new HomePageController(continentService, paysService, siteService, localisationService);
        modelAndView = homePageController.searchSite(nomSiteRecherche, continentRecherche, paysRecherche, regionRecherche, cotationMinRecherche, cotationMaxRecherche);
    }

    @Then("La liste (.*) des sites du fichier rechercheSiteResultat.json s'affiche")
    public void checkResult(String resultats) throws IOException {

        List<SiteDto> sites = (List<SiteDto>) modelAndView.getModel().get("resultats");

        ClassLoader classLoader = getClass().getClassLoader();
        String nomFichier = "rechercheSiteResultat.json";
        String path = "bdd/" + nomFichier;
        URL url = classLoader.getResource(path);
        File file;
        byte[] jsonData = null;
        if (url != null) {
            file = new File(url.getFile());
            jsonData = Files.readAllBytes(file.toPath());
        }
        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode jsonNode = rootNode.get(resultats);
        String cras = jsonNode.toString();
        jsonData = cras.getBytes("utf-8");
        Set<SiteDto> expectedSites = objectMapper.readValue(jsonData, new TypeReference<HashSet<SiteDto>>() {
        });

        Assert.assertEquals(expectedSites.size(), sites.size());
        compareSites(expectedSites, sites);
    }

    private void compareSites(Set<SiteDto> expectedSites, List<SiteDto> actualSites) {
        for (SiteDto expectedSite : expectedSites) {
            boolean found = false;
            for (SiteDto actualSite : actualSites) {
                if(expectedSite.getNom().equals(actualSite.getNom())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
