package polinomi;
import java.util.Iterator;

public interface Polinomio extends Iterable<Monomio> {
	// grazie a iteratore e factory rimangono astratti solo add(Monomio) , crea() , iterator()
	default int size() {
		int c=0;
		for (Iterator <Monomio> it=iterator();it.hasNext();it.next(),c++);
		return c;
	}//size
	
	void add(Monomio m);
	Polinomio crea(); // metodo factory    metodo non ancora creato
	
	default Polinomio add(Polinomio p) {
		Polinomio somma=crea();
		for (Monomio m:this) somma.add(m); // usa add di monomio
		for (Monomio m:p) somma.add(m);
		return somma;
	}//add 
	
	default Polinomio mul(Monomio m) {
		Polinomio prodotto=crea();
		for (Monomio m1:this) 
			prodotto.add(m1.mul(m));
		return prodotto;
	}// mul Monomio
	
	
	default Polinomio mul(Polinomio p) {
		Polinomio prodotto=crea();
		for (Monomio m:this)
			prodotto=prodotto.add(p.mul(m));
		return prodotto;
	}//mul Polinomio
	
	default Polinomio derivata() {
		Polinomio d=crea();
		for( Monomio m: this ) {
			if (m.getGrado()==0)
				m.add(new Monomio(m.getCoeff(),0));
			else {
			d.add( new Monomio( m.getCoeff()*m.getGrado(), m.getGrado()-1 ) );
			}
		}
		return d;
	}//derivata
	
	default double valore(double x) {
		double v=0.0D;
		for (Monomio m: this)
			v=v+m.getCoeff()*Math.pow(x, m.getGrado());
		return v;
	}// valore

}
