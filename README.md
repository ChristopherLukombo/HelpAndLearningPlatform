Prérequis :

- Installer Maven

Mettre la variable dans VM arguments et au niveau du build MAVEN dans eclipse ou Intellij

-DCONF_DIR=/home/christopher/Documents

Ajouter une copie du fichier de configuration "helpAndLearningPlatform.properties" correspondant au chemin indiqué dans la variable -DCONF_DIR. Le fichier "helpAndLearningPlatform.properties" permettra d'externaliser les variables de configuration.

Aussi, mettre également la variable dans VM arguments au niveau du build MAVEN dans eclipse ou Intellij
-Dlogging.config="file:C:\Users\Christopher LUKOMBO\ProgramDev\conf\helpAndLearningPlatform\logback-spring.xml"

Puis, ajouter une copie du fichier logback-spring.xml dans le chemin spécifié.
Editez ensuite le fichier logback-spring.xml, remplacer la valeur de la property value par le chemin où vous voulez stocker les logs.
<property name="LOGS" value="C:\\Users\\Christopher LUKOMBO\\ProgramDev\\logs\\helpAndLearningPlatform" />

Créer la base de données MySQL "helpAndLearningPlatform"

Swagger est accessible à l'URL suivante : http://localhost:8080/swagger-ui.html#
