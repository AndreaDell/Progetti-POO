package bigInt;

import java.util.Iterator;
import java.util.ListIterator;

public interface BigInt extends Comparable<BigInt>,Iterable<Integer>{
	
	default String value() {  //ritorna il valore del BigInt sottoforma di stringa di caratteri
		StringBuilder sb=new StringBuilder(100);
		for (Integer x:this) 
			sb.append((String.valueOf(x)));
		return sb.toString();
	} //value
	
	default int length() { //ritorna il numero di cifre di questo BigInt
		int size=0;
		for (Integer x:this) size++;
		return size;
	}// length
	
	BigInt factory( int x );   // implementato in BigIntLL
	
	 default BigInt incr() {
		 return this.add(factory(1));
	 }// incr
	 
	default BigInt decr() {
		if (this.value().equals("0")) throw new IllegalArgumentException("Argomento invalido! (0)");
		 return this.sub(factory(1));
	 }//decr
	
	 BigInt add( BigInt a );
	 BigInt sub( BigInt s );
	 BigInt mul( BigInt m );
		
	default BigInt div( BigInt d ) {//ritorna il quoziente della divisione intera tra this e d; atteso this>=d
		if (this.compareTo(d)==-1)
			throw new IllegalArgumentException("this atteso >= d");
		BigInt ris= this;
		BigInt cnt= new BigIntLL(0);	
		while (ris.compareTo(d)>=0) {
			ris=ris.sub(d);
			cnt=cnt.incr();
		}//while
		return cnt;
	}//div
	
	default BigInt rem( BigInt d ) { //ritorna il resto della divisione intera tra this e d; atteso this>=d
		if (this.compareTo(d)==-1) throw new IllegalArgumentException("Il BigInt passato come parametro deve essere <= dell'altro!!");
		BigInt ris=this.div(d);
		BigInt prod=d.mul(ris);
		return this.sub(prod);
	}// rem
	
	default BigInt pow( int exponent ){//calcola la potenza this^exponent
		int cnt=0;
		BigInt ris=factory(1);
		while (cnt<exponent) {
			ris=(ris.mul(this));
			cnt++;
			}//while
		return ris;
		}//pow
		
	default int compareTo(BigInt o){
		Iterator<Integer> i1=iterator();
		Iterator<Integer> i2=o.iterator();
		if (this.length()>o.length()) return 1;
		else if (this.length()==o.length()) {
			while (i1.hasNext()) {
				Integer a1=i1.next();
				Integer a2=i2.next();
				if (a1>a2) return 1;
				else if (a1<a2) return -1;
			}
			return 0;
		}
		else return -1;
	}//compareTo

	ListIterator<Integer> iterator(int i);

}// BigInt
