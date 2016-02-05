package bioseq;

/**
 * @author Delassus Alexandre
 * @author Merciert Tony
 * Classe main de bioseq
 */
public class Main {
	/**
	 * Main permettant d'utiliser la bonne fonction par rapport à l'option ecrite
	 * @param args
	 */
	public static void main (String[] args){
	    String option = args[0];
	    Analyser analyser = new Analyser();
	    /* On examine l'option et on utilise la bonne fonction */
	    if(option.equals("print-fasta-sequences")){
	    	analyser.printFastaSequences(args[1]);
	    }
	    if(option.equals("print-fasta-stats")){
	    	analyser.printFastaStats(args[1]);
	    }
	    if(option.equals("list-kmers")){
	    	analyser.listKmers(Integer.parseInt(args[1]), args[2]);
	    }
	    if(option.equals("common-kmers")){
	    	analyser.commonKmers(Integer.parseInt(args[1]),args[2], args[3]);
	    }
	    if(option.equals("ratio-common-kmers")){
	    	System.out.println(analyser.ratioCommonKmers(Integer.parseInt(args[1]), args[2], args[3]));
	    }
	    if(option.equals("random-mutations")){
	    	System.out.println(analyser.random_mutations(Integer.parseInt(args[1]), args[2]));
	    }
	    if(option.equals("list-spaced-kmers")){
	    	analyser.listSpacedKmers(args[1], args[2]);
	    }
	    if(option.equals("common-spaced-kmers")){
	    	analyser.commonSpacedKmers(args[1], args[2], args[3]);
	    }
	    if(option.equals("ratio-common-spaced-kmers")){
	    	System.out.println(analyser.ratioCommonSpacedKmers(args[1], args[2], args[3]));
	    }
	    if(option.equals("windows")){
	    	System.out.println(analyser.windows(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]));
	    }
	    if(option.equals("mapper-windows-kmers")){
	    	analyser.mapperWindowsKmers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Double.parseDouble(args[4]), args[5], args[6]);
	    }
	    if(option.equals("mapper-windows-spaced-kmers")){
	    	analyser.mapperWindowsKmers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], Double.parseDouble(args[4]), args[5], args[6]);
	    }
	    if(option.equals("enumerate-spaced-kmers")){
	    	Kmers kmers = new Kmers();
	    	System.out.println(kmers.enumerateSpacedKmers(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
	    }
	}
}
