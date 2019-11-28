package glescroel.escalade.repository;

import glescroel.escalade.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

    Optional<Site> findByNomIgnoreCase(String nom);

    @Query("select site from Site site " +
            "where site.nom LIKE %:nomPartiel% " +
            "order by site.nom asc")
    List<Site> getSiteByNomPartiel(@Param("nomPartiel") String nomPartiel);

    @Query("select site from Site site " +
            "where site.localisation in (select localisation from Localisation localisation " +
                                            "where localisation.continent.id = :continentRecherche) " +
            "order by site.nom asc")
    List<Site> getSiteByContinent(@Param("continentRecherche") Integer continentRecherche);

    @Query("select site from Site site " +
            "where site.localisation in (select localisation from Localisation localisation " +
            "where localisation.pays.id = :paysRecherche) " +
            "order by site.nom asc")
    List<Site> getSiteByPays(@Param("paysRecherche") Integer paysRecherche);

    @Query("select site from Site site " +
            "where site.nom LIKE %:nomPartiel% and " +
            "site.localisation in (select localisation from Localisation localisation " +
            "where localisation.continent.id = :continentRecherche) " +
            "order by site.nom asc")
    List<Site> getSitesByNomPartielAndContinent(@Param("nomPartiel") String nomPartiel, @Param("continentRecherche") Integer continentRecherche);

    @Query("select site from Site site " +
            "where site.nom LIKE %:nomPartiel% and " +
            "site.localisation in (select localisation from Localisation localisation " +
            "where localisation.continent.id = :paysRecherche) " +
            "order by site.nom asc")
    List<Site> getSitesByNomPartielAndPays(@Param("nomPartiel") String nomPartiel, @Param("paysRecherche") Integer paysRecherche);

    Site save(Site site);
}
