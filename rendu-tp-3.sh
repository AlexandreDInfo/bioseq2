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

echo "Taille 4 -> pas significatif
Taille tros grosse, ça ne fonctionne pas toujours"

echo "\nQuestion 4"

echo "On retrouve presque les mêmes Sequences que sur le logiciel pour les V,
mais pas pour les J car 2 J se ressemble beaucoup, vidjil retombe sur TRGJ1*02 alors que nous retombons sur TRGJ1*01.
Cela viens du faite que les J sont plus court que les V et donc on a plus de chance de se tromper."
