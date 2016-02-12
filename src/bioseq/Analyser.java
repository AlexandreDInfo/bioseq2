package bioseq;

import java.util.*;

/**
 * @author Delassus Alexandre
 * @author Merciert Tony
 * Analyser regroupe toutes les fonctions principales.
 */
public class Analyser {
	
	public Analyser() {}
	
	/**
	 * Imprime les sequences contenues dans un fichier fasta en une seule ligne par séquence.
	 * @param fichier
	 */
	public void printFastaSequences(String fichier){
		String sequence = new Sequence(fichier).getSequence();
		/* On imprime toutes les sequences */
		System.out.println(sequence);
	}
	
	/*
	 * Réponse à la question 5:
	 * Les longueurs sont exactes :
	 * phage-lambda 48 502 nucléotides
	 * ebola-z 18 958 nucléotides
	 * ebola-t 18 935 nucléotides
	 */
	
	/**
	 * Imprime le nom des sequences et leurs tailles.
	 * @param fichier
	 */
	public void printFastaStats(String fichier){
		String sequence = new Sequence(fichier).getSequence();
		String name = new Sequence(fichier).getName();
		/* Les scans permettent de lire ligne par ligne donc sequence par sequence */
		Scanner scanSeq = new Scanner(sequence);
		Scanner scanName = new Scanner(name);
		/* Tant qu'il y a des sequences, on imprime le nom et a taille */
		while(scanName.hasNextLine()){
			System.out.println(scanName.nextLine() + " " + scanSeq.nextLine().length());
		}
		scanSeq.close();
		scanName.close();
	}
	
