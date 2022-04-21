package valutaEspressione;

import java.util.Stack;
import java.util.StringTokenizer;

public class ValutaExpr {
	String expr;
	
	
	public ValutaExpr(String s) {
			this.expr=s; 
	}//costruttore
	
	public int risolvi() {
		StringTokenizer st= new StringTokenizer(expr,"+-*/()^%",true);
		return valutaEspressione(st);
	}//risolvi
	
	public int valutaEspressione(StringTokenizer st ) {
		Stack<Integer> operandi= new Stack<>();
		Stack<Character> operatori= new Stack<>();
		
		String OPERANDO="[0-9]*";
		String OPERATORI="[\\+\\-\\*\\/\\^]";
		while (st.hasMoreTokens()) {
			String tk=st.nextToken();
			if (tk.matches(OPERANDO)) {
				operandi.push(Integer.valueOf(tk));
			}
			else if (tk.matches(OPERATORI)) {
				Character c=tk.charAt(0);
				if (operatori.isEmpty() || primoMaggSec(c,operatori.peek()) ) {
					operatori.push(c);
				}
				else {
					Character op=c;
					try{
						op=operatori.pop();
						int o2=operandi.pop();
						int o1=operandi.pop();
						int res=0;
						
						switch (op) {
							case '+': res=o1+o2; break;
							case '-': res=o1-o2; break;
							case '*': res=o1*o2; break;
							case '/': res=o1/o2; break;
							case '%': res=o1%o2; break;
							case '^': res=(int) Math.pow(o1, o2); break;
						}
						operandi.push(res);
						operatori.push(c);						
					}catch(Exception e) {
						throw new IllegalArgumentException("Stringa malformata!");
				}//catch
				}//else
			}//else if operatori
			else if(tk.equals("(")) {
				operandi.push(valutaEspressione(st));
			}
			else if(tk.equals(")")) {
				break;
			}
			else {
				throw new IllegalArgumentException("Stringa Malformata!");
			}//else eccezione
			
		}//while token
		while (!operatori.isEmpty()) {
			try {
			Character op=operatori.pop();
			int o2=operandi.pop();
			int o1=operandi.pop();
			int res=0;
			switch (op) {
				case '^': res=(int) Math.pow(o1, o2); break;
				case '*': res=o1*o2; break;
				case '/': res=o1/o2; break;
				case '%': res=o1%o2; break;
				case '+': res=o1+o2; break;
				case '-': res=o1-o2; break;
			}
			operandi.push(res);
			
			
			
			
		}catch(Exception e) {
			throw new IllegalArgumentException("Stringa malformata!");
		}//catch
		}//while
		return operandi.pop();
	}//valutaEspressione
	
	private static boolean primoMaggSec(Character a, Character b) {
		if (a=='+' || a=='-') 
			return false;
		
		if (a=='*' || a=='/' || a=='%')
			switch(b) {
			case '^': return false;
			default: return true;
			}
		if (a=='^')
			return true;
		return false;
	}
	
	public static void main(String [] args) {
		ValutaExpr v= new ValutaExpr("28*2+55*3^(58/2+(2*9)+(58/2)-74)");
		System.out.println(v.risolvi());
		
	}//main
	
	
}//ValutaEspr
