package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Sudoku extends Backtracking<Integer[], Integer> {

	public int[][] board= new int[9][9];
	private boolean[][] bool= new boolean[9][9]; 
	public int numSol=0, numMaxSol=10;
	public LinkedList<int[][]> listaSol=new LinkedList<>();
	
	public Sudoku() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				board[i][j]=0;
				bool[i][j]=false;
			}
		}
	}//costruttore 
	public Sudoku(int [][] mat) {  // utile nell'interfaccia grafica
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				board[i][j]=mat[i][j];
				if(mat[i][j]!=0)
					bool[i][j]=true;
				else 
					bool[i][j]=false;
			}//for interno
		}//for esterno
	}//costruttore passando come parametro una matrice di interi
	
	@Override
	protected boolean assegnabile(Integer[] p, Integer s) {
		//controllo riga
		for(int i=0; i<board[0].length; i++)
			if(board[p[0]][i]==s) return false;
		
		for(int i=0; i<board[1].length; i++)
			if(board[i][p[1]]==s) return false;
		
		Integer[] list= trovaMat(p);
		for(int i=list[0]; i<=list[0]+2; i++)
			for(int j=list[1]; j<=list[1]+2; j++)
				if(board[i][j]==s) return false;
		return true;
	}//assegnabile
	
	private Integer [] trovaMat(Integer [] p) {
		Integer [] coppia= new Integer[2];
		if (p[0]<3 && p[1]<3) {
			coppia[0]=0; coppia[1]=0;
			return coppia;
		}
		else if (p[0]<3 && p[1]<6) {
			coppia[0]=0; coppia[1]=3;
			return coppia;
		}
		else if (p[0]<3 && p[1]<9) {
			coppia[0]=0; coppia[1]=6;
			return coppia;
		}
		
		if (p[0]>2 && p[0]<6 && p[1]<3) {
			coppia[0]=3; coppia[1]=0;
			return coppia;
		}
		else if(p[0]>2 && p[0]<6 && p[1]<6) {
			coppia[0]=3; coppia [1]=3;
			return coppia;
		}
		else if (p[0]>2 && p[0]<6 && p[1]<9) {
			coppia[0]=3; coppia[1]=6;
			return coppia;
		}
		if (p[0]>5 && p[1]<3) {
			coppia[0]=6; coppia[1]=0;
			return coppia;
		}
		else if (p[0]>5 && p[1]<6) {
			coppia[0]=6; coppia[1]=3;
			return coppia;
		}
		else if (p[0]>5 && p[1]<9) {
			coppia[0]=6; coppia[1]=6;
			return coppia;
		}
		return coppia;
	}//trovaMat

	@Override
	protected void assegna(Integer[] p, Integer s) {
		if (board[p[0]][p[1]]==0)
		board[p[0]][p[1]] = s;
	}//assegna

	@Override
	protected void deassegna(Integer[] ps, Integer s) {
		board[ps[0]][ps[1]] = 0;
	}//deassegna

	@Override
	protected void scriviSoluzione(Integer[] p) {
		numSol++;
		int [][] copy= new int [9][9];
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				copy[i][j]=board[i][j];
			}
		}//for esterno
		listaSol.add(copy);
	}//scriviSoluzione

	@Override
	protected List<Integer[]> puntiDiScelta() {
		ArrayList<Integer[]> ps= new ArrayList<>();
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++) {
				if (bool[i][j]==false) {
				Integer[] coppia= new Integer[2];
				coppia[0]= i;
				coppia[1]= j;
				ps.add(coppia);
				}//if
			}//for
		return ps;
	}//puntiDiScelta

	@Override
	protected Collection<Integer> scelte(Integer[] p) {
		ArrayList<Integer> list= new ArrayList<>();
		for(int i=1; i<=9; i++)
			if(assegnabile(p,i)) 
				list.add(i);
		return list;
	}//scelte

	@Override
	protected void risolvi() {
		Integer[] l= {0,0};
		tentativo(puntiDiScelta(), l);
	}//risolvi
	
	@Override
	protected boolean esisteSoluzione(Integer[] p) {
		return p[0]==board.length-1 && p[1]== board.length-1;
	}//esisteSoluzione
	
	@Override
	protected boolean ultimaSoluzione(Integer[] p) {
		return numSol>numMaxSol;
	}//ultimaSoluziome
	
	public void imposta( int i, int j , int v) {
		Integer [] test= {i,j};
		if (assegnabile(test,v) && v<10 && v>0) {
			board[i][j]=v;
			bool[i][j]=true;
		}
		else throw new IllegalArgumentException("Valore non Assegnabile!");
	}//imposta
	
	
	public static void main(String[] args) {
		Sudoku s= new Sudoku();
		s.risolvi();
		System.out.println(Arrays.deepToString(s.listaSol.get(0)));
	}//main
	
}//Sudoku
