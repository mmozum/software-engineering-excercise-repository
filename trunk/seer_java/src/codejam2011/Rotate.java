package codejam2011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rotate {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int N = scanner.nextInt();
			int K = scanner.nextInt();

			char[][] board = new char[N][];

			for (int i = 0; i < N; i++) {
				board[i] = scanner.next().toCharArray();

			}

			rotate(board);
			String result = judge(board, K);
			out.format("Case #%d: %s\n", t, result);
		}

		scanner.close();
		out.close();
	}

	private static String judge(char[][] board, int K) {

		int rWin = 0;
		int bWin = 0;

		done: for (int row = 0; row < board.length; row++) {

			for (int col = board[row].length - 1; col >= 0; col--) {

				char c = board[row][col];
				if (c == '.') {
					break; // this row is done
				}

				if (c == 'R' && rWin == 0) {
					rWin = test(board, row, col, c, K);
				} else if (c == 'B' && bWin == 0) {
					bWin = test(board, row, col, c, K);
				}

				if (rWin + bWin == 2) {
					break done;
				}
			}
		}

		String[] ans = { "Neither", "Blue", "Red", "Both" };

		return ans[(rWin << 1) | bWin];
	}

	private static int test(char[][] board, int row, int col, char c, int K) {

		int[][] directs = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 } };
		int N = board.length;

		next: for (int[] dir : directs) {

			int count = 0;
			for (int i = row, j = col; i >= 0 && i < N && j >= 0 && j < N; i += dir[0], j += dir[1]) {
				if (board[i][j] != c) {
					continue next;
				}

				if (++count == K) {
					return 1;
				}
			}
		}
		return 0;
	}

	private static void rotate(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			int j = board[i].length - 1, k = j;
			for (; j >= 0; j--) {
				if (board[i][j] != '.') {
					board[i][k] = board[i][j];
					k--;
				}
			}

			while (k >= 0) {
				board[i][k--] = '.';
			}
		}
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
