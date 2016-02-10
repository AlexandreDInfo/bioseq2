#!/bin/sh

echo "Question 1"

java -jar bioseq.jar windows 80 40 phage-lambda.fasta

echo "Question 2\n"

echo "Partie inférieure ( (n-l)/s ) + 1"

echo "\nQuestion 3\n"

java -jar bioseq.jar mapper-windows-kmers 80 40 8 0.5 read1.fasta phage-lambda.fasta

echo "\nQuestion 4\n"

java -jar bioseq.jar mapper-windows-kmers 80 40 4 0.1 read1.fasta phage-lambda.fasta

echo "..."

java -jar bioseq.jar mapper-windows-kmers 8 40 20 0.1 read1.fasta phage-lambda.fasta

java -jar bioseq.jar mapper-windows-kmers 80 40 4 0.1 read2.fasta phage-lambda.fasta

java -jar bioseq.jar mapper-windows-kmers 80 40 20 0.1 read2.fasta phage-lambda.fasta


echo "En utilisant des kmers de longueur 4, on observe que l'on obtient toutes les fenêtres du fichier phage-lambda
aussi bien en utilisant read1, que read2. Celà s'explique par le fait qu'avec des kmers de 4 nucléotides, on ne peut
obtenir que 256 kmers différents.

En passant à des kmers de longueur 8, on obtient un nombre de fenêtres beaucoup moins conséquent qu'avec le cas précèdent
En fixant le ratio à 0.1, plus de 50 fenêtres sont affichées pour read1 et plus de 30 pour read2.
Cependant lorsque l'on augmente le ratio (à 0.5 par exemple), le nombre de fenêtres qui s'affiche est plus ou moins
équivalent pour read1 et read2 (un peu plus d'un dizaine pour un seuil à 0.5) avec des positions dans la séquence
qui sont bien différenciés :
		Aux alentours de la position 27000 pour read1 , et de la position 34000 pour read2

En utilisant des kmers de longueur 12, on observe que le nombre de fenêtres diminue mais celà nous donne un
meilleur encadrement de la portion de séquence qui nous intéresse :
	Read1 : les fenêtres vont de la position 27800 à la position 27950
	Read2 : Avec un seuil de 0.1, les fenêtres vont de la position 34800 à 34900. Cependant lorsque l'on dépasse ce seuil
			il n'y a plus aucune fenêtre qui apparaît.

Pour des kmers de longueur 16 et 20, nous obtenons aucune fenêtre pour read2 et seulement quelques une avec read1 mais
lorsque le seuil ne dépasse pas 0.1

On peut en conclure que la longueur optimale de kmers est la longueur 12. En effet, elle permet de montrer un bon
encadrement de la séquence, tout en gardant un seuil correct. Grâce à cette étude, on a pu voir de grosses similitudes
entre read1 et la portion de séquence de phage-lambda (position 27800 à 27950) ainsi qu'un nombre plus faible de similitudes
avec read2 et phage-lambda."

echo "\nQuestion 5\n"

java -jar bioseq.jar list-spaced-kmers "##-##" test1.fasta

echo "\nQuestion 6\n"

java -jar bioseq.jar common-spaced-kmers "##-##" ebola-z.fasta ebola-t.fasta

java -jar bioseq.jar ratio-spaced-kmers "##-##" ebola-z.fasta ebola-t.fasta

java -jar bioseq.jar mapper-windows-spaced-kmers 80 40 "####-####" 0.5 read1.fasta phage-lambda.fasta

echo "\nQuestion 7\n"
echo "En utilisant des graines comme par exemple #-#--###-### qui sont de poids 8, on observe que le ratio reste plus ou
 moins identique qu'en utilisant la graine non espacée de poids 8.
Lorsque l'on augmente la taille des kmers, la ratio obtenu reste aussi très proche que celui obtenu avec des graines non
espacées de même poids.
On peut en conclure que les graines espacés permettent d'ignorer un nucléotide qui a été muté dans une portion de séquence
et donc de garder un ratio proche de celui obtenu avec des kmers non espacés.
L utilisation des graines à aussi un intérêt pour les séquences qui ont subi des délétions ainsi que des ajouts de nucléotides. En effet, le fait
d oublier un nucléotide dans le motif de la graine permet de ne pas prendre en compte une potentielle addition ou suppression."

java -jar bioseq.jar ratio-spaced-kmers "########" ebola-z.fasta ebola-t.fasta

java -jar bioseq.jar ratio-spaced-kmers "####-####" ebola-z.fasta ebola-t.fasta

echo "\nQuestion 8\n"

echo "En utilisant des graines contiguës, on obtient les mêmes fenêtres que ceux de la question 4, ce qui est logique.
Lorsqu'on utilise des graines espacées de poids 12 qui permet les résultats les plus précis (Cf question 4) comme
par exemple ####-####-####, on observe que la position des fenêtres se précisent encore plus qu'avec les
graines contigues. En effet on peut augmenter le seuil plus facilement tout en gardant un bon résultat.
Cela s'explique par le fait qu'on peut ignorer certains nucléotides qui diffère entre le read et la fenêtre."
