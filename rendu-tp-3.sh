#!/bin/sh

echo "Question 1\n"

java -jar bioseq.jar best-matches 3 "########" read1.fasta TRGV.fasta

echo "\nQuestion 2\n"

java -jar bioseq.jar VJ-discover "########" reads-TRG.fasta TRGV.fasta TRGJ.fasta

echo "\nQuestion 3"
echo "\nAvec read1 et TRGV"
java -jar bioseq.jar best-matches 5 "##-##" read1.fasta TRGV.fasta
echo "\nAvec read2 et TRGV"
java -jar bioseq.jar best-matches 5 "##-##" read2.fasta TRGV.fasta
echo "\nGraine ####"
java -jar bioseq.jar VJ-discover "####" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ########"
java -jar bioseq.jar VJ-discover "########" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ############"
java -jar bioseq.jar VJ-discover "############" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ################"
java -jar bioseq.jar VJ-discover "################" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ####################"
java -jar bioseq.jar VJ-discover "####################" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ####-####"
java -jar bioseq.jar VJ-discover "####-####" reads-TRG.fasta TRGV.fasta TRGJ.fasta
echo "\nGraine ##-##-##-##"
java -jar bioseq.jar VJ-discover "##-##-##-##" reads-TRG.fasta TRGV.fasta TRGJ.fasta

echo "En prenant des kmers de taille 4, on obtient un résultat non significatif (256 possibilités de kmers différents).
Il en va de même pour des kmers avec une trop grande longueur (16 et 20 par exemple), les résultats ne fonctionnent pas toujours.
En revanche, lorsqu'on utilise des kmers de longueur intermédiaire (entre 8 et 12), nous obtenons des résultats plus cohérent.
En effet, on remarque que les reads du fichier reads-TRG montrent une ressemblance avec certaines séquences des fichiers
TRGV.fasta et TRGJ.fasta :
  TGRV : ce sont les séquences TRGV3*01 et TRGV2*02 qui montrent le plus de ressemblance
  TGRJ : ce sont les séquences TRGJ1*01 et TRGJP1 qui apparaissent comme le plus semblable avec les reads."

echo "\nQuestion 4"

echo "On retrouve presque les mêmes séquences que sur le logiciel pour les V,
mais pas pour les J car 2 J se ressemble beaucoup, vidjil trouve la séquence TRGJ1*02 alors que nous trouvons la séquence TRGJ1*01.
Cela vient du fait que les J sont plus court que les V et donc on a plus de chance de se tromper et aussi que vidjil fait preuve d'une
plus grande précision de comparaison entre les reads et les séquences."
