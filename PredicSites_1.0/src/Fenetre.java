import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.DefaultCaret;



public class Fenetre extends JFrame implements ActionListener{

	//Attributs des donnees
	public static MutablePositiveIntegerMatrix matrix ; 
	public static DNASequence sequence;
	public static String typeMatrice = new String("entropy");
	private int nbCol = 5 ;
	private int nbLigne = 4 ;
	public static float threshold = 0.85f ;
	public static CountMatrix countMatrix;
	public static EntropyMatrix entropyMatrix;
	public static ArrayList<String> resultatMatch = new ArrayList<String> () ;
	public static LogOddMatrix logOddMatrix;

	//Attributs des objets de la fenetre
	private JButton Lancer = new JButton("Lancer avec entropy - seuil : "+threshold);
	private JButton Stop = new JButton("Stop");
	private JButton Clear = new JButton("Clear");
	private JTextArea text1 = new JTextArea();
	public static JTextArea text2 = new JTextArea();
	private JLabel label1 = new JLabel("  Sequence :  ");
	private JLabel label2 = new JLabel("  Matrice initiale :  ");
	private JTable table1 ;
	public static JProgressBar barre= new JProgressBar(0,100);
	private JOptionPane popu1 = new JOptionPane();

	//Attributs des icones
	private ImageIcon image1 = new ImageIcon("src/images/noun_628 small.png");
	private ImageIcon image2 = new ImageIcon("src/images/noun_4305 small.png");
	private ImageIcon image3 = new ImageIcon("src/images/noun_5136 small.png");
	private ImageIcon image4 = new ImageIcon("src/images/noun_19281 small.png");

	//Attributs de la barre de menu
	private JMenuItem Ouvrir = new JMenuItem("Ouvrir une sequence");
	private JMenuItem Enregistrer = new JMenuItem("Enregistrer");
	private JMenuItem boutonMatrice = new JMenuItem("Matrice exemple");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem seuil = new JMenuItem("Seuil");
	private JMenuItem type = new JMenuItem("Type de matrice");
	private JMenuItem longmatr = new JMenuItem("Nombre de colonne Matrice");
	private JMenuItem aide = new JMenuItem("?");
	private JMenuBar barreMenu = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenu menu2 = new JMenu("Options");
	private JMenu menu3 = new JMenu("A propos");

	//Attributs de division de la fenetre
	private JPanel pane = new JPanel(new BorderLayout());
	private JPanel pane1 = new JPanel(new BorderLayout());
	private JPanel pane11 = new JPanel(new BorderLayout());
	private JPanel pane12 = new JPanel(new BorderLayout());
	private JPanel pane2 = new JPanel(new BorderLayout());
	private JPanel pane3 = new JPanel(new FlowLayout());
	private JPanel pane31 = new JPanel(new BorderLayout());
	private JPanel pane32 = new JPanel(new BorderLayout());


