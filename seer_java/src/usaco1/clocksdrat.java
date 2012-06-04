package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: clocks
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class clocksdrat {

	static final int N = 9;
	static int[][] moves;
	static Integer[] result;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		
		Clocks clk = new Clocks();
		
		StringTokenizer st = new StringTokenizer(br.readLine() + " " +
				br.readLine() + " " +
				br.readLine() );
		

		clk.data = new int[N];
		for(int i = 0; i < N; i ++){
			clk.data[i] = Integer.parseInt(st.nextToken());
		}
		
		String[] rawMoves = {
				"ABDE",
				"ABC",
				"BCEF",
				"ADG",
				"BDEFH",
				"CFI",
				"DEGH",
				"GHI",
				"EFHI",
		};
		
		moves = new int[rawMoves.length][];
		
		for(int i = 0; i < moves.length; i ++){
			moves[i] = new int[rawMoves[i].length()];
			
			for(int j = 0; j < moves[i].length; j ++){
				moves[i][j] = rawMoves[i].charAt(j) - 'A';
			}
		}
		
		// list impl
		//LinkedList<Clocks> list = new LinkedList<Clocks>();
		
		// PriorityQueue impl
		PriorityQueue<Clocks> list = new PriorityQueue<Clocks>();
		
		
		// 
		done:
		while(!clk.isDone()){
			
			int lastMove = 0;
			
			if(!clk.moves.isEmpty()){
				lastMove = clk.moves.get( clk.moves.size() - 1 );
			}
			
			for(int i = lastMove; i < moves.length; i ++){
				
				Clocks nextClk = clk.move(moves[i], i);
				
				if(nextClk.isDone()){
					clk = nextClk;
					break done;
				} else {
					list.add(nextClk);
				}
			}
			
			//clk = list.removeFirst();
			clk = list.poll();
		}
		
		
		result = clk.moves.toArray(new Integer[0]);
		
		// print result
		if(result.length == 0){
			System.out.println("already done!");
			pw.println();
		} else {
			for(int i = 0; i < result.length - 1; i ++){
				pw.print((result[i] + 1) + " ");
				System.out.print((result[i] + 1) + " ");
			}
			pw.println((result[result.length - 1] + 1));
			System.out.println((result[result.length - 1] + 1));
		}
		
		System.out.println(list.size());
		
		pw.close();
		System.exit(0);

	}
	
	static class Clocks implements Comparable<Clocks> {
		
		int[] data;
		List<Integer> moves = new LinkedList<Integer>();
		
		boolean isDone(){
			for(int i : data){
				if(i != 12){
					return false;
				}
			}
			return true;
		}
		
		Clocks move(int[] move, int moveIndex){
			
			Clocks next = new Clocks();
			
			next.data = Arrays.copyOf(data, data.length);
			next.moves = new LinkedList<Integer>(moves);
			
			for(int i : move){
				
				next.data[i] += 3;
				
				if(next.data[i] > 12){
					next.data[i] -= 12;
				}
			}
			next.moves.add(moveIndex);
			
			return next;
		}

		@Override
		public int compareTo(Clocks arg0) {
			return hash() - arg0.hash();
		}
		
		int hash(){
			
			int s = 0;
			
			for(int i : data){
				s += (12 - i);
			}
			
			s /= 3;
			s *= 5;
			
			s += moves.size() * 3;
			
			return s;
		}
	}

}