	/**
	 * Imprime la liste de tous les K-mers de taille longueur
	 * @param longueur
	 * 			la longueur d'un k-mers
	 * @param fichier
	 */
	public void listKmers(int longueur, String fichier){
		ArrayList<String> list = new Kmers(longueur, fichier).getList();
		/* On imprime tous les K-mers */
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * Imprime la liste de tous les K-mers qui suivent le pattern de la graine
	 * @param graine
	 * @param fichier
	 */
	public void listSpacedKmers(String graine, String fichier){
		ArrayList<String> list = new Kmers(graine, fichier).getList();
		/* On imprime tous les K-mers */
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * Imprime la liste de tous les K-mers commum aux 2 sequences choisies.
	 * @param longueur
	 * 			la longueur d'un k-mers
	 * @param fichier1
	 * @param fichier2
	 */
	public void commonKmers(int longueur, String fichier1, String fichier2){
		Kmers kmers1 = new Kmers(longueur, fichier1);
		Kmers kmers2 = new Kmers(longueur, fichier2);
		this.commonKmersAux(kmers1, kmers2);
	}
	
	public void commonSpacedKmers(String graine, String fichier1, String fichier2){
		Kmers kmers1 = new Kmers(graine, fichier1);
		Kmers kmers2 = new Kmers(graine, fichier2);
		this.commonKmersAux(kmers1, kmers2);
	}
	
	/**
	 * Imprime la liste de tous les K-mers commum aux 2 Kmers choisis.
	 * @param kmers1
	 * @param kmers2
	 */
	public void commonKmersAux(Kmers kmers1, Kmers kmers2){
		
		/* On enleve les doublons pour ne pas faire plusieurs fois la meme chose */
		kmers1.deleteDoublon();
		kmers2.deleteDoublon();
		ArrayList<String> list1 = kmers1.getList();
		ArrayList<String> list2 = kmers2.getList();
		/* Pour chaque k-mers de la première liste, on imprime ce k-mers si il est dans la deuxième */
		for(int i = 0; i < list1.size(); i++){
			for(int j = 0; j < list2.size(); j++){
				if(list1.get(i).equals(list2.get(j))){
					System.out.println(list1.get(i));
				}
			}
		}
	}
	
	public double ratioCommonKmers(int longueur, String fichier1, String fichier2){
		Kmers kmers1 = new Kmers(longueur, fichier1);
		Kmers kmers2 = new Kmers(longueur, fichier2);
		return ratioCommonKmersAux(kmers1, kmers2);
	}
	
	public double ratioCommonSpacedKmers(String graine, String fichier1, String fichier2){
		Kmers kmers1 = new Kmers(graine, fichier1);
		Kmers kmers2 = new Kmers(graine, fichier2);
		return ratioCommonKmersAux(kmers1, kmers2);
	}
	
	/**
	 * @param longueur 
	 * 			la longueur d'un kmer
	 * @param fichier1
	 * @param fichier2
	 * @return la proportion de kmers du premier fichier qui sont présent dans le deuxième
	 */
	public double ratioCommonKmersAux(Kmers kmers1, Kmers kmers2) {
		double nombreTotalKmers1 = kmers1.getList().size();
		// On initialise le compteur pour connaitre le nombre de kmers du fichier 1 qui est présent dans le fichier 2
		double nombreDeKmers1Dans2 = 0;
		// On supprime les doublons du deuxième fichier pour éviter de comparer deux fois
		kmers2.deleteDoublon();
		
		ArrayList<String> list1 = kmers1.getList();
		ArrayList<String> list2 = kmers2.getList();		
		
		// On effectue les comparaisons et on incrémente le compteur
		for(int i = 0; i < list1.size(); i++){
			for(int j = 0; j < list2.size(); j++){
				if(list1.get(i).equals(list2.get(j))){
					nombreDeKmers1Dans2++;
				}
			}
		}
		
		// calcul de la proportion : nombre de présent du fichier1 / nombre total de kmer du fichier1
		return nombreDeKmers1Dans2 / nombreTotalKmers1;
	}
	
	/*
	 * Réponse à la question 9 : 
	 *  Lorsque que l'on compare les 4-mers d'ebola-z et d'ebola-t on obtient un ratio de 1. Cela montre que
	 *  la séquence de ces deux virus est très proche, ainsi ils font parti de la même catégorie de virus. 
	 *  Mais lorsque que l'on augmente la taille des kmers (longueur 8 par exemple), ce ratio diminue fortement. 
	 *  Ceci montre que les différences entre les deux virus se trouvent en milieu de séquence et non aux extrémités.
	 *  
	 *  On trouve un ratio de 0,49 lorsque l'on compare ebola-z et phage lambda (kmer longueur 8). Cela montre que ces deux virus 
	 *  appartiennent à des catégories différentes mais présente certaines séquences en commun.
	 */
	
	/**
	 * @param nucleotide
	 * @return un autre nucléotide différent de celui passé en paramètre
	 */
	public String nucleotideAleatoire (String nucleotide) {
		Random rand = new Random();
		int n = rand.nextInt(3);
		String nucleotideMutee;
		switch (n) 
		{
			case 0 : 
				nucleotideMutee = "A";
				break;
			case 1 :
				nucleotideMutee = "T";
				break;
			case 2 : 
				nucleotideMutee = "C";
				break;
			default :
				nucleotideMutee = "G";
		}
		if (nucleotideMutee.equals(nucleotide)) {
			return nucleotideAleatoire(nucleotide);
		}
		return nucleotideMutee;
	}
	
	/**
	 * 
	 * @param mutation
	 * 		nombre de mutation que l'on souhaite
	 * @param fichier
	 * 		la séquence que l'on souhaite muter
	 * @return une séquence modifiée avec n mutations
	 */
	public StringBuffer random_mutations (int mutation, String fichier) {
		Random rand = new Random();
		String sequence = new Sequence(fichier).getSequence();
		StringBuffer sequenceMutee = new StringBuffer(sequence);
		for (int i = 0 ; i < mutation ; i++) {
			int endroitRandomSequence = rand.nextInt(sequence.length());
			String nucleotideMute = nucleotideAleatoire(Character.toString(sequenceMutee.charAt(endroitRandomSequence)));
			sequenceMutee.replace(endroitRandomSequence, endroitRandomSequence+1,nucleotideMute);
		}
		return sequenceMutee;
	}
	
	/*
	 * Question 11 :
	 * Pour une longueur de kmers de 8 nucléotides, on obtient en ratio pour les séquences mutées :
	 * 	ebola_mutant_10 : 0,997
	 *  ebola_mutant_100 : 0,97
	 *  ebola_mutant_1000 : 0,77
	 *  ebola_mutant_10000 : 0,35 
	 *  Lorsque l'on augmente la longueur des kmers, la ratio diminu encore plus rapidement.
	 *  
	 *  Celà montre que le fait de modifier des centaines de nucléotides sur une séquence qui en contient beaucoup
	 *  plus, peut complétement fausser le ratio entre deux mêmes séquences. Celà montre que s'il y a des erreurs 
	 *  d'analyse pour une même séquence, la méthode des kmers n'est pas la plus précise et peut nous induire 
	 *  en erreur pour comparer plusieurs séquences. 
	 */
	
	
	/**
	 * Imprime les différentes fenêtres de la sequence.
	 * @param longueur
	 * @param shift
	 * @param fichier
	 */
	public String windows(int longueur, int shift, String fichier){
		Sequence sequence = new Sequence(fichier);
		Scanner scan = new Scanner(sequence.getSequence());
		String res = "";
		while(scan.hasNextLine()){
			String ligne = scan.nextLine();
			for(int i = 0; i <= (ligne.length() - longueur); i += shift){
				res += (i+1) + " " +ligne.substring(i, i + longueur) + " " + (i+longueur)+ "\n";
			}
		}
		scan.close();
		return res;
	}
	
	/**
	 * Enleve les chiffres d'une ligne pour ne laisser que les ACGT
	 * @param ligne
	 * @return
	 */
	public String transformeLigne(String ligne){
		String res = "";
		for(int i = 0; i < ligne.length(); i++){
			if(ligne.charAt(i) == 'A' || ligne.charAt(i) == 'T' || ligne.charAt(i) == 'C' || ligne.charAt(i) == 'G'){
				res += ligne.charAt(i);
			}
		}
		return res;
	}
	
	/**
	 * Calcul le ratio entre le fichier read et le fichier Genome et affiche ceux superieurs au seuil
	 * @param longueurWin
	 * @param shiftWin
	 * @param longueurKmer
	 * @param seuilKmer
	 * @param read
	 * @param genome
	 */
	public void mapperWindowsKmers(int longueurWin, int shiftWin, String graine, double seuilKmer, String read, String genome){
		Kmers kmers1 = new Kmers(graine,read);
		String genomeWindows = windows(longueurWin, shiftWin, genome);
		Scanner scan = new Scanner(genomeWindows);
		while(scan.hasNextLine()) {
			String ligne = scan.nextLine();
			String window = this.transformeLigne(ligne);
			Kmers kmerWindow = new Kmers();
			kmerWindow.generateWindowKmers(graine, window);
			if (ratioCommonKmersAux(kmers1, kmerWindow) >= seuilKmer){
				System.out.println(ligne);
			}
		}
		scan.close();
	}
	
	/**
	 * Calcul le ratio entre le fichier read et le fichier Genome et affiche ceux superieurs au seuil
	 * @param longueurWin
	 * @param shiftWin
	 * @param longueurKmers
	 * @param seuilKmer
	 * @param read
	 * @param genome
	 */
	public void mapperWindowsKmers(int longueurWin, int shiftWin, int longueurKmers, double seuilKmer, String read, String genome){
		String graine = "";
		for(int i = 0 ; i < longueurKmers - 1; i++){
			graine += '#';
		}
		mapperWindowsKmers(longueurWin, shiftWin, graine, seuilKmer, read, genome);
	}
	
	/**
	 * Compte le nombre de kmers en commun entre la liste des kmers de read et celle de germ
	 * @param read
	 * @param germ
	 * @return
	 */
	public int nombreKmersCommuns(Kmers read, Kmers germ) {
		int cpt = 0;
		read.deleteDoublon();
		for (int i = 0; i < read.getList().size(); i++){
			if(germ.getList().contains(read.getList().get(i))){
				cpt++;
			}
		}
		return cpt;
	}
	
	/**
	 * Ecrit les n séquences de germline les plus semblables en les comparant avec le fichier read
	 * @param n
	 * @param graine
	 * @param read
	 * @param germline
	 */
	public void bestMatches (int n, String graine, String read, String germline) {
		Kmers kmersRead = new Kmers(graine,read);
		Sequence seqGermline = new Sequence(germline);
		Scanner scanSequence = new Scanner(seqGermline.getSequence());
		Scanner scanNom = new Scanner(seqGermline.getName());
		Map<String,Integer> tableDesComparaisons = new HashMap<String,Integer>(); 
		int kmersCommun;
		while(scanSequence.hasNext()) {
			Kmers kmersGermline = new Kmers();
			kmersGermline.generateWindowKmers(graine, scanSequence.nextLine());
			kmersCommun = nombreKmersCommuns(kmersRead, kmersGermline);
			tableDesComparaisons.put(scanNom.nextLine(),kmersCommun); 
		}
		scanSequence.close();
		scanNom.close();
		
		// On imprime les meilleurs résultats
		for(int i = 0; i < n ; i++) {
			int max = 0;
			String nom = "";
			for(Map.Entry<String, Integer> entry : tableDesComparaisons.entrySet()){
				if (max < entry.getValue()){
					max = entry.getValue();
					nom = entry.getKey();
				}	
			}
			tableDesComparaisons.remove(nom);
			System.out.printf(">%s   %d \n", nom, max);
		}
	}
	
	/**
	 * Affiche les meilleure séquence V et J pour chaque séquence read
	 * @param graine
	 * @param reads
	 * @param germlineV
	 * @param germlineJ
	 */
	public void vjDiscover(String graine, String reads, String germlineV, String germlineJ){
		Sequence seqReads = new Sequence(reads);
		Sequence seqGermlineV = new Sequence(germlineV);
		Sequence seqGermlineJ = new Sequence(germlineJ);
		Scanner scanSeqReads = new Scanner(seqReads.getSequence());
		Scanner scanNomReads = new Scanner(seqReads.getName());
		Map<String,Integer> tableDesComparaisons = new HashMap<String,Integer>();
		int kmersCommun = 0;
		int max = 0;
		String nomGermlineV = "";
		String nomGermlineJ = "";

		// On parcourt chaque séquence du fichier reads
		while(scanSeqReads.hasNextLine()){
			Kmers kmersRead = new Kmers();
			kmersRead.generateWindowKmers(graine, scanSeqReads.nextLine());
			Scanner scanSeqGermlineJ = new Scanner(seqGermlineJ.getSequence());
			Scanner scanNomGermlineJ = new Scanner(seqGermlineJ.getName());
			Scanner scanSeqGermlineV = new Scanner(seqGermlineV.getSequence());
			Scanner scanNomGermlineV = new Scanner(seqGermlineV.getName());
			
			// Comparaisons pour le germlineV
			while(scanSeqGermlineV.hasNextLine()){
				Kmers kmersGermlineV = new Kmers();
				kmersGermlineV.generateWindowKmers(graine, scanSeqGermlineV.nextLine());
				kmersCommun = nombreKmersCommuns(kmersRead, kmersGermlineV);
				tableDesComparaisons.put(scanNomGermlineV.nextLine(), kmersCommun);
			}
			for(Map.Entry<String, Integer> entry : tableDesComparaisons.entrySet()){
				if (max < entry.getValue()){
					max = entry.getValue();
					nomGermlineV = entry.getKey();
				}	
			}
			
			// On réinitialise les données pour effectuer la comparaison avec l'autre fichier
			tableDesComparaisons.clear();
			max = 0;
			
			// Comparaisons pour le germlineJ
			while(scanSeqGermlineJ.hasNextLine()){
				Kmers kmersGermlineJ = new Kmers();
				kmersGermlineJ.generateWindowKmers(graine, scanSeqGermlineJ.nextLine());
				kmersCommun = nombreKmersCommuns(kmersRead, kmersGermlineJ);
				tableDesComparaisons.put(scanNomGermlineJ.nextLine(), kmersCommun);
			}
			for(Map.Entry<String, Integer> entry : tableDesComparaisons.entrySet()){
				if (max < entry.getValue()){
					max = entry.getValue();
					nomGermlineJ = entry.getKey();
				}	
			}
			tableDesComparaisons.clear();
			max = 0;
			
			// Affichage du résultat
			System.out.printf(">%s   %12s   %12s \n", scanNomReads.nextLine(), nomGermlineV, nomGermlineJ);
			scanSeqGermlineV.close();
			scanNomGermlineV.close();
			scanSeqGermlineJ.close();
			scanNomGermlineJ.close();
		}
		
		scanSeqReads.close();
		scanNomReads.close();
	}
}
