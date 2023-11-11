Killian Gervais & Gabriel Hazan - IFT3923-TP2

Lien vers le repository GitHub: https://github.com/kgerv/IFT3923-TP2

Obtention des métriques :

La métrique que nous avons implémenté est celle de l'AGE, JFreeChart-Analysis.jar, qui retourne l'age d'un fichier.
Les données collectées se retrouvent dans le fichier "outdated.csv". Nous avons aussi utilisé
la métrique TASSERT mais via un appel de tropcomp.jar, les résulats sont dans le fichier tassert.csv.
Les autres métriques ont été obtenues à l'aide de divers outils (plugins) que l'on retrouve dans l'IDE Intellij.
Ces outils sont instalables en allant dans "settings" > "plugins" > "marketplace". Les outils en 
questions sont metricsTree, statistic et coverage(deja inclu dans l'IDE). Toutes les données 
collectés ont été mis dans un fichier excel "jfreeClasses.xlsx".

Pour l'usage de tropcomp.jar voir la documentation ici:
https://github.com/kgerv/IFT3923-TP1

Pour l'usage de JFreeChart-Analysis.jar:
1. copier le fichier jar dans le dossier jfreechart-master
2. lancer le fichier via un appel en ligne de commande en se trouvant dans le dossier jfreechart-master:
	java -jar JFreeChart-Analysis.jar
	java -jar JFreeChart-Analysis.jar value_outdated_file_in_days
	java -jar JFreeChart-Analysis.jar value_outdated_file_in_days path_cvs_file.cvs

Toutes les facon de lancer le jar vont afficher l'analyse en ligne de commande. 
Le second permet de changer la valeur du nombre de jours décrétant qu'un fichier de test n'est pas à 
jour en comparaison au fichier qu'il teste, par défaut celle-ci est 7 jours.
La derniere permet de sauvegarder le résultat dans un fichier csv a l'aide du chemin fournit.
Ces outils sont instalables en allant dans "settings" > "plugins" > "marketplace".
Les outils en questions sont metricsTree, statistic et coverage(deja inclu dans l'IDE).
Toutes les données collectés ont été mis dans un fichier excel "jfreeClasses.xlsx".

Guide d'utilisation metricsTree :
après installation, clicker sur "View" > "Tool Windows" > "MetricsTree". Ensuite clicker sur project metrics.

Guide d'utilisation coverage :
Clicker sur "Run" > "Run 'all in jfreeCharts' with coverage ". 

Guide d'utilisation Statistic :
Après installation, clicker sur "View" > "Tool Windows" > "Statistic". Clicker sur le dossier sur lequel vous voulez les metriques, dans notre cas, nous avons sélectionner le dosser "java" du dossier "test". Ensuite, clicker sur "Refresh on selection" dans la fenetre de Statistic.

