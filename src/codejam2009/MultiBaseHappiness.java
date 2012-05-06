package codejam2009;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MultiBaseHappiness {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader("A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int L = scanner.nextInt();
			int P = scanner.nextInt();
			int C = scanner.nextInt();

			int endExp = 0;
			long start = L;
			while(start < P){
				start *= C;
				endExp ++;
			}
			
			start = 1;
			int result = 0;
			while(start < endExp){
				start *= 2;
				result ++;
			}
			
			out.format("Case #%d: %s\n", t, ""+result);
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
