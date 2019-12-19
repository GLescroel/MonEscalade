#Cette feature traite la recherche de sites sur la page d'accueil
Feature: §IHM - Pouvoir rechercher des sites sur la page d'accueil
  En tant qu'utilisateur du site,
  Je veux pouvoir saisir tout ou partie d'un nom de site et/ou choisir un continent et éventuellement choisir un pays
  Afin de pouvoir afficher la liste des sites qui répondent aux critères.

  Scenario Outline: Filtrer la liste des sites en fonction des critères saisis/sélectionnés
    Given je saisis les paramètres de recherche suivants
      | nom       | continent   | pays   | region   | rechercheSiteResultat  |
      | <nom>     | <continent> | <pays> | <region> | <rechercheSiteResultat>|
    When je clique sur le bouton Rechercher
    Then La liste <rechercheSiteResultat> des sites du fichier rechercheSiteResultat.json s'affiche
    Examples:
      | nom       | continent   | pays   | region   | rechercheSiteResultat  |
      | Mont      |      0      |   0    |          | rechercheSiteResultat1 |
      |           |      1      |   0    |          | rechercheSiteResultat2 |
      |           |      1      |   1    |  PACA    | rechercheSiteResultat3 |
      | Mont      |      1      |   1    |          | rechercheSiteResultat4 |
