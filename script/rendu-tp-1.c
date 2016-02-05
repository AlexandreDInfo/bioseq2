#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {

  printf("Tout le code est commenté dans les fichiers sources.\n");

  printf("Question 2\n");
  printf("  java -jar bioseq.jar print-fasta-sequences test1.fasta\n");

  printf("Question 3\n");
  printf("  java -jar bioseq.jar print-fasta-stats test1.fasta\n");

  printf("Question 4\n");
  printf("  java -jar bioseq.jarlist-kmers 4 test1.fasta\n");

  printf("Question 5 -> Commentaire dans le code java\n");
  printf("  java -jar bioseq.jar common-kmers 4 test1.fasta test2.fasta\n");

  printf("Question 8\n");
  printf("  java -jar bioseq.jar ratio-common-kmers 4 test2.fasta test1.fasta\n");

  printf("Question 9 -> Commentaire dans le code java\n");
  printf("  java -jar bioseq.jar ratio-common-kmers 4 ebola-t.fasta ebola-z.fasta\n");
  printf("  java -jar bioseq.jar ratio-common-kmers 4 ebola-z.fasta phage-lamba.fasta\n");
  printf("... \n");
  printf("  java -jar bioseq.jar ratio-common-kmers 20 ebola-z.fasta phage-lamba.fasta\n");

  printf("Question 10\n");
  printf("  java -jar bioseq.jar random-mutations 2 test1.fasta\n");


  printf("Question 11 -> Commentaire dans le code java\n");
  printf("  java -jar bioseq.jar random-mutations 10 ebola-z.fasta\n");
  printf ("... \n");
  printf("  java -jar bioseq.jar random-mutations 10000 ebola-z.fasta\n");
  printf("  java -jar bioseq.jar ratio-common-kmers 8 ebola_mutant_10.fasta ebola-z.fasta\n");
  printf("...\n");
  printf("  java -jar bioseq.jar ratio-common-kmers 8 ebola_mutant_10000.fasta ebola-z.fasta\n");

  printf("Question 12\n");
  printf("  java -jar bioseq.jar list-spaced-kmers \"##-##\" test1.fasta\n");

  printf("Question 13\n");
  printf("  java -jar bioseq.jar common-spaced-kmers \"##-##\" test1.fasta test2.fasta\n");
  printf("  java -jar bioseq.jar ratio-common-spaced-kmers \"##-##\" test1.fasta test2.fasta\n");

  printf("Question 14 -> Commentaire dans le code java\n");
  printf("  java -jar bioseq.jar ratio-common-spaced-kmers \"########\" ebola_mutant_10000.fasta ebola-z.fasta\n");
  printf("  java -jar bioseq.jar ratio-common-spaced-kmers \"####-####\" ebola_mutant_10000.fasta ebola-z.fasta\n");

  return 0;
}
