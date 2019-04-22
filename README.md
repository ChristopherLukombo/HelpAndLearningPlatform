Prérequis :

- Installer Maven

Mettre la variable dans VM arguments et au niveau du build MAVEN dans eclipse ou Intellij

-DCONF_DIR=/home/christopher/Documents

Ajouter une copie du fichier de configuration "helpAndLearningPlatform.properties" correspondant au chemin indiqué dans la variable -DCONF_DIR. Le fichier "helpAndLearningPlatform.properties" permettra d'externaliser les variables de configuration.

Aussi ajouter également la variable avec votre chemin où vous voulez mettre le fichier de log
-Dlogging.file=/home/christopher/Documents/helpAndLearningPlatform.log

Créer la base de données MySQL "helpAndLearningPlatform"

Swagger est accessible à l'URL suivante : http://localhost:8080/swagger-ui.html#
