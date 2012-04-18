/*
ID: jastina1
LANG: JAVA
TASK: checker
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class checker {

	static int total = 0;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("checker.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				"checker.out")));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		List<String> list = new ArrayList<String>();
		
		
		int[] board = new int[N + 1];
		
		// helper arrays
		boolean[] col = new boolean[N + 1];
		boolean[][] diag = new boolean[2][3 * N];
		
		place(board, col, diag, pw, 1);
		
//		for(int i = 0; i < 3; i ++){
//			System.out.println(list.get(i));
//			pw.println(list.get(i));
//		}
		
		System.out.println(total);
		pw.println(total);
		
		pw.close();
		System.exit(0);

	}

	private static void place(int[] board, boolean[] col, boolean[][] diag,
			PrintWriter pw, int row) {

		int N = board.length - 1;
		
		for(int i = 1; i <= N; i ++){
			
			if(!col[i] && 
					!diag[0][row + i] && 
					!diag[1][board.length+1 - row + i]){
				
				col[i] = true;
				diag[0][row + i] = true;
				diag[1][board.length+1 - row + i] = true;
				
				board[row] = i;
				
				if(row == N){
					
					//if(list.size() < 3)
					//	list.add(join(board));
					if(total++ < 3){
					pw.println(join(board));
					System.out.println(join(board));
					}
					
				} else {
					place(board, col, diag, pw, row + 1);
				}
				
				col[i] = false;
				diag[0][row + i] = false;
				diag[1][board.length+1 - row + i] = false;
			}
		}
		
	}

	private static String join(int[] arr) {

		String s = "";
		for(int i = 1; i < arr.length - 1; i ++){
			s += arr[i] + " ";
		}
		
		s += arr[arr.length - 1];
		return s;
	}

}
