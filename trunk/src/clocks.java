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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class clocks {

	static final int N = 9;
	static ArrayList<String> result = new ArrayList<String>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				"clocks.out")));

		StringTokenizer st = new StringTokenizer(br.readLine() + " "
				+ br.readLine() + " " + br.readLine());

		int[] data = new int[N];

		for (int i = 0; i < N; i++) {
			data[i] = (Integer.parseInt(st.nextToken()) / 3) % 4;
		}

		String[] rawMoves = { "ABDE", "ABC", "BCEF", "ADG", "BDEFH", "CFI",
				"DEGH", "GHI", "EFHI", };

		int[][] moves = new int[rawMoves.length][];

		for (int i = 0; i < moves.length; i++) {
			moves[i] = new int[rawMoves[i].length()];

			for (int j = 0; j < moves[i].length; j++) {
				moves[i][j] = rawMoves[i].charAt(j) - 'A';
			}
		}

		dfs(data, moves, 0, "");

		Collections.sort(result);
		String ans = result.get(0);
		
		for(int i = 0; i < ans.length() - 1; i ++){
			System.out.print(ans.charAt(i) + " ");
			pw.print(ans.charAt(i) + " ");
		}
		
		System.out.println(ans.charAt( ans.length() - 1));
		pw.println(ans.charAt( ans.length() - 1));
		
		pw.close();
		System.exit(0);

	}

	private static void dfs(int[] data, int[][] moves, int pMove, String movesSoFar) {
		
		
		if (isDone(data)) {
			result.add(movesSoFar);
			return;
		}
		
		if(pMove >= moves.length){
			return;
		}
		

		for (int i = 0; i <= 3; i++) {
			String newMove = movesSoFar;
			for(int j = 0; j < i; j ++){
				newMove += (pMove + 1);
			}
			move(data, moves[pMove], i);
			dfs(data, moves, pMove + 1, newMove);
			unmove(data, moves[pMove], i);
		}


	}

	private static void move(int[] data, int[] m, int n) {

		for (int i : m) {
			data[i] = (data[i] + n) % 4;
		}
	}

	private static void unmove(int[] data, int[] m, int n) {

		for (int i : m) {
			data[i] = (data[i] + 4 - n) % 4;
		}
	}

	private static boolean isDone(int[] data) {

		for (int i : data) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
