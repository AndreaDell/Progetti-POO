package sudoku;
import sudoku.Sudoku;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;





 class FinestraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	File fileDiSalvataggio;
	private JMenuItem about , salva, apri , nPartita, esci , imposta, hnp, haf, hsp;
	private int [][] salvare= new int [9][9];
	boolean salvato=false, aperto=false;
	private int [][] aprire= new int [9][9];
	
	public FinestraGUI() {
		this.setTitle("SUDOKU");
		
		AscoltatoreEventiAzione listener=new AscoltatoreEventiAzione();
		this.setBounds(400, 200, 600, 600);
		this.setLayout(new BorderLayout());
		
		this.setVisible(true);
		//creazione menubar
		JMenuBar menuBar= new JMenuBar();
		this.setJMenuBar(menuBar);
		
		//creazione menu partita
		JMenu partita= new JMenu("Partita");
		menuBar.add(partita);
		nPartita= new JMenuItem("Nuova partita");
		nPartita.addActionListener(listener);
		partita.add(nPartita);
		partita.addSeparator();
		apri= new JMenuItem("Apri da file");
		apri.addActionListener(listener);
		partita.add(apri);
		partita.addSeparator();
		esci= new JMenuItem("Esci");
		esci.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	System.exit(0);
            }
        });
		partita.add(esci);
		
		
		//creazione menu help
		JMenu help= new JMenu("Help");
		menuBar.add(help);
		about= new JMenu("Come funziona?");
		hnp=new JMenuItem("Nuova partita (?)");
		hnp.addActionListener(listener);
		about.add(hnp);
		haf= new JMenuItem("Apri da file (?)");
		haf.addActionListener(listener);
		about.add(haf);
		hsp= new JMenuItem("Salva su file (?)");
		hsp.addActionListener(listener);
		about.add(hsp);
		
		help.add(about);
		setLocation(200,200);
	    setSize(500,400);

	}//costruttore
	
	private void menuIniziale() {
		nPartita.setEnabled(true);
		salva.setEnabled(false);
		apri.setEnabled(true);
		about.setEnabled(true);
		esci.setEnabled(true);
	}//menuIniziale
	
	private void menuAvviato() {
		nPartita.setEnabled(true);
		salva.setEnabled(false);
		apri.setEnabled(true);
		about.setEnabled(true);
		esci.setEnabled(true);
	}//menuAvviato
	
	private class NewGame extends JFrame{
		private static final long serialVersionUID = 1L;
		int gridSize=3, k;
	    private JPanel  buttonPanel, grid1, grid2, grid3,grid4, grid5, grid6, grid7, grid8, grid9;
	    private JButton risolviButton, nextButton, prevButton;
	    AscoltatoreEventiAzione listener=new AscoltatoreEventiAzione();
	    
	    
		public NewGame(Sudoku s) {
			
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener( new WindowAdapter() {
		        public void windowClosing(WindowEvent e){
		       	 if( consensoUscita() ) System.exit(0);
		        }
		     } );
			//creazione menubar
			JMenuBar menuBar= new JMenuBar();
			this.setJMenuBar(menuBar);
			
			//creazione menu partita
			JMenu partita= new JMenu("Partita");
			menuBar.add(partita);
			salva= new JMenuItem("Salva su file");
			salva.addActionListener(listener);
			partita.add(salva);
			imposta= new JMenuItem("Imposta numero massimo soluzioni");
			imposta.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	            	s.numMaxSol=Integer.valueOf(JOptionPane.showInputDialog("Inserisci il numero massimo di soluzioni:"))-1;
	            }
	        });
			partita.add(imposta);
			partita.addSeparator();
			esci= new JMenuItem("Esci");
			esci.addActionListener(listener);
			partita.add(esci);
			
		JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));    
        buttonPanel = new JPanel();  //matrice grande 
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        blackline = BorderFactory.createLineBorder(Color.black);
        //grid 1
        grid1= new JPanel(new GridLayout(gridSize, gridSize));
        grid1.setBorder(blackline);
        //grid 2
        grid2= new JPanel(new GridLayout(gridSize, gridSize));
        grid2.setBorder(blackline);
        //grid 3
        grid3= new JPanel(new GridLayout(gridSize, gridSize));
        grid3.setBorder(blackline);
        //grid 4
        grid4= new JPanel(new GridLayout(gridSize, gridSize));
        grid4.setBorder(blackline);
        //grid 5
        grid5= new JPanel(new GridLayout(gridSize, gridSize));
        grid5.setBorder(blackline);
        // grid 6
        grid6= new JPanel(new GridLayout(gridSize, gridSize));
        grid6.setBorder(blackline);
        //grid 7
        grid7= new JPanel(new GridLayout(gridSize, gridSize));
        grid7.setBorder(blackline);
        // grid 8
        grid8= new JPanel(new GridLayout(gridSize, gridSize));
        grid8.setBorder(blackline);
        //grid 9
        grid9= new JPanel(new GridLayout(gridSize, gridSize));
        grid9.setBorder(blackline);
     
        risolviButton = new JButton("     Risolvi    ");
        risolviButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	
            	buttonPanel.removeAll();
            	grid1.removeAll();
            	grid2.removeAll();
            	grid3.removeAll();
            	grid4.removeAll();
            	grid5.removeAll();
            	grid6.removeAll();
            	grid7.removeAll();
            	grid8.removeAll();
            	grid9.removeAll();
            	buttonPanel.repaint();
            	s.risolvi();
                refresh(k,s);
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                imposta.setEnabled(false);
                apri.setEnabled(false);
                buttonPanel.add(grid1);
                buttonPanel.add(grid2);
                buttonPanel.add(grid3);
                buttonPanel.add(grid4);
                buttonPanel.add(grid5);
                buttonPanel.add(grid6);
                buttonPanel.add(grid7);
                buttonPanel.add(grid8);
                buttonPanel.add(grid9);
                
                contentPane.add(buttonPanel);
                
                
                setContentPane(contentPane);
            }
        });// ascoltatore eventi bottone risolvi
        
        leftPanel.add(risolviButton);
        nextButton = new JButton("     Next->>   ");
        
        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	try {
            		k++;
            		buttonPanel.removeAll();
            		grid1.removeAll();
            		grid2.removeAll();
            		grid3.removeAll();
            		grid4.removeAll();
            		grid5.removeAll();
            		grid6.removeAll();
            		grid7.removeAll();
            		grid8.removeAll();
            		grid9.removeAll();
            		buttonPanel.repaint();
            		refresh(k,s);
               
            		buttonPanel.add(grid1);
            		buttonPanel.add(grid2);
            		buttonPanel.add(grid3);
            		buttonPanel.add(grid4);
            		buttonPanel.add(grid5);
            		buttonPanel.add(grid6);
            		buttonPanel.add(grid7);
            		buttonPanel.add(grid8);
            		buttonPanel.add(grid9);
                
            		contentPane.add(buttonPanel);
            		setContentPane(contentPane);
            	}catch(Exception e) {
            		JOptionPane.showMessageDialog(null, "Soluzione Finite", "OK",JOptionPane.ERROR_MESSAGE);
            	}//catch
            	
            }
        });//actionPerformered  next
        leftPanel.add(nextButton);
        
        //PREVIOUS BOTTON
        prevButton = new JButton("<<-Previous");
        prevButton.setEnabled(false);
        prevButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	try {
            		k--;
            		buttonPanel.removeAll();
            		grid1.removeAll();
            		grid2.removeAll();
            		grid3.removeAll();
            		grid4.removeAll();
            		grid5.removeAll();
            		grid6.removeAll();
            		grid7.removeAll();
            		grid8.removeAll();
            		grid9.removeAll();
            		buttonPanel.repaint();
            		refresh(k,s);
               
            		buttonPanel.add(grid1);
            		buttonPanel.add(grid2);
            		buttonPanel.add(grid3);
            		buttonPanel.add(grid4);
            		buttonPanel.add(grid5);
            		buttonPanel.add(grid6);
            		buttonPanel.add(grid7);
            		buttonPanel.add(grid8);
            		buttonPanel.add(grid9);
                
            		contentPane.add(buttonPanel);
            		setContentPane(contentPane);
            	}catch(Exception e) {
            		JOptionPane.showMessageDialog(null, "Soluzione precedente non disponibile!", "OK",JOptionPane.ERROR_MESSAGE);
            	}//catch
            }
        });//actionPerformered  previous
        leftPanel.add(prevButton);
       
        contentPane.add(leftPanel,BorderLayout.WEST);
        //FINE PARTE SINISTRA
        buttonPanel = new JPanel();  //matrice grande 
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize));
        //INIZIALIZZAZIONE MATRICE VUOTA-- DA INSERIRE
        int i;
        int j;
        for ( i = 0; i < 9; i++)
        {
            for (j = 0; j < 9; j++)
            {	
            	final int riga=i;
            	final int col=j;
                JButton button = new JButton(" ");
                if (s.board[i][j]!=0)
                	button.setText(""+s.board[i][j]+"");
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                Font font = new Font("Arial", Font.PLAIN, 20);
                button.setFont(font);
                button.setForeground(Color.BLACK);
                button.setBackground(Color.WHITE);
                button.addActionListener(new ActionListener() {
	    		    @Override
	    		    public void actionPerformed(ActionEvent event) {
	    		    	try {
	    		       int val=Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero da inserire in questa casella"));
	    		       s.imposta(riga,col,val);
	    		       button.setText(String.valueOf(val));
	    		       
	    		       for (int i=0;i<9;i++)
	    		    	   for (int j=0;j<9;j++) {
	               			salvare[i][j]=s.board[i][j];
	               			
	               		}
	    		    }catch(Exception ex) {
	    		    	JOptionPane.showMessageDialog(null, "Valore non assegnabile!", "OK",JOptionPane.ERROR_MESSAGE);
	    		    }//catch
	    		    }
	    		});//actionPerformered per ogni bottone
                
                //DIVISIONE IN 9 SOTTOMATRICI 3X3
                if (i<3 && j<3) {
        			grid1.add(button);
        		}
        		else if (i<3 && j<6) {
        			grid2.add(button);
        		}
        		else if (i<3 && j<9) {
        			grid3.add(button);
        		}
        		if (i>2 && i<6 && j<3) {
        			grid4.add(button);
        		}
        		else if(i>2 && i<6 && j<6) {
        			grid5.add(button);
        		}
        		else if (i>2 && i<6 && j<9) {
        			grid6.add(button);
        		}
        		if (i>5 && j<3) {
        			grid7.add(button);
        		}
        		else if (i>5 && j<6) {
        			grid8.add(button);
        		}
        		else if (i>5 && j<9) {
        			grid9.add(button);
        		}
               
                
            }//for esterno	
            }//for interno
        
        //aggiunta sottomatrici al buttonPanel (matrice grande)
        buttonPanel.add(grid1);
        buttonPanel.add(grid2);
        buttonPanel.add(grid3);
        buttonPanel.add(grid4);
        buttonPanel.add(grid5);
        buttonPanel.add(grid6);
        buttonPanel.add(grid7);
        buttonPanel.add(grid8);
        buttonPanel.add(grid9);
        
        contentPane.add(buttonPanel,BorderLayout.CENTER);
        

        setContentPane(contentPane);
        this.setSize(500,400);
        setVisible(true);
        setLocation(200,200);
		}//costruttore
		//metodo refresh che serve per aggiornare il contenuto delle 9 sottomatrici
		private void refresh(int k,Sudoku s) {
	        for (int i = 0; i < 9; i++)
	        {
	            for (int j = 0; j < 9; j++)
	            {	
	            	int n=s.listaSol.get(k)[i][j];
	                JButton button = new JButton(" "+n+" ");
	                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	                Font font = new Font("Arial", Font.PLAIN, 20);
	                button.setFont(font);
	                button.setForeground(Color.BLACK);
	                button.setBackground(Color.WHITE);
	                button.setEnabled(false);
	                
	                if (i<3 && j<3) {
	        			grid1.add(button);
	        		}
	        		else if (i<3 && j<6) {
	        			grid2.add(button);
	        		}
	        		else if (i<3 && j<9) {
	        			grid3.add(button);
	        		}
	        		
	        		if (i>2 && i<6 && j<3) {
	        			grid4.add(button);
	        		}
	        		else if(i>2 && i<6 && j<6) {
	        			grid5.add(button);
	        		}
	        		else if (i>2 && i<6 && j<9) {
	        			grid6.add(button);
	        		}
	        		if (i>5 && j<3) {
	        			grid7.add(button);
	        		}
	        		else if (i>5 && j<6) {
	        			grid8.add(button);
	        		}
	        		else if (i>5 && j<9) {
	        			grid9.add(button);
	        		}
	               
	            }//for esterno
	            }//for interno
		}//refresh
		
	}//New Game
	
	
	
      
	private boolean consensoUscita(){
		   int option=JOptionPane.showConfirmDialog(
				   null, "Continuare ?", "Uscendo si perderanno tutti i dati!",
				   JOptionPane.YES_NO_OPTION);
		   return option==JOptionPane.YES_OPTION;
	}//consensoUscita
	 
	 private class AscoltatoreEventiAzione implements ActionListener{
	  	   public void actionPerformed(ActionEvent e){
	  		   if (e.getSource()==nPartita) {
	  			   if (aperto) {
	  				 Sudoku s= new Sudoku(aprire);
	  				 NewGame n= new NewGame(s);
		  			 n.setVisible(true);
	  				   
	  			   }else {
	  			   Sudoku s= new Sudoku();
	  			   NewGame n= new NewGame(s);
	  			   n.setVisible(true);
	  			   }
	  		   }//nuova partita
	  		   else if (e.getSource()==esci) {
	  			   if (salvato) System.exit(0);
	  			   else if( consensoUscita() ) System.exit(0);
	  		   }//esci
	  		   else if (e.getSource()==salva) {
	  			//file chooser
	  			   JFileChooser chooser=new JFileChooser();
	  			   try{
	  				 JOptionPane.showMessageDialog(null,"Inserire il nome del file nel formato: nomeFile.txt ");
	  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
	  						 fileDiSalvataggio=chooser.getSelectedFile();
	  						 FinestraGUI.this.setTitle(fileDiSalvataggio.getName());
	  					   }
	  				   if( fileDiSalvataggio!=null ){
	  					 salva(fileDiSalvataggio,salvare);
	  					 JOptionPane.showMessageDialog(null,"Salvataggio effettuato correttamente!");
	  					 salvato=true;
	  				   }
	  				   else
	  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }catch( IOException exc ){
	  				 JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }  			   
	  		   }//salva
	  		   else if(e.getSource()==hnp) {
	  			   JOptionPane.showMessageDialog(null, "Per inizializzare una nuova partita, premere sul menu Partita-->Nuova partita \n "
	  			   		+ "A questo punto selezionare le celle in cui  si vogliono aggiungere i numeri del Sudoku.\n"
	  			   		+ "Per impostare il numero massimo di soluzioni che il programma determinerà: \n"
	  			   		+ "Cliccare sul menù Partita--> Imposta numero massimo soluzioni. (E' impostato 10 di default)\n"
	  			   		+ "Per far risolvere il Sudoku: \n"
	  			   		+ "Cliccare sul tasto 'Risolvi', posto accanto alla matrice del Sudoku. \n"
	  			   		+ "Per scorrere la prossima soluzione proposta dal programma: \n "
	  			   		+ "Cliccare sul tasto 'Next-->' ,posto accanto alla matrice del Sudoku. \n"
	  			   		+ "Per scorrere la soluzione precedente: \n "
	  			   		+ "Cliccare sul tastp '<--Previous' posto accanto alla matrice del Sudoku. \n"
	  			   		+ "Buon gioco!" );
	  		   }//help nuova partita
	  		   else if (e.getSource()==haf) {
	  			 JOptionPane.showMessageDialog(null, "Per inizializzare il Sudoku a partire da un file: \n"
	  			 		+ "Cliccare sul menù Partita-->Apri da file \n"
	  			 		+ "E' necessario selezionare un file nel formato .txt e strutturato nel seguente modo: \n"
	  			 		+ "[ \n"
	  			 		+ "[0, 0, 3, 0, 0, 0, 0, 0, 0, ] , \n"
	  			 		+ "... scrivere tutte le righe della matrice andando a capo a ogni fine riga... \n"
	  			 		+ "e concludere con l'ultima riga e: \n"
	  			 		+ "[0, 0, 0, 4, 0, 0, 0, 0, 0, ] , \n"
	  			 		+ "] \n"
	  			 		+ "E' necessario rispettare questa formattazione altrimenti il programma non leggerà il File \n"
	  			 		+ "Buon gioco!");
	  		   }//help apri file
	  		   else if (e.getSource()==hsp) {
	  			 JOptionPane.showMessageDialog(null, "Questa funzione serve per salvare su file la matrice di partenza di un Sudoku: \n"
	  			 		+ "Per salvare quindi, far iniziare una partita e una volta impostate le celle \n"
	  			 		+ "andare nel menù Partita-->Salva su file \n "
	  			 		+ "A questo si aprirà una finestra di dialogo dove: \n"
	  			 		+ "-Scegliere un file .txt già esistente sul proprio PC e sovrascriverlo con la matrice di partenza del Sudoku. \n"
	  			 		+ "-Andare nella directory dove si vuole effettuare il salvataggio e generare il file scrivendo: nomeFile.txt \n"
	  			 		+ "Una volta effettuate queste semplici operazioni, avrete salvato su file il vostro Sudoku di partenza. \n"
	  			 		+ "Buon gioco!");
	  		   }//help salva partita
	  		   else if (e.getSource()==apri) {
	  			//file chooser
	  			   JFileChooser chooser=new JFileChooser();
	  			   try{
	  				   if( chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION ){
	  					   if( !chooser.getSelectedFile().exists() ){
	  						   JOptionPane.showMessageDialog(null,"File inesistente!"); 
	  					   }
	  					   else{	
	  						   fileDiSalvataggio=chooser.getSelectedFile();
	  						   FinestraGUI.this.setTitle(fileDiSalvataggio.getName());
	  						   try{
	  							   JOptionPane.showMessageDialog(null, "Hai scelto il file:"+fileDiSalvataggio.getAbsolutePath());
	  							   
	  							  aprire= apri(fileDiSalvataggio);
	  							  aperto=true;
	  							 
	  						   }catch(IOException ioe){
	  							   JOptionPane.showMessageDialog(null,"Fallimento apertura. File malformato!");
	  						   }
	  					   }
	  				   }
	  				   else
	  					   JOptionPane.showMessageDialog(null,"Nessuna apertura!");
	  			   }catch( Exception exc ){
	  				   exc.printStackTrace();
	  			   }
	  		   }//apri
	  		   
	  			
	  	   }//actionperformered
	 }//AscoltatoreEventiAzione
	 
	 private void salva(File f, int [][] mat) throws IOException{   // metodo che salva su file
		 PrintWriter pw=new PrintWriter( new FileWriter(f));
			 pw.print("[ ");
			 pw.println();
			for (int i = 0; i < 9; i++){
				pw.print("[");
	            for (int j = 0; j < 9; j++){	
	            	pw.print(mat[i][j]);
	            	pw.print(", ");
	            }
	            pw.print("] ,");
	            pw.println();
	        }
			pw.print(" ]");
			pw.close();
	 }//salva
	 
	 private int [][] apri(File f) throws IOException{
		 int[][] ris= new int[9][9];
			BufferedReader br=new BufferedReader(new FileReader(f));
			br.readLine();int i=0;
			for(;;) {
				String s=br.readLine();
				if (s==null) break;
				StringTokenizer st= new StringTokenizer(s," [],",false);
				int j=0;
				while( st.hasMoreTokens()) {
							ris[i][j]=Integer.valueOf(st.nextToken());
							j++;
				}//while
				i++;
				
			}//for 
			br.close();
			Arrays.deepToString(ris);
			return ris;
	 }//apri
	 
 }//FinestraGUI
	 public class SudokuGUI{
		 
	 public static void main(String[] args) {
		 new FinestraGUI();
		 
	 }//main
}//SudokuGUI
