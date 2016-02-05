package bioseq;

import java.io.*;
import java.util.*;

/**
 * @author Delassus Alexandre
 * @author Merciert Tony
 * Sequence est la sequence ADN des fichiers fasta mise au format adapté.
 */
public class Sequence {
	
	private String sequence;
	private String name;
	/**
	 * Constructeur de Sequence
	 * @param fichier
	 */
	public Sequence(String fichier){
		this.generateSequence(fichier);
		this.generateName(fichier);
	}
	/**
	 * Transforme le fichier en sequence d'ADN au bon format
	 * @param fichier
	 */
	public void generateSequence(String fichier){
		
		Scanner scanner;
		boolean init = false;
		this.sequence = "";
		/* On met dans un try pour catcher l'exception de mauvais fichier */
		try {
			/* On utilise un scan pour compte ligne par ligne */
			scanner = new Scanner(new File(fichier));
			/* Tant qu'il reste des lignes dans le scanner,
			 * On regarde s'il appartient à une sequence d'ADN ou si c'est le nom,
			 * et on ne garde que les sequences ADN */
			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    if(line.charAt(0) == '>'){
			    	if(init)
			    		this.sequence += '\n';
			    }
			    else{
			    	this.sequence += line;
			    }
			    init = true;
			}
			 
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé.");
		}
	}
	/**
	 * Transforme le fichier en liste de noms de sequence
	 * @param fichier
	 */
	public void generateName(String fichier){
		Scanner scanner;
		this.name = "";
		/* On met dans un try pour catcher l'exception de mauvais fichier */
		try {
			/* On utilise un scan pour compte ligne par ligne */
			scanner = new Scanner(new File(fichier));
			/* Tant qu'il reste des lignes dans le scanner,
			 * On regarde s'il appartient à une sequence d'ADN ou si c'est le nom,
			 * on ne garde que les noms */
			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    if(line.charAt(0) == '>'){
			    	name += line.substring(1);
			    	name += '\n';
			    }
			}
			scanner.close();
		} catch (FileNotFoundException e) {}
	}
	/**
	 * Getter de sequence
	 * @return
	 */
	public String getSequence(){
		return this.sequence;
	}
	/**
	 * Getter de name
	 * @return
	 */
	public String getName(){
		return this.name;
	}
}
