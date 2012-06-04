package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Round1BA {
	
	static class Item {
		int cost1;
		int cost2;
		int level;
		int status;
		
		Item(int l, int c1, int c2){
			level = l;
			cost1 = c1;
			cost2 = c2;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {
			
			int N = scanner.nextInt();
			int[] sArr = new int[N];
			
			double sum = 0;
			
			for(int i = 0; i < N; i ++){
				sArr[i] = scanner.nextInt();
				
				sum += sArr[i];
			}
			
			double[] result = new double[N];
			
			for(int i = 0; i < N; i ++){
				
				double lo = 0;
				double hi = 1;
				
				while(hi - lo > 1e-7){
					
					double mi = (lo + hi) / 2;
					
					double score = sArr[i] + sum * mi;
					
					double totalY = 0;
					
					for(int j = 0; j < N; j ++){
						if(j == i){
							continue;
						}
						
						double y = (score - sArr[j]) / sum;
						
						if(y < 0){
							y = 0;
						} else if(y > 1){
							y = 1;
						}
						
						totalY += y;
					}
					
					if(totalY > 1 - mi){
						hi = mi;
					} else {
						lo = mi;
					}
				}
				
				result[i] = lo * 100;
			}
			
			out.format("Case #%d: %s", t, joinArray(result, " "));
			
			out.println("");

		}

		scanner.close();
		out.close();
	}

	static String joinArray(double[] arr, String sep){
		
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

		public void format(String format, Object... args) {
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
