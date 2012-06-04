package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Round1CC {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {
			
			int N = scanner.nextInt();
			int M = scanner.nextInt();
			
			long[][] A = new long[N+1][2];
			long[][] B = new long[M+1][2];
			
			for(int i = 1; i <= N; i ++){
				A[i][0] = scanner.nextLong();
				A[i][1] = scanner.nextLong();
			}
			
			for(int i = 1; i <= M; i ++){
				B[i][0] = scanner.nextLong();
				B[i][1] = scanner.nextLong();
			}
			
			long[][] dp = new long[M+1][N+1];
			
			for(int i = 1; i <= M; i ++){
				for(int j = 1; j <= N; j ++){
					
					if(B[i][1] == A[j][1]){
						dp[i][j] = dp[i-1][j-1] + Math.min(B[i][0], A[j][0]);
					} else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					}
				}
			}
			out.format("Case #%d: %d\n", t, dp[M][N]);


		}

		scanner.close();
		out.close();
	}




	private static boolean search(boolean[] workspace, int[][] cArr, int i) {
		

		for(int j = 1; j < cArr[i].length; j ++){
			if(workspace[ cArr[i][j] ]){
				return true;
			}
			
			workspace[cArr[i][j]] = true;
			
			boolean b = search(workspace, cArr, cArr[i][j]);
			
			if(b){
				return true;
			}
		}
		
		
		return false;
	}




	static <E> String joinList(List<E> list, String sep){
		
		if(list == null || list.isEmpty()){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));
		
		Iterator<E> it = list.iterator();
		it.next();
		
		while(it.hasNext()){
			sb.append(sep).append(it.next());
		}
		
		return sb.toString();
	}
	
	static String joinArray(int[] arr, String sep){
		
		if(arr == null || arr.length == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		
		for(int i = 1; i < arr.length; i ++){
			sb.append(sep).append(arr[i]);
		}
		
		return sb.toString();
	}
	

	static class Output {

		PrintWriter pw;

		public Output(String filename) throws IOException {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		}

		public void print(String s) {
			pw.print(s);
			System.out.print(s);
		}

		public void println(String s) {
			pw.println(s);
			System.out.println(s);
		}

		public void println() {
			pw.println();
			System.out.println();
		}

		public void format(String format, Object... args) {
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
