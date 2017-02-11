

public class Main {

	private static Traitement t1 ;
	
	public static void main(String[] args) {
		//Creation d'une fenetre et affichage
		Fenetre F1 = new Fenetre();
		F1.setTitle("PredicSites 1.0 - © Copyright 2015");
		F1.setVisible(true);
	}

	public static void traitement () {
		//Lance l'analyse de la séquence
		try {
			t1 = new Traitement () ;
			t1.start();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void arretTraitement () {
		//Stop l'analyse de la sequence
		try {
			t1.interrupt();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
