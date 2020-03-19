monEscalade

Site de d'informations à destination des passionés d'escalade, et de prêts de "topos" (documents de présentation de sites d'escalade).

Pré-requis technique

Version de Java : 1.8
 JDK : jdk1.8.0_202
 Maven 3.6.0

Base de données : PostgresSQL

Installation et déploiement:

Packaging
mvn clean package : le fichier MonEscalade-1.0.war qui contient l'application est généré

Il est maintenant possible de lancer l'application directement dans votre IDE en exécutant le Main
ou de déployer le war dans un tomcat.

Le port de l'Application est paramétré dans application.propertie : http://localhost:8080/

L'application est livrée avec 2 configurations
•dev avec une base de données en mémoire (H2) créée à chaque lancement et peuplée avec le contenu du script src\main\resources\scripts\data.sql.
•prod avec une base de données PostgreSQL peuplée avec le contenu du script src\resources\data.sql. 
La base sera créée automatiquement au premier lancement.
En prod, il faudra ensuite modifier le ddl-auto=update dans le fichier application.properties pour qu'elle ne se recrée pas à chaque démarrage.
•test avec une base de données en mémoire (HSQL) créée à chaque lancement et peuplée avec le contenu du script src\test\resources\scripts\data.sql.

Documentation : la javadoc peut être générée via la commande mvn javadoc:javadoc puis consultée à partir de la page \target\site\apidocs\index.html

Comptes utilisateurs de test :
user@oc.com / USER = compte utilisateur
membre@oc.com / MEMBRE = compte membre de l'association
admin@oc.com / ADMIN = compte administrateur
dupont@oc.com / DUPONT = utilisateur
dubois@oc.com / DUBOIS = utilisateur
smith@oc.com / SMITH = utilisateur

Sources disponibles sur : https://github.com/GLescroel/MonEscalade
