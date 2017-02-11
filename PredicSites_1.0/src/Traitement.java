import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Traitement extends Thread {


	public void run() {
		lancement();
	} 

	public static void lancement(){

		// Create the corresponding CountMatrix.
		Fenetre.countMatrix = null;
		
		try {
			Fenetre.countMatrix = new CountMatrix(Fenetre.matrix);
		} catch (BadCountException e) {
			System.err.println(e);
			System.exit(1);
		}
		
		Fenetre.text2.setText(Fenetre.text2.getText()+"initial matrix"+"\n");
		Fenetre.text2.setText(Fenetre.text2.getText()+Fenetre.matrix+"\n");

		Fenetre.text2.setText(Fenetre.text2.getText()+"count matrix"+"\n");
		Fenetre.text2.setText(Fenetre.text2.getText()+Fenetre.countMatrix+"\n");

		//Calcul la matrice entropy et traite la sequence
		if(Fenetre.typeMatrice.endsWith("entropy")){
			Fenetre.entropyMatrix = new EntropyMatrix((CountMatrix) Fenetre.countMatrix);
			Fenetre.text2.setText(Fenetre.text2.getText()+"entropy matrix"+"\n");
			Fenetre.text2.setText(Fenetre.text2.getText()+Fenetre.entropyMatrix+"\n");
			PatternMatcher.printOccurrencesEntropy(Fenetre.entropyMatrix, Fenetre.sequence, Fenetre.threshold);
		}

		//Calcul la matrice logOdd et traite la sequence
		if(Fenetre.typeMatrice.equals("logOdd")){
			Fenetre.logOddMatrix = new LogOddMatrix((CountMatrix) Fenetre.countMatrix);
			Fenetre.text2.setText(Fenetre.text2.getText()+"log odd matrix"+"\n");
			Fenetre.text2.setText(Fenetre.text2.getText()+Fenetre.logOddMatrix+"\n");
			PatternMatcher.printOccurrencesLogOdd(Fenetre.logOddMatrix, Fenetre.sequence, Fenetre.threshold);
		}
	}
}
