package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Round1BC2 {
	
	static List<Long> list1, list2;
	static int target;

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"C-large-practice.in")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {
			
			int N = scanner.nextInt();
			long[] sArr = new long[N];
			
			for(int i = 0; i < N; i ++){
				sArr[i] = scanner.nextLong();
			}

			Arrays.sort(sArr);
			
			final int V = 1000007;
			int[][] dp = new int[N + 1][V];
			boolean[][] marks = new boolean[N + 1][V];
			
			
			int n = 1, v = 0;
			
			found:
			for(v = 1; v < V; v ++){

				for( n = 1; n <= N; n ++){
					
					long c = sArr[n-1];
					
					dp[n][v] = dp[n-1][v];
					
					if(v - c == 0){
						dp[n][v] += 1;
					} else if(v - c > 0){
						dp[n][v] += dp[n-1][v - (int)c];
					}
					
					if(v == c || (v - c > 0 && dp[n-1][v - (int)c] > 0)){
						marks[n][v] = true;
					}
					
					if(dp[n][v] >= 2){
						
//						System.out.println("n = " + n);
//						System.out.println("v = " + v);
						break found;
					}
				}
			}
			
			
			int nn = n - 1;
			int vv = v;
			
			if(n <= N && v < V){
				list1 = new ArrayList<Long>();
				while(n > 0 && dp[n][v] > 0){
					
					if(marks[n][v]){
						list1.add(sArr[n-1]);
						v -= sArr[n-1];
					} 
					n --;
				}
				
				list2 = new ArrayList<Long>();
				while(nn >= 0 && dp[nn][vv] > 0){
					
					if(marks[nn][vv]){
						list2.add(sArr[nn-1]);
						//System.out.print(sArr[nn] + ", ");
						vv -= sArr[nn-1];
					}
					nn --;
				}
				
				out.format("Case #%d:\n", t);

				out.format("%s\n", joinList(list1, " "));
				out.format("%s\n", joinList(list2, " "));

			} else {
				out.format("Case #%d: Impossible\n", t);
				
			}
			
			
//			if(b){
//				workspace.clear();
//				search2(workspace, sArr, 0, 0);
//				out.format("Case #%d:\n", t);
//
//				out.format("%s\n", joinList(list1, " "));
//				out.format("%s", joinList(list2, " "));
//				
//			} else {
//				out.format("Case #%d: Impossible", t);
//				
//			}
			
			
//			
//			for(int i = 1; i < N; i ++){
//				out.format(" %f", result[i]);
//			}
			

		}

		scanner.close();
		out.close();
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