	public Fenetre(){
		//Parametres de la fenetre
		setSize(1024, 768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		table1 = new JTable(nbLigne, nbCol);
		Dimension m = new Dimension(300,65);

		//Ajout des elements de la fenetre
		table1.setPreferredSize(m);
		text1.setEditable(false);
		JScrollPane scroll1 = new JScrollPane(text1);
		label1.setForeground(new java.awt.Color(49,140,231));
		pane11.add(label1, BorderLayout.NORTH);
		pane11.add(scroll1, BorderLayout.CENTER);
		label2.setForeground(new java.awt.Color(49,140,231));
		pane12.add(label2, BorderLayout.NORTH);
		pane12.add(table1, BorderLayout.EAST);
		pane1.add(pane11, BorderLayout.CENTER);	
		pane1.add(pane12, BorderLayout.EAST);	

		text2.setEditable(false);
		text2.setBackground(new java.awt.Color(223,242,255));
		JScrollPane scroll2 = new JScrollPane(text2);
		pane2.add(scroll2, BorderLayout.CENTER);

		barre.setStringPainted(true);
		barre.setForeground(new java.awt.Color(49,140,231));
		barre.setBackground(new java.awt.Color(223,242,255));

		pane31.add(Lancer, BorderLayout.WEST);
		pane31.add(Stop, BorderLayout.EAST);
		barre.setValue(0);
		pane32.add(barre, BorderLayout.WEST);
		pane32.add(Clear, BorderLayout.EAST);
		pane3.add(pane31, BorderLayout.NORTH);
		pane3.add(pane32, BorderLayout.SOUTH);

		//Ajout des elements du menu
		menu1.setForeground(new java.awt.Color(49,140,231));
		menu2.setForeground(new java.awt.Color(49,140,231));
		menu3.setForeground(new java.awt.Color(49,140,231));

		menu1.add(Ouvrir);
		Ouvrir.setIcon(image1);
		menu1.add(Enregistrer);
		Enregistrer.setIcon(image3);
		menu1.add(boutonMatrice);
		boutonMatrice.setIcon(image2);
		menu1.addSeparator();
		menu1.add(fermer);

		menu2.add(seuil);
		menu2.add(type);
		menu2.add(longmatr);

		menu3.add(aide);

		barreMenu.add(menu1);
		barreMenu.add(menu2);
		barreMenu.add(menu3);

		//Ajout des panneaux dans le cadre principal

		pane.add(pane1, BorderLayout.NORTH);
		pane.add(pane2, BorderLayout.CENTER);
		pane.add(pane3, BorderLayout.SOUTH);
		setJMenuBar(barreMenu);
		add(pane);

		///////////////////////////////////////////////////////////////////////////////////	
		//Ajout des ActionListener
		///////////////////////////////////////////////////////////////////////////////////	

		Ouvrir.addActionListener(this);
		Ouvrir.setForeground(new java.awt.Color(49,140,231));
		Enregistrer.addActionListener(this);
		Enregistrer.setForeground(new java.awt.Color(49,140,231));
		boutonMatrice.addActionListener(this);
		boutonMatrice.setForeground(new java.awt.Color(49,140,231));
		aide.addActionListener(this);
		aide.setForeground(new java.awt.Color(49,140,231));
		fermer.addActionListener(this);
		fermer.setForeground(new java.awt.Color(49,140,231));
		seuil.addActionListener(this);		
		seuil.setForeground(new java.awt.Color(34,120,15));
		type.addActionListener(this);		
		type.setForeground(new java.awt.Color(34,120,15));
		longmatr.addActionListener(this);		
		longmatr.setForeground(new java.awt.Color(34,120,15));
		Lancer.addActionListener(this);		
		Lancer.setForeground(new java.awt.Color(34,120,15));
		Lancer.setIcon(image4); 
		Stop.addActionListener(this);
		Stop.setForeground(new java.awt.Color(219,23,2));
		Clear.addActionListener(this);
		Clear.setForeground(new java.awt.Color(219,23,2));

		table1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){}
			public void focusLost (FocusEvent e){
				for(int icol=0; icol<nbCol;icol++){
					for(int ilin=0; ilin<nbLigne;ilin++){
						if (table1.getValueAt(ilin, icol) != null){
							int value = Integer.parseInt( table1.getValueAt(ilin, icol).toString());
							matrix.setElement(ilin, icol, value);
						}
					}
				}				
			}
		});

		matrix = new MutablePositiveIntegerMatrix(nbLigne, nbCol);
	}

	///////////////////////////////actionPerformed////////////////////////////////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Ouvrir) {
			String s ="";
			String chaine = "" ;
			String pretitre="";
			JFileChooser fileSelect = new JFileChooser();
			if (fileSelect.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				s = fileSelect.getSelectedFile().getAbsolutePath();
			}
			try{
				InputStream ips=new FileInputStream(s); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;


				ligne=br.readLine();
				Pattern pattern = Pattern.compile(">(.+)");
				Matcher matcher =pattern.matcher(ligne);
				//Recupere la ligne de titre du fichier FASTA
				if(matcher.find()){
					pretitre=matcher.group(1);
				}
				//parcours le fichier et recupere la sequence
				while ((ligne=br.readLine())!=null){
					chaine+=ligne;
				}
				br.close();
			}		
			catch (Exception newException){
				System.out.println(newException);
			}
			try {
				this.sequence = new DNASequence(pretitre, chaine);
			} catch (BadDNACharacterException ex) {
				System.err.println("Bad DNA sequence " + ex);
			}
			text1.setText(this.sequence.rawSequence);
			barre.setMaximum(this.sequence.length() - ( this.nbCol + (this.sequence.length()%this.nbCol) ) ) ;
		}

		if(e.getSource() == Enregistrer) {
			try {
				String s ="";
				JFileChooser fileSelect = new JFileChooser();
				if (fileSelect.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					s = fileSelect.getSelectedFile().getAbsolutePath();
				}
				BufferedWriter out = new BufferedWriter(new FileWriter(s));
				//Ecrit dans le fichier de sortie le contenu du panneau de texte
				out.write(text2.getText());
				out.close();
			}
			catch (Exception newException) {
				System.out.println(newException);
			}
		}



		if(e.getSource() == boutonMatrice) {
			nbCol = 5;
			this.setVisible(false);
			this.pane12.remove(table1);
			this.table1 = new JTable(nbLigne, nbCol);
			table1.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e){}
				public void focusLost (FocusEvent e){
					for(int icol=0; icol<nbCol;icol++){
						for(int ilin=0; ilin<nbLigne;ilin++){
							if (table1.getValueAt(ilin, icol) != null){
								int value = Integer.parseInt( table1.getValueAt(ilin, icol).toString());
								matrix.setElement(ilin, icol, value);
							}
						}
					}				
				}
			});
			this.pane12.add(table1, BorderLayout.EAST);
			this.setVisible(true);
			matrix = new MutablePositiveIntegerMatrix(nbLigne, nbCol);

			// Column 0.
			this.matrix.setElement(0, 0, 2);
			table1.setValueAt(2, 0, 0);
			this.matrix.setElement(1, 0, 2);
			table1.setValueAt(2, 1, 0);
			this.matrix.setElement(2, 0, 2);
			table1.setValueAt(2, 2, 0);
			this.matrix.setElement(3, 0, 2);
			table1.setValueAt(2, 3, 0);

			// Column 1.
			this.matrix.setElement(0, 1, 2);
			table1.setValueAt(2, 0, 1);
			this.matrix.setElement(1, 1, 0);
			table1.setValueAt(0, 1, 1);
			this.matrix.setElement(2, 1, 3);
			table1.setValueAt(3, 2, 1);
			this.matrix.setElement(3, 1, 3);
			table1.setValueAt(3, 3, 1);

			// Column 2.
			this.matrix.setElement(0, 2, 8);
			table1.setValueAt(8, 0, 2);
			this.matrix.setElement(1, 2, 0);
			table1.setValueAt(0, 1, 2);
			this.matrix.setElement(2, 2, 0);
			table1.setValueAt(0, 2, 2);
			this.matrix.setElement(3, 2, 0);
			table1.setValueAt(0, 3, 2);

			// Column 3.
			this.matrix.setElement(0, 3, 1);
			table1.setValueAt(1, 0, 3);
			this.matrix.setElement(1, 3, 2);
			table1.setValueAt(2, 1, 3);
			this.matrix.setElement(2, 3, 2);
			table1.setValueAt(2, 2, 3);
			this.matrix.setElement(3, 3, 3);
			table1.setValueAt(3, 3, 3);

			// Column 4.
			this.matrix.setElement(0, 4, 2);
			table1.setValueAt(2, 0, 4);
			this.matrix.setElement(1, 4, 2);
			table1.setValueAt(2, 1, 4);
			this.matrix.setElement(2, 4, 2);
			table1.setValueAt(2, 2, 4);
			this.matrix.setElement(3, 4, 2);
			table1.setValueAt(2, 3, 4);
		}

		if(e.getSource() == aide) {
			popu1.showMessageDialog(null,"PredicSites 1.0 - © Copyright 2015\nDeveloppe par Birer Aurelien, Bonnardel Francois et Pageaud Yoann", "A propos de PredicSites 1.0",JOptionPane.INFORMATION_MESSAGE);
		}

		if(e.getSource() == fermer) {

			int choix=popu1.showConfirmDialog(null,"Quitter ?", "Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(choix == JOptionPane.OK_OPTION){
				System.exit(ABORT);
			}
		}

		if(e.getSource() == seuil) {
			String seuilvaleur = popu1.showInputDialog(null,"Veuillez definir le score minium du matchpattern","Seuil",JOptionPane.QUESTION_MESSAGE);
			Float seuil = Float.valueOf(seuilvaleur);
			if (seuil!=null) {
				if(seuil >= 0 && seuil <= 1){
					threshold = Float.valueOf(seuilvaleur);
					this.Lancer.setText("Lancer avec "+this.typeMatrice+" - seuil : "+threshold);
				}
			}
		}

		if(e.getSource() == type) {
			String[] tabType = {"entropy", "logOdd", "annule"};
			int valType = popu1.showOptionDialog(null, "Veuillez indiquer le type de matrice", "Type de matrice", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, tabType, tabType[2]);
			if (valType!=2){
				this.typeMatrice = tabType[valType];
				this.Lancer.setText("Lancer avec "+this.typeMatrice+" - seuil : "+threshold);
			}
		}

		if(e.getSource() == longmatr) {
			String longmatrvaleur = popu1.showInputDialog(null,"Veuillez definir le nombre de colonne de la matrice","Colonne",JOptionPane.QUESTION_MESSAGE);
			if (!longmatrvaleur.isEmpty()){
				this.nbCol = Integer.valueOf(longmatrvaleur);
				this.setVisible(false);
				this.pane12.remove(table1);
				this.table1 = new JTable(nbLigne, nbCol);
				table1.addFocusListener(new FocusListener() {
					public void focusGained(FocusEvent e){}
					public void focusLost (FocusEvent e){
						for(int icol=0; icol<nbCol;icol++){
							for(int ilin=0; ilin<nbLigne;ilin++){
								if (table1.getValueAt(ilin, icol) != null){
									int value = Integer.parseInt( table1.getValueAt(ilin, icol).toString());
									matrix.setElement(ilin, icol, value);
								}
							}
						}				
					}
				});
				this.pane12.add(table1, BorderLayout.EAST);
				this.setVisible(true);
				matrix = new MutablePositiveIntegerMatrix(nbLigne, nbCol);
			}
		}

		if(e.getSource() == Lancer) {
			Main.traitement();
		}

		if(e.getSource() == Stop) {
			Main.arretTraitement () ;
		}

		if(e.getSource() == Clear) {
			text2.setText("");
			ClearTraitement();
		}
	}

	public static void Traitement () {
		barre.setValue(barre.getValue()+1);
	}

	public static void ClearTraitement () {   
		barre.setValue(0);
	}

} 