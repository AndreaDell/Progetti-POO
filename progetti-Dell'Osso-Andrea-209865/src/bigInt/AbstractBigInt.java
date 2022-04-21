package bigInt;

import java.util.Iterator;

public abstract class AbstractBigInt implements BigInt {

	public String toString() {
		StringBuilder sb= new StringBuilder(300);
		for (Integer x: this)
			sb.append(x);
		return sb.toString();
	}// toString
	
	public boolean equals (Object x) {
		if (!(x instanceof BigInt)) return false;
		if (this==x) return true;  // aliasing
		BigInt b=(BigInt) x;
		if (this.length()!=b.length()) return false;
		Iterator<Integer> i1= iterator();
		Iterator<Integer> i2=b.iterator();
		while (i1.hasNext()) {
			Integer m1=i1.next(), m2=i2.next();
			if (m1!=m2) return false;
		}
		return true;
	}// equals
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for (Integer x: this)
			h+=h*M+String.valueOf(x).hashCode();
		return h;
	}// hashCode
	
}// AbstractBigInt
