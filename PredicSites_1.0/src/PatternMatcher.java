import java.io.*;
import java.lang.*;
import java.lang.String.*; 
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class PatternMatcher
 */
class PatternMatcher {

	/**
	 * Print all occurrences of the matrix in the sequence.
	 * @param matrix A search matrix
	 * @param sequence A DNA sequence
	 * @param threshold The pattern threshold 
	 */
	public static void printOccurrencesEntropy(EntropyMatrix matrix, DNASequence sequence, double threshold) {
		// Print the context
		//System.out.println("PatterMatcher (threshold = " + threshold + ")");
		ArrayList<String> resultat = new ArrayList<String> ();
		String s = "PatterMatcher (threshold = " + threshold + ")";
		resultat.add(s);
		// Some useful values
		int rawSequenceLength = sequence.length();
		int patternLength = matrix.getNumberOfColumns();
		int endIndex = rawSequenceLength - (patternLength+(rawSequenceLength%patternLength)) ;
		float score ;
		Fenetre.Traitement();
		// For each possible starting position in the sequence.
		for (int i = 0; i <= endIndex; i++) {
			score = 0;
			int valeurs[] = new int[patternLength];
			String frag = sequence.substring(i, i+patternLength);
			valeurs = motifValue(frag) ;
			for (int lecteur=0;lecteur<frag.length();lecteur++){
				score += matrix.getElement(valeurs[lecteur], lecteur);
			}
			// Print the pattern if the score is above the given threshold.
			if (score >= threshold) {
				// Construct the pattern
				//System.out.println("Position: "+i+"\t"+frag+"\tscore: "+score);
				s = "Position: "+i+"\t"+frag+"\tscore: "+score;
				Fenetre.text2.setText(Fenetre.text2.getText()+"\n"+s);
			}
			Fenetre.Traitement();
		}
	}

	public static void printOccurrencesLogOdd(LogOddMatrix matrix, DNASequence sequence, double threshold) {

		//Normalisation de la matrice
		float[] tabMin = new float[matrix.numberOfColumns] ;
		float[] tabMax = new float[matrix.numberOfColumns] ;
		float valMin = 0;
		float valMax = 0;
		for(int iCol=0; iCol<matrix.numberOfColumns; iCol++){
			for(int iLigne=0; iLigne<matrix.numberOfRows; iLigne++){
				float val = (float) matrix.element[iLigne][iCol] ;
				if (iLigne>0){
					if (val > tabMax[iCol]) {
						tabMax[iCol]=val ;
					}
					if (val<tabMin[iCol]) {
						tabMin[iCol]=val ;
					}
				}
				else {
					tabMax[iCol]=val ;
					tabMin[iCol]=val ;
				}
			}
		}
		
		for(int i=0; i<matrix.numberOfColumns; i++){
			valMax += tabMax[i] ;
			valMin += tabMin[i] ;
		}
		
		System.out.println("Max"+valMax);
		System.out.println("Min"+valMin);
		
		ArrayList<String> resultat = new ArrayList<String> ();
		String s = "PatterMatcher (threshold = " + threshold + ")";
		Fenetre.text2.setText(Fenetre.text2.getText()+"\n"+s);
		resultat.add(s);
		// Some useful values
		int rawSequenceLength = sequence.length();
		int patternLength = matrix.getNumberOfColumns();
		int endIndex = rawSequenceLength - (patternLength+(rawSequenceLength%patternLength)) ;
		float score ;
		// For each possible starting position in the sequence.
		for (int i = 0; i <= endIndex; i++) {
			Fenetre.Traitement();
			score = 0;
			int valeurs[] = new int[patternLength];
			String frag = sequence.substring(i, i+patternLength);
			valeurs = motifValue(frag) ;
			for (int lecteur=0;lecteur<frag.length();lecteur++){
				score += matrix.getElement(valeurs[lecteur], lecteur);
			}
			score = (float) ((score - valMin) / (valMax - valMin)) ;
			// Print the pattern if the score is above the given threshold.
			if (score >= threshold) {
				// Construct the pattern
				//System.out.println("Position: "+i+"\t"+frag+"\tscore: "+score);
				s = "Position: "+i+"\t"+frag+"\tscore: "+score;
				Fenetre.text2.setText(Fenetre.text2.getText()+"\n"+s);
			}
		}
	}

	public static int[] motifValue(String motif){
		int valeurs[] = new int[motif.length()] ;
		for(int i=0;i<motif.length();i++){
			char c = motif.charAt(i);
			if (c == 'A') {valeurs[i]= 0;}
			else if (c == 'C') {valeurs[i]= 1;}
			else if (c == 'G') {valeurs[i]= 2;}
			else if (c == 'T') {valeurs[i]= 3;}
		}
		return valeurs ;
	}
}
