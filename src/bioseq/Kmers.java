package bioseq;

import java.util.*;

/**
 * @author Delassus Alexandre
 * @author Merciert Tony
 * Représente le K-mers sous forme de Liste pour permettre un meilleur traitement des données.
 */
public class Kmers {

	private ArrayList<String> list;
	/**
	 * Constructeur de K-mers 
	 * @param longueur
	 * 			la longueur d'un k-mers
	 * @param fichier
	 */
	public Kmers(int longueur, String fichier){
		this.list = new ArrayList<String>();
		this.generateKmers(longueur, fichier);
	}
	/**
	 * Constructeur de K-mers espacés
	 * @param graine
	 * @param fichier
	 */
	public Kmers(String graine, String fichier){
		this.list = new ArrayList<String>();
		this.generateSpacedKmers(graine, fichier);
	}
	
	public Kmers(){
		this.list = new ArrayList<String>();
	}
	
	/**
	 * Getter de list
	 * @return list
	 */
	public ArrayList<String> getList(){
		return this.list;
	}
	/**
	 * Ajoute le k-mers à la liste des k-mers
	 * @param kmers
	 */
	public void addKmers(String kmers){
		this.list.add(kmers);
	}
	/**
	 * Transforme un fichier fasta en liste de K-mers
	 * @param longueur
	 * 			la longueur d'un K-mers
	 * @param fichier
	 */
	public void generateKmers(int longueur, String fichier){
		String sequence = new Sequence(fichier).getSequence();
		Scanner scan = new Scanner(sequence);
		/* Tant qu'il reste une ligne de sequence,
		 * On ajoute tous les K-mers de cette sequence */
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			for(int i = 0; i <= (line.length() - longueur); i++){
				this.addKmers(line.substring(i, i + longueur));
			}
		}
		scan.close();
	}
	/**
	 * Enleve les doublons de la liste de k-mers
	 */
	public void deleteDoublon(){
		/* On passe la liste en set pour enlever les doublons puis on la repasse en liste */
        Set<String> set = new HashSet<String>();
        set.addAll(this.list);
        this.list = new ArrayList<String>(set) ;
	}
	/**
	 * Génère la liste de kmers suivant le pattern de la graine.
	 * @param graine
	 * @param fichier
	 */
	public void generateSpacedKmers(String graine, String fichier){
		String sequence = new Sequence(fichier).getSequence();
		Scanner scan = new Scanner(sequence);
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			for(int i = 0; i <= (line.length() - graine.length()); i++){
				String kmers = "";
				for(int j = 0; j < graine.length(); j++){
					if(graine.charAt(j) == '#'){
						kmers = kmers + line.charAt(i+j);
					}
				}
				this.addKmers(kmers);
			}
		}
		scan.close();
	}
	
	public void generateWindowKmers(int longueur, String ligne){
		for(int i = 0; i <= ligne.length() - longueur; i++){
			this.addKmers(ligne.substring(i,i+longueur));
		}
	}
	
	public void generateWindowKmers(String graine, String ligne){
		for(int i = 0; i <= ligne.length() - graine.length(); i++){
			String kmers = "";
			for(int j = 0; j < graine.length(); j++){
				if(graine.charAt(j) == '#'){
					kmers = kmers + ligne.charAt(i+j);
				}
			}
			this.addKmers(kmers);
		}
	}
	
	public ArrayList<String> enumerateSpacedKmers(int poids, int longueur){
		ArrayList<String> list = new ArrayList<String>();
		String graine = "";
		for(int i = 0; i < poids ; i++){
			graine += '#';
		}
		list.add(graine);
		for(int i = poids + 1; i <= longueur; i++){
			list = this.differenteGraine(list,i);
		}
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		return list;
	}
	
	public ArrayList<String> differenteGraine(ArrayList<String> liste, int i){
		boolean running;
		int cpt;
		String graine;
		ArrayList<String> temp = liste;
		for(int j = 0; j < liste.size(); j++){
			String onRegarde = liste.get(j);
			if(onRegarde.length() == i - 1){
				running = true;
				cpt = onRegarde.length() - 1;
				while(running){
					if(cpt > 0 && onRegarde.charAt(cpt) == '#'){
						graine = onRegarde.substring(0,cpt) + "-" + onRegarde.substring(cpt);
						cpt--;
						temp.add(graine);
					}
					else{
						running = false;
					}
				}
			}
		}
		return temp;
	}
}
