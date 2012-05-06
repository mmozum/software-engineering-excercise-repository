package codejam2011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SquareTiles {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int R = scanner.nextInt();
			int C = scanner.nextInt();

			char[][] board = new char[R][];

			for (int i = 0; i < R; i++) {
				board[i] = scanner.next().toCharArray();

			}

			boolean b = flip(board);
			
			if(!b){
				out.format("Case #%d:\n%s\n", t, "Impossible");
				
			} else {
				
				StringBuilder sb = new StringBuilder();
				
				for(int i = 0; i < R; i ++){
					sb.append(new String(board[i]) + "\n");
				}
				out.format("Case #%d:\n%s", t, sb.toString());
			}
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
