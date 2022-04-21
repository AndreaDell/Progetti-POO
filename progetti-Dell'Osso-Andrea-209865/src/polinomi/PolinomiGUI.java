package polinomi;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.*;


class FinestraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	File fileDiSalvataggio;
	private JMenuItem nuoviPol, salva , apri, esci , mul , add, der , val, op , salvaConNome,
						about;
	LinkedList<PolinomioLL> lista=new LinkedList<>(); 
	JPanel p;
	boolean salvato=false;
	
	public FinestraGUI() {
		this.setTitle("FINESTRA INTERATTIVA POLINOMI");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener( new WindowAdapter() {
	        public void windowClosing(WindowEvent e){
	       	 if( consensoUscita() ) System.exit(0);
	        }
	     } );
		AscoltatoreEventiAzione listener=new AscoltatoreEventiAzione();
		this.setBounds(400, 200, 600, 600);
		this.setLayout(new BorderLayout());
		
		this.setVisible(true);
		//creazione barra dei menu'
        JMenuBar menuBar=new JMenuBar();
        this.setJMenuBar( menuBar );
		
      //creazione file menu
        JMenu fileMenu=new JMenu("File");
        menuBar.add(fileMenu);
        nuoviPol=new JMenuItem("Crea nuovo");
        nuoviPol.addActionListener(listener);
        fileMenu.add(nuoviPol);
        salva=new JMenuItem("Salva");
        salva.addActionListener(listener);
        fileMenu.add(salva);
        salvaConNome=new JMenuItem("Salva con nome");
        salvaConNome.addActionListener(listener);
        fileMenu.add(salvaConNome);
        apri= new JMenuItem("Apri da file");
        apri.addActionListener(listener);
        fileMenu.add(apri);
        fileMenu.addSeparator();
        esci= new JMenuItem("Esci");
        esci.addActionListener(listener);
        fileMenu.add(esci);
      
      //creazione menu Comandi
        JMenu commandMenu=new JMenu("Comandi");
        menuBar.add(commandMenu);
        op= new JMenu("Operazioni tra polinomi");
        commandMenu.add(op);
        commandMenu.addSeparator();
        mul= new JMenuItem("Moltiplicazione");
        mul.addActionListener(listener);
        op.add(mul);
        add= new JMenuItem("Addizione");
        add.addActionListener(listener);
        op.add(add);
        der= new JMenuItem("Derivata");
        der.addActionListener(listener);
        commandMenu.add(der);
        val= new JMenuItem("Calcola il valore");
        val.addActionListener(listener);
        commandMenu.add(val);
        //creazione menu help
        JMenu help=new JMenu("Help");
        menuBar.add(help);
        about= new JMenuItem("Come funziona?");
        about.addActionListener(listener);
        help.add(about);
        
       menuIniziale();
       setLocation(200,200);
       setSize(500,400);
	}//costruttore
	
	private void menuIniziale(){
		nuoviPol.setEnabled(true);
		apri.setEnabled(true);
		salva.setEnabled(false);
		salvaConNome.setEnabled(false);
		esci.setEnabled(true);
		op.setEnabled(false);
		der.setEnabled(false);
		add.setEnabled(false);
		val.setEnabled(false);
	 }//menuIniziale
	
	private void menuAvviato() {
		nuoviPol.setEnabled(true);
		apri.setEnabled(false);
		salva.setEnabled(true);
		salvaConNome.setEnabled(true);
		esci.setEnabled(true);
		op.setEnabled(true);
		der.setEnabled(true);
		add.setEnabled(true);
		val.setEnabled(true);
	}//menuAvviato
	
	 private boolean consensoUscita(){
		   int option=JOptionPane.showConfirmDialog(
				   null, "Continuare ?", "Uscendo si perderanno tutti i dati!",
				   JOptionPane.YES_NO_OPTION);
		   return option==JOptionPane.YES_OPTION;
	}//consensoUscita
	 
	 private class BoxArea extends JFrame{
    	 private JTextField area;
    	 
    	 public BoxArea(){
    		 setTitle("Inserisci i polinomi");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel principale=new JPanel();
    	     JPanel field =new JPanel();
    	     JPanel stringa= new JPanel();
    	     JLabel s= new JLabel("Inserisci un polinomio: (nella forma coeff x ^ grado)");
    	     JLabel inf= new JLabel("-->",SwingConstants.LEFT);
    	     principale.setLayout( new BorderLayout() ); 
    	     area=new JTextField(10);
    	     area.setEditable(true);
    	     field.add(inf);
    	     field.add(area);
    	     stringa.add(s);
    	     this.add(principale);
    	     principale.add(s, BorderLayout.NORTH);
    	     principale.add(field,BorderLayout.CENTER);
    	     JButton button= new JButton("Inserisci");
    	     button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					PolinomioLL p1=Metodi.inserimento(area.getText());
					lista.add(p1);
					System.out.println("lista"+lista);
					JOptionPane.showMessageDialog(null, "Polinomio inserito correttamente", "OK",JOptionPane.INFORMATION_MESSAGE);
					area.setText("");
					menuAvviato();
					salvato=false;
					}catch(Exception ex ) {
						JOptionPane.showMessageDialog(null, "Polinomio NON inserito correttamente", "Errore!",JOptionPane.ERROR_MESSAGE);
						area.setText("");
					}//catch
				}//actionPerformered
    	     });
    	     principale.add(button, BorderLayout.SOUTH);
    		 setLocation(250,340);
    		// setSize(200,100);
    		pack();
    		 
    	 }//BoxArea(costruttore)
	 }//BoxArea
	 
	 private class AddArea extends JFrame{
		 LinkedList<PolinomioLL> poli= new LinkedList<>();
		 Polinomio addizione;
		 public AddArea() {
			 setTitle("Addizione tra polinomi");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel principale=new JPanel();
    	     principale.setLayout( new BorderLayout() ); 
    	     JPanel str= new JPanel();
    	     str.add(new JLabel("Selezionare almeno due polinomi per effettuare l'addizione."));
    	     principale.add(str,BorderLayout.NORTH);
    	     JPanel ch= new JPanel();
    	     for (PolinomioLL x: lista) {
    	    	 JCheckBox j=new JCheckBox(x.toString());
    	    	 j.addActionListener(new ActionListener() {
    	    		    @Override
    	    		    public void actionPerformed(ActionEvent event) {
    	    		        JCheckBox cb = (JCheckBox) event.getSource();
    	    		        if (cb.isSelected()) {
    	    		        	 poli.add(x);
    	    		        } else {
    	    		        	 if (poli.contains(x))
    	    		        		 poli.remove(x);
    	    		        }
    	    		    }
    	    		});
    	    	 ch.add(j);
    	     }//for
    	     principale.add(ch,BorderLayout.CENTER);
    	     this.add(principale);
    	     JButton button= new JButton("Addiziona!");
    	     button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					addizione=new PolinomioLL();
					for (PolinomioLL x: poli) {
						addizione=addizione.add(x);
					}
					if (poli.size()==1)
						throw new IllegalArgumentException("Gli argomenti dell'addizione devono essere due!");
					JOptionPane.showMessageDialog(null, "Addizione effettuata correttamente!\n Il risultato è : " +addizione, "OK",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex ) {
						JOptionPane.showMessageDialog(null, "Addizione NON effettuata correttamente!", "OK",JOptionPane.ERROR_MESSAGE);
					}//catch
				}//actionPerformered
    	     });
    	     principale.add(button, BorderLayout.SOUTH);
    	     pack();
    	     setLocation(250,340);
    		 
		 }//costruttore
		
			
	 }//AddArea
	 
	 private class MulArea extends JFrame{
		 LinkedList<PolinomioLL> poliSel= new LinkedList<>();
		 Polinomio moltiplicazione;
		 public MulArea() {
			 setTitle("Moltiplicazione tra polinomi");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel principale=new JPanel();
    	     principale.setLayout( new BorderLayout() ); 
    	     JPanel str= new JPanel();
    	     str.add(new JLabel("Selezionare almeno due polinomi per effettuare la moltiplicazione."));
    	     principale.add(str,BorderLayout.NORTH);
    	     JPanel ch= new JPanel();
    	     for (PolinomioLL x: lista) {
    	    	 JCheckBox j=new JCheckBox(x.toString());
    	    	 j.addActionListener(new ActionListener() {
    	    		    @Override
    	    		    public void actionPerformed(ActionEvent event) {
    	    		        JCheckBox cb = (JCheckBox) event.getSource();
    	    		        if (cb.isSelected()) {
    	    		        	 poliSel.add(x);
    	    		        } else {
    	    		        	 if (poliSel.contains(x))
    	    		        		 poliSel.remove(x);
    	    		        }
    	    		    }
    	    		});
    	    	 ch.add(j);
    	     }//for
    	     principale.add(ch,BorderLayout.CENTER);
    	     this.add(principale);
    	     JButton button= new JButton("Moltiplica!");
    	     button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					moltiplicazione=new PolinomioLL();
					moltiplicazione.add(new Monomio(1,0));
					for (PolinomioLL x: poliSel) {
						moltiplicazione=moltiplicazione.mul(x);
					}
					if (poliSel.size()==1 || poliSel.size()==0)
						throw new IllegalArgumentException("Gli argomenti dell'addizione devono essere due!");
					JOptionPane.showMessageDialog(null, "Moltiplicazione effettuata correttamente!\n Il risultato è : " +moltiplicazione, "OK",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex ) {
						JOptionPane.showMessageDialog(null, "Moltiplicazione NON effettuata correttamente!", "OK",JOptionPane.ERROR_MESSAGE);
					}//catch
				}//actionPerformered
    	     });
    	     principale.add(button, BorderLayout.SOUTH);
    	     pack();
    	     setLocation(250,340);
		 }//costruttore
	 }//MulArea
	 
	 private class DerivArea extends JFrame{
		 LinkedList<Polinomio> daDer= new LinkedList<>();
		 public DerivArea() {
			 setTitle("Derivata di un polinomio");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel principale=new JPanel();
    	     principale.setLayout( new BorderLayout() ); 
    	     JPanel str= new JPanel();
    	     str.add(new JLabel("Selezionare solo un polinomio per effettuarne la derivata"));
    	     principale.add(str,BorderLayout.NORTH);
    	     JPanel ch= new JPanel();
    	     for (PolinomioLL x: lista) {
    	    	 JCheckBox j=new JCheckBox(x.toString());
    	    	 j.addActionListener(new ActionListener() {
    	    		    @Override
    	    		    public void actionPerformed(ActionEvent event) {
    	    		        JCheckBox cb = (JCheckBox) event.getSource();
    	    		        if (cb.isSelected()) {
    	    		        	 daDer.add(x);
    	    		        } else {
    	    		        	if (daDer.contains(x))
	    		        		 daDer.remove(x);
    	    		        }
    	    		    }
    	    		});
    	    	 ch.add(j);
    	     }//for
    	     principale.add(ch,BorderLayout.CENTER);
    	     this.add(principale);
    	     JButton button= new JButton("Deriva!");
    	     button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					Polinomio derivato=daDer.get(0).derivata();
					if (daDer.size()>1  || daDer.size()==0 ) throw new IllegalArgumentException("Seleziona solo un polinomio!");
					JOptionPane.showMessageDialog(null, "Derivata effettuata correttamente!\n Il risultato è : " +derivato, "OK",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex ) {
						JOptionPane.showMessageDialog(null, "Derivata NON effettuata correttamente!", "OK",JOptionPane.ERROR_MESSAGE);
					}//catch
				}//actionPerformered
    	     });
    	     principale.add(button, BorderLayout.SOUTH);
    	     pack();
    	     setLocation(250,340);
		 }//costruttore
		 
	 }//DerivArea
	 
	 private class ValArea extends JFrame{
		 LinkedList<Polinomio> daCalc= new LinkedList<>();
		 public ValArea() {
			 setTitle("Valore di un polinomio");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel principale=new JPanel();
    	     principale.setLayout( new GridLayout(4,1));
    	     JPanel str= new JPanel();
    	     JLabel myLabel=new JLabel();
    	     myLabel.setText("<html>Selezionare solo un polinomio per calcolarne il valore. <br>Inserisci il valore nel campo sottostante nella forma (intero . decimale)</html>");
    	     str.add(myLabel);
    	    
    	     principale.add(str);
    	     JPanel field= new JPanel();
    	     JLabel inf= new JLabel("-->",SwingConstants.LEFT);
    	     JTextField area= new JTextField(5);
    	     area.setEditable(true);
    	     field.add(inf);
    	     field.add(area);
    	     principale.add(field);
    	     JPanel ch= new JPanel();
    	     for (PolinomioLL x: lista) {
    	    	 JCheckBox j=new JCheckBox(x.toString());
    	    	 j.addActionListener(new ActionListener() {
    	    		    @Override
    	    		    public void actionPerformed(ActionEvent event) {
    	    		        JCheckBox cb = (JCheckBox) event.getSource();
    	    		        if (cb.isSelected()) {
    	    		        	 daCalc.add(x);
    	    		        } else {
    	    		        	if (daCalc.contains(x))
	    		        		 daCalc.remove(x);
    	    		        }
    	    		    }
    	    		});
    	    	 ch.add(j);
    	     }//for
    	     principale.add(ch);
    	     this.add(principale);
    	     JButton button= new JButton("Calcola!");
    	     button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					String s=area.getText();
					String REGEX="\\-?[0-9]+(\\.?[0-9]+)?";
					if (!s.matches(REGEX)) throw new IllegalArgumentException("Stringa inserita non è un numero!");
					double valore= daCalc.get(0).valore(Double.parseDouble(s));
					if (daCalc.size()>1 || daCalc.size()==0) throw new IllegalArgumentException("Seleziona solo un polinomio!");
					JOptionPane.showMessageDialog(null, "Valore del polinomio calcolato correttamente!\n Il risultato è : " +String.format("%1.3f",valore), "OK",JOptionPane.INFORMATION_MESSAGE);
					area.setText("");
					}catch(Exception ex ) {
						JOptionPane.showMessageDialog(null, "Valore del polinomio NON calcolato correttamente!", "OK",JOptionPane.ERROR_MESSAGE);
						area.setText("");
					}//catch
				}//actionPerformered
    	     });
    	     principale.add(button);
    	     pack();
    	     setLocation(250,340);
		 }//costruttore
		 
	 }//ValArea
	 
	 private class AscoltatoreEventiAzione implements ActionListener{
	  	   public void actionPerformed(ActionEvent e){
	  		 if( e.getSource()==esci ){
	  			 if (salvato) System.exit(0);
	  			 else if( consensoUscita() ) System.exit(0);
	  		   }//esci
	  		 
	  		 else if(e.getSource()==apri) {
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
	  							   lista=Metodi.leggiFile(fileDiSalvataggio);
	  							   menuAvviato();
	  							   JOptionPane.showMessageDialog(null, "Hai scelto il file:"+fileDiSalvataggio.getAbsolutePath());
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
	  		 
	  		 else if (e.getSource()==salvaConNome) {
	  			 //file chooser
	  			   JFileChooser chooser=new JFileChooser();
	  			   try{
	  				 JOptionPane.showMessageDialog(null,"Inserire il nome del file nel formato: nomeFile.txt ");
	  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
	  						   fileDiSalvataggio=chooser.getSelectedFile();
	  						   FinestraGUI.this.setTitle(fileDiSalvataggio.getName());
	  					   }
	  				   if( fileDiSalvataggio!=null ){
	  					   Metodi.salva(fileDiSalvataggio, lista);
	  					   salvato=true;
	  					 JOptionPane.showMessageDialog(null,"Salvataggio effettuato correttamente!");
	  				   }
	  				   else
	  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }catch( Exception exc ){
	  				 JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }  			   
	  		 }//salvaConNome
	  		 
	  		 else if (e.getSource()==salva) {
	  			//file chooser
	  			   JFileChooser chooser=new JFileChooser();
	  			   try{
	  				   if( fileDiSalvataggio!=null ){
	  					   int ans=JOptionPane.showConfirmDialog(null,"Sovrascrivere "+fileDiSalvataggio.getAbsolutePath()+" ?");
						   if( ans==0 /*SI*/) {
							   Metodi.salva(fileDiSalvataggio, lista);
							   salvato=true;
						   }
						   else
							   JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
						   return;
					   }
	  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
	  					   fileDiSalvataggio=chooser.getSelectedFile();
	  					   FinestraGUI.this.setTitle(fileDiSalvataggio.getName());
	  				   }
	  				   if( fileDiSalvataggio!=null ){
	  					 Metodi.salva(fileDiSalvataggio, lista);
	  					 salvato=true;
	  				   }
	  				   else
	  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }catch( Exception exc ){
	  				 JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
	  			   }
	  		 }//salva
	  		 
	  		else if( e.getSource()==about ){
	  			   JOptionPane.showMessageDialog( null,
	  					   "Inserisci i polinomi nella forma coeff x ^grado ed esegui le tutte le operazioni che vuoi!\n",
	  						"Help", JOptionPane.PLAIN_MESSAGE );
	  		   }//about
	  		 
	  		else if (e.getSource()==nuoviPol) {
	  			BoxArea bx= new BoxArea();
	  			bx.setVisible(true);
	  		}//nuoviPol
	  		 
	  		else if (e.getSource()==add) {
	  			AddArea ad= new AddArea();
	  			ad.setVisible(true);
	  		}//add
	  		 
	  		else if (e.getSource()==mul) {
	  			MulArea ma= new MulArea();
	  			ma.setVisible(true);
	  		}//mul
	  		 
	  		else if (e.getSource()==der) {
	  			DerivArea da= new DerivArea();
	  			da.setVisible(true);
	  		}//der
	  		
	  		else if (e.getSource()==val) {
	  			ValArea va= new ValArea();
	  			va.setVisible(true);
	  		}//val
	  		 
	  	   }//actionPerformed
	 
	 }//AscoltatoreEventiAzione
	  			 
	 
	}// FinestraGui

	


public class PolinomiGUI {
	
	public static void main(String[] args) {
		new FinestraGUI();
	}
	

}
