# Killian Gervais & Gabriel Hazan - IFT3923-TP1

https://github.com/kgerv/IFT3923-TP1

Pour executer les fichier .jar present vous devez les telecharger, puis les appeler a partir de la ligne de commande
comme suit:
```
java -jar tloc.jar chemin_du_fichier_de_test.java
java -jar tassert.jar chemin_du_fichier_de_test.java
java -jar tls.jar chemin_du_projet_java_a_mesurer
java -jar tropcomp.jar chemin_du_projet_java_a_mesurer valeur_entiere_du_seuil_superieur_souhaite
```

Par defaut la sortie se fait en ligne de commande mais vous pouvez faire en sorte que les sorties de tls.jar et tropcomp.jar se retrouvent dans un fichier CSV comme suit:
```
java -jar tls.jar -o chemin_du_fichier.csv chemin_du_projet_java_a_mesurer
java -jar tropcomp.jar -o chemin_du_fichier.csv chemin_du_projet_java_a_mesurer valeur_entiere_du_seuil_superieur_souhaite
```
