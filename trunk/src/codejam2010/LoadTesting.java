package codejam2010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class LoadTesting {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader("A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt(); scanner.nextLine();

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(scanner.nextLine());
			List<Integer> list = new ArrayList<Integer>(10);
			
			while(st.hasMoreTokens()){
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int num = 1;
			
			next:
			while(true){
				num ++;
				
				for(Integer i : list){
					if(!isHappy(num, i)){
						continue next;
					}
				}
				
				break;
			}

			out.format("Case #%d: %s\n", t, ""+num);
		}

		scanner.close();
		out.close();
	}

	private static boolean isHappy(int num, int base) {

		Set<Long> set = new HashSet<Long>();
		
		long sum = num;
		
		while(sum != 1 && !set.contains(sum)){
			
			set.add(sum);
			
			long newSum = 0;
			
			while(sum != 0){
				
				long a = sum % base;
				sum /= base;
				newSum += a * a;
			}
			
			sum = newSum;
		}
		
		return sum == 1;
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
