package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PickingUpChicks {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader("A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int D = scanner.nextInt();
			int I = scanner.nextInt();
			int M = scanner.nextInt();
			int N = scanner.nextInt();

			int[] A = new int[N];

			for (int i = 0; i < N; i++) {
				A[i] = scanner.nextInt();
			}
			
			int[][] cost = new int [N][256];
			
			for(int i = 0; i < 256; i ++){
				
				// change
				int minCost = Math.abs(A[0] - i);
				
				// delete
				int localCost = D;
				
				if(localCost < minCost){
					minCost = localCost;
				}
				
				cost[0][i] = minCost;
			}
			
			
			for(int i = 1; i < N; i ++){
				for(int j = 0; j < 256; j ++){
					
					// delete
					int minCost = D + cost[i-1][j];
					
					// change
					int c0 = Math.abs(A[i] - j);

					if(M == 0){
						int localCost = c0 +
								cost[i-1][j];
						
						if(localCost < minCost){
							minCost = localCost;
						}
					} else {
						for(int k = 0; k < 256; k ++){
							int localCost = c0 + // change 
									Math.max(0, (Math.abs(k - j) - 1) / M) * I + // insert
									cost[i-1][k];
							
							if(localCost < minCost){
								minCost = localCost;
							}
						}
					}

					
					cost[i][j] = minCost;
				}
				
//				cost[i][256] = cost[i-1][256] + D;
			}

			int result = Integer.MAX_VALUE;
			
			for(int i = 0; i < 256; i ++){
				result = Math.min(result, cost[N-1][i]);
			}
			out.format("Case #%d: %s\n", t, result);
		}

		scanner.close();
		out.close();
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
		
		public void format(String format, Object...args){
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
