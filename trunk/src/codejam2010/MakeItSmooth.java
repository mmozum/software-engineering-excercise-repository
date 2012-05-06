package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MakeItSmooth {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader("A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int C = scanner.nextInt();

		for (int t = 1; t <= C; t++) {

			int N = scanner.nextInt();
			long K = scanner.nextLong();
			long B = scanner.nextLong();
			long T = scanner.nextLong();

			long[] X = new long[N]; // increasing order
			long[] V = new long[N];

			for(int i = 0; i < N; i++) {
				X[i] = scanner.nextLong();
			}
			for(int i = 0; i < N; i++) {
				V[i] = scanner.nextLong();
			}
			
			int numFast = 0;
			int numSlow = 0;
			int numSwap = 0;
			for(int i = N - 1; i >= 0 && numFast < K; i --){
				
				if(T * V[i] >= B - X[i]){
					numFast ++;
					numSwap += numSlow;
				} else {
					numSlow ++;
				}
			}
			
			String result = "IMPOSSIBLE";
			
			if(numFast == K){
				result = String.valueOf(numSwap);
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
