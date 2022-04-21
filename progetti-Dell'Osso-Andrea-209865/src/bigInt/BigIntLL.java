package bigInt;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class BigIntLL extends AbstractBigInt{

	private LinkedList<Integer> lista=new LinkedList<>();  // dichiarazione lista

	public BigIntLL() {} // costruttore di default
	
	public BigIntLL(String s) { //costruttore usato passando come parametro una stringa
		int cnt=0;
		String PATTERN="[\\d]*"; // uso REGEX --> se la stringa non è un numero intero sollevo una eccezione
		if (!s.matches(PATTERN)) throw new IllegalArgumentException("Inserisci un numero intero!!");		

		while (cnt<s.length()) {
			Integer i= Character.getNumericValue(s.charAt(cnt));
			lista.add(i);
			cnt++;
		}//while
	}//costruttore(String)
	
	public BigIntLL(int x) { // costruttore usato passando come parametro un int 
		if (x<0) throw new IllegalArgumentException("Numero inserito negativo");  // int e' atteso >=0
		String s= String.valueOf(x);
		int cnt=0;
		while (cnt<s.length()) {
			Integer i= Character.getNumericValue(s.charAt(cnt));
			lista.add(i);
			cnt++;
		}//while
	}//costruttore(int)
	
	
	public BigIntLL factory(int x) { // metodo che serve nell'interfaccia per costruire un BigInt a partire da un intero
		return new BigIntLL(x);
	}// factory
	
	@Override    
	public int length() { // reimplementato nella classe concreta per motivi di costo
		return lista.size();
	}//lenght
	
	@Override
	public String value() {// reimplementato per motivi di costo
		return this.toString();
	}

	public BigInt add( BigInt a ) {
		StringBuilder sb1=new StringBuilder(50);  // StringBuilder per this
		StringBuilder sb2=new StringBuilder(50);    // StringBuilder per a
		Iterator<Integer> i1=iterator();     // Iterator per this
		Iterator<Integer> i2=a.iterator();   // Iterator per a
		
		while (i1.hasNext()) {
			sb1.append(i1.next());    // caricamento di this su sb1
		}
		
		while (i2.hasNext()) {
			sb2.append(i2.next());   // caricamento di a su sb2
		}
		
		if (sb1.length()==sb2.length()) {  // caso in cui i due BigInt abbiano lo stesso numero di cifre
			Integer riporto=0;
			for (int i=sb1.length()-1;i>=0;i--) {
				Integer n1= Character.getNumericValue(sb1.charAt(i));
				Integer n2= Character.getNumericValue(sb2.charAt(i));
				Integer ris=n1+n2+riporto;  // faccio la somma dei due numeri tenendo conto del riporto
				if (ris>=10 && i!=0) {
					riporto=1;   // il riporto nelle addizioni in colonna se e' presente sara' sempre uguale a 1
					sb1.setCharAt(i, ris.toString().charAt(1)); // uso lo StringBuilder sb1 per mettere il risultato nelle parti gia' utilizzate per calcolare la somma
				}
				else if (ris>=10 && i==0) { 
					sb1.setCharAt(i, ris.toString().charAt(1));
					sb1.insert(0, '1');
				}
				else {  // caso in cui non ci sia il riporto
					riporto=0;
					sb1.setCharAt(i, ris.toString().charAt(0));
				}
		
			}// for
			return new BigIntLL(sb1.toString());
		}// if
		
		if (sb1.length()<sb2.length()) {   // caso in cui il primo BigInt abbia meno cifre del secondo
			StringBuilder park=sb1; 		// inverto gli StringBuilder
			sb1=sb2; sb2=park;
		}
        // algoritmo in caso sb1>sb2 o con l'operazione precedente anche sb1<sb2
		Integer riporto=0;     
		String s=sb1.substring(0, sb1.length()-sb2.length()); // mi salvo la parte di BigInt in più, da aggiungere alla fine della somma
		sb1.delete(0,sb1.length()-sb2.length());
		for (int i=sb2.length()-1;i>=0;i--) {
			Integer n1= Character.getNumericValue(sb1.charAt(i));
			Integer n2= Character.getNumericValue(sb2.charAt(i));
			Integer ris=n1+n2+riporto;
			if (ris>=10) {
				riporto=1;
				sb1.setCharAt(i, ris.toString().charAt(1));
			}
			else {
				riporto=0;
				sb1.setCharAt(i, ris.toString().charAt(0));
			}
		}// for
		if (riporto==1)// aggiungo alla fine la parte salvata in precedenza , se c'e' riporto incremento il valore di questa porzione
			sb1.insert(0, new BigIntLL(s).incr().value()); 
		else
			sb1.insert(0, new BigIntLL(s).value()); // altrimenti la inserisco normalmente
		return new BigIntLL(sb1.toString());
		}// add
	
	public BigInt sub( BigInt s ){ //ritorna un BigInt con la differenza tra this e d; atteso this>=d
		if (this.compareTo(s)==-1)
			throw new IllegalArgumentException("Il BigInt da sottrarre deve essere <= dell'altro!");
		
		StringBuilder sb1=new StringBuilder(50);  // StringBuilder per this
		StringBuilder sb2=new StringBuilder(50);    // StringBuilder per a
		Iterator<Integer> i1=iterator();     // Iterator per this
		Iterator<Integer> i2=s.iterator();   // Iterator per a
		
		while (i1.hasNext()) {
			sb1.append(i1.next());    // caricamento di this su sb1
		}
		
		while (i2.hasNext()) {
			sb2.append(i2.next());   // caricamento di a su sb2
		}
		
		StringBuilder sb3= new StringBuilder(30);  // StringBuilder per il risultato
		// in questo caso non è ammissibile il fatto che il BigInt da sottrarre abbia più cifre dell'altro (sb1<sb2) NO
		
		if (sb1.length()==sb2.length()) {  // caso in cui i due BigInt abbiano lo stesso numero di cifre
			for (int i=sb1.length()-1;i>=0;i--) {
				Integer n1= Character.getNumericValue(sb1.charAt(i));
				Integer n2= Character.getNumericValue(sb2.charAt(i));
				Integer n3= n1-n2; // eseguo la sottrazione rispettivamente tra i due numeri presi in questione dal ciclo for
				if (n3<0) { // caso in cui il numero calcolato sia negativo
					n3+=10;  
					Integer a=Character.getNumericValue(sb1.charAt(i-1))-1;
					sb1.setCharAt(i-1, a.toString().charAt(0));  // decremento il prossimo numero, il quale mi ha prestato una cifra per poter effettuare quel +10
				}
				if (n3==0 && i==0 ) // caso in cui il numero calcolato sia 0 e siamo all'ultima iterazione del ciclo, ritorna direttamente il BigInt
					return new BigIntLL(sb3.toString());
				sb3.insert(0, n3);// in ogni caso inserisco il numero risultante
				
			}//for
		}
		else { // caso in cui sb1>sb2
			String st=sb1.substring(0, sb1.length()-sb2.length());  // mi salvo la porzione in piu' come effettuato nella add
			sb1.delete(0,sb1.length()-sb2.length());   // elimino la porzione appena salvata in modo che i due StringBuilder abbiano la stessa lunghezza
			for (int i=sb2.length()-1;i>=0;i--) {
				Integer n1= Character.getNumericValue(sb1.charAt(i));
				Integer n2= Character.getNumericValue(sb2.charAt(i));
				Integer n3= n1-n2;
				
				if (n3<0 && i!=0) {
					n3+=10;
					
					sb3.insert(0, n3);
					Integer a=Character.getNumericValue(sb1.charAt(i-1))-1;
					sb1.setCharAt(i-1, a.toString().charAt(0));
				}
				else if(n3<0) {
					n3+=10;
					sb3.insert(0, n3);
					st=new BigIntLL(st).decr().value();
				}
				else
					sb3.insert(0, n3);
			}// for
			String regex="[0]*";  // se ci sono degli zeri rimanenti dalla sottrazione nella parte sinistra del numero in questo modo li ometto
			if (!st.matches(regex))
				sb3.insert(0, st);
		}
		
		return new BigIntLL(sb3.toString());
	}// sub
	
	
	public ListIterator<Integer> iterator(){ return lista.listIterator();}
	
	public ListIterator<Integer> iterator (int i) {return lista.listIterator(i);}
	
	public BigInt mul (BigInt m) {  
		StringBuilder sb1=new StringBuilder(50); // stringBuilder di appoggio
		ListIterator<Integer> i1=iterator(this.length()); // faccio partire i due listIterator dalla fine 
		ListIterator<Integer> i2=m.iterator(m.length());
		BigInt ris= new BigIntLL(); // creo un BigInt che mi servirà per salvare di volta in volta il risultato
		int zeri=0;// variabile che serve per gestire la quantità di spazi da lasciare nelle addizioni tra i sub-prodotti
		int riporto=0;
		while (i2.hasPrevious()) { 
			Integer a=i2.previous();
			i1=iterator(this.length());
			for (int i=0;i<zeri;i++)
				sb1.append("0");
			while (i1.hasPrevious()) {
				Integer b=i1.previous();
				Integer p=a*b+riporto;  // eseguo la moltiplicazione numero per numero tenendo conto del riporto
				riporto=0;
				if (p>=10) {
					String s=String.valueOf(p);
					sb1.insert(0,(s.charAt(s.length()-1)));
					riporto=Integer.parseInt(s.substring(0, s.length()-1));  // salvo il riporto che in questo caso puo' essere anche diverso da 1
				}
				else sb1.insert(0,p); //inserisco nello stringBuilder il risultato del prodotto
			}//while interno
		if (riporto!=0)  // se alla fine del ciclo è ancora presente un prodotto lo inserisco all'inizio dello stringBuilder di appoggio
			sb1.insert(0 ,riporto);
		riporto=0;  // annullo il riporto per le prossime iterazioni
		BigInt tmp= new BigIntLL(sb1.toString()); // creo un BigInt sulla base dello stringBuilder e lo sommo al risultato precendente
		ris=tmp.add(ris);
		sb1.delete(0, sb1.length());// resetto lo stringBuilder per le prossime iterazioni
		zeri++; 
		}//while esterno
		return ris;
	}//mul
	
	
	public static void main(String args[]) {
		
		BigInt n1= new BigIntLL("2");
		BigInteger b= new BigInteger("2");	
		System.out.println("BigInteger:  2^128= "+b.pow(128));
		System.out.println("BigInt:  2^128= "+n1.pow(128));
	
		
	}//main

}// BigIntLL
