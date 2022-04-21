package polinomi;
import java.util.*;
// provare a fare la classe concreta basata su set e su map  TreeSet e TreeMap
public class PolinomioLL extends PolinomioAstratto {
	LinkedList<Monomio> lista= new LinkedList<>();
	// non inseriamo costruttore, quindi ci avvaliamo del costruttore di default
	
	@Override
	public PolinomioLL crea() { return new PolinomioLL(); }  //covarianza del tipo di ritorno
	
	@Override
	public int size() { return lista.size();}
	@Override
	public Iterator<Monomio> iterator(){
		return lista.iterator();
	}// iterator
	
	public void add(Monomio m) {
		//si mantiene la lista continuamente ordinata per gradi decrescenti
				if( m.getCoeff()==0 ) return;
				ListIterator<Monomio> lit=lista.listIterator();
				boolean flag=false; //true quando m e' sistemato
				while( lit.hasNext() && !flag ){
					Monomio m1=lit.next();
					if( m.equals(m1) ){//monomi simili
						Monomio m2=m.add(m1);
						if( m2.getCoeff()!=0 ){
							lit.set( m2 );
						}
						else lit.remove();
						flag=true;
					}
					else if( m1.compareTo(m)>0 ){
						lit.previous();
						lit.add(m);
						flag=true;
					}
				}//while
				if( !flag ) lit.add( m );
	}// add
	public static void main(String[] args) {
		/*
		Polinomio p1= new PolinomioSet();
		p1.add(new Monomio(-3,0));
		p1.add(new Monomio(4,2));
		p1.add(new Monomio(-7,5));
		p1.add(new Monomio(-9,2));
		p1.add(new Monomio(3,5));
		p1.add(new Monomio(1,8));
		System.out.println(p1);
		Polinomio pd=p1.derivata();
		System.out.println("Derivata="+pd);
		*/
		
		Polinomio p1=new PolinomioLL();
		Polinomio p2= new PolinomioLL();
		p1.add(new Monomio(3,5));
		p2.add(new Monomio(4,2));
		LinkedList<Polinomio> lista= new LinkedList<>();
		lista.add(p1);  lista.add(p2);
		Polinomio moltiplicazione= new PolinomioLL();
		moltiplicazione.add(new Monomio(1,0));
		for (Polinomio x : lista) {
			moltiplicazione=moltiplicazione.mul(x);
		}
		System.out.println(moltiplicazione);
	}
	
	
	
	
	
}// PolinomioLL
