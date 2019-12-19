package glescroel.escalade.controller.config;


import glescroel.escalade.service.ContinentService;
import glescroel.escalade.service.LocalisationService;
import glescroel.escalade.service.PaysService;
import glescroel.escalade.service.SecteurService;
import glescroel.escalade.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;

@Configuration
@EnableTransactionManagement
public class TestConfig {

    private final EntityManager entityManager;
    private final SiteService siteService;
    private final ContinentService continentService;
    private final PaysService paysService;
    private final SecteurService secteurService;
    private final LocalisationService localisationService;

    @Autowired
    public TestConfig(EntityManager entityManager,
                      SiteService siteService,
                      ContinentService continentService,
                      PaysService paysService,
                      SecteurService secteurService,
                      LocalisationService localisationService) {
        this.entityManager = entityManager;
        this.siteService = siteService;
        this.continentService = continentService;
        this.paysService = paysService;
        this.secteurService = secteurService;
        this.localisationService = localisationService;
    }
}
