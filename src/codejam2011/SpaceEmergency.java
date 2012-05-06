package codejam2011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SpaceEmergency {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int L = scanner.nextInt();
			long tt = scanner.nextLong();
			int N = scanner.nextInt();
			int C = scanner.nextInt();

			int[] A = new int[C];
			
			for (int i = 0; i < C; i++) {
				A[i] = scanner.nextInt() * 2;

			}
			
			long start = 0;
			float [] gains = new float[N];
			
			for(int i = 0; i < N; i ++){
				int d = A[i % C];
				
				if(start + d <= tt){
					gains[i] = 0;
				} else if(start >= tt) {
					gains[i] = d / 2;
				} else {
					gains[i] = (start + d - tt) / 2f;
				}
				
				start += d;
			}
			
			double overall = start;
			
			Arrays.sort(gains);
			
			for(int i = 1; i <= L && N - i >= 0; i ++){
				overall -= gains[N - i];
			}
			

			out.format("Case #%d: %s\n", t, String.valueOf((long)overall));
		}

		scanner.close();
		out.close();
	}



	private static boolean flip(char[][] board) {
		
		int M = board.length;
		int N = board[0].length;

		for(int i = 0; i < M; i ++){
			for(int j = 0; j < N; j ++){
				if(board[i][j] == '#'){
					if(i + 1 >= M || j + 1 >= N || board[i+1][j] != '#' || board[i][j+1] != '#' || board[i+1][j+1] != '#'){
						return false;
					}
					
					board[i][j] = '/';
					board[i][j+1] = '\\';
					board[i+1][j] = '\\';
					board[i+1][j+1] = '/';
				}
			}
		}
		
		return true;
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
