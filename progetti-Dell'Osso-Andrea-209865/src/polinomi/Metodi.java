package polinomi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class Metodi {
	
	
	public static PolinomioLL inserimento(String linea) {
		String schema="(\\-?[0-9]+x\\^[0-9]+\\+?)*";
		if (!linea.matches(schema)) throw new IllegalArgumentException("Polinomio inserito non valido!");
		StringTokenizer st= new StringTokenizer(linea,"+-",true);
		PolinomioLL p1= new PolinomioLL();
		boolean meno=false;
		while (st.hasMoreTokens()) {
			String token=st.nextToken();
			
			if (token.equals("-")) {
				meno=true; 
				continue;
			}
			else if(token.equals("+")) {
				meno=false;
				continue;
			}
			
			else {
				if (meno) {
					StringTokenizer st2=new StringTokenizer(token,"-+x^");
					p1.add(new Monomio(-Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken())));
				}else {
				StringTokenizer st2=new StringTokenizer(token,"-+x^");
				p1.add(new Monomio(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken())));
				
				}
			}
	}
	return p1;
	}//inserimento
	
	public static LinkedList<PolinomioLL> leggiFile(File f) throws IOException{
		LinkedList<PolinomioLL> ris= new LinkedList<>();
		BufferedReader br=new BufferedReader(new FileReader(f));
		for(;;) {
			String s=br.readLine();
			if (s==null) break;
			StringTokenizer st= new StringTokenizer(s,", ");
			while( st.hasMoreTokens()) {
				ris.add(inserimento(st.nextToken().toString()));
			}
			
			
		}//for 
		br.close();
		return ris;
	}//leggiFile
	
	public static void salva(File f, LinkedList<PolinomioLL> lista) throws IOException {
		PrintWriter pw=new PrintWriter( new FileWriter(f));
		ListIterator<PolinomioLL> lit= lista.listIterator();
		while (lit.hasNext()) {
			pw.print(lit.next());
			if (lit.hasNext())
				pw.print(" ,");
		}//while
		
		pw.close();

	}
	
	public static void main(String[] args) throws IOException {
		String nomeFile=null;
		JFileChooser jfc=new JFileChooser();
		File f=null;
		int val=jfc.showOpenDialog(null);
		if (val==JFileChooser.APPROVE_OPTION) {
			nomeFile= jfc.getSelectedFile().getAbsolutePath();
			f=jfc.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Hai scelto il file:"+nomeFile);
		}
		else if(val==JFileChooser.CANCEL_OPTION)
			JOptionPane.showMessageDialog(null, "Hai annullato la scelta del file");
		
		LinkedList<PolinomioLL> ris=new LinkedList<>();
		PolinomioLL p1= new PolinomioLL();
		p1.add(new Monomio (3,4));
		p1.add(new Monomio(5,2));
		ris.add(p1);
		PolinomioLL p2= new PolinomioLL();
		p2.add(new Monomio (2,22));
		p2.add(new Monomio(8,4));
		ris.add(p2);
		System.out.println(ris);
		salva(f,ris);
		
	}//main
}//Metodi
