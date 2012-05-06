package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: sprime
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class sprime {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				"sprime.out")));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		int[] firstDigit = { 2, 3, 5, 7 };
		int[] laterDigit = { 1, 3, 7, 9 };

		Stack<String> stack = new Stack<String>();
		List<String> list = new ArrayList<String>();

		for (int first : firstDigit) {
			
			stack.push("" + first);

			while (!stack.empty()) {

				String str = stack.pop();

				if (str.length() == N) {
					list.add(str);
					continue;
				}

				int num = Integer.parseInt(str);

				for (int i = laterDigit.length - 1; i >= 0; i--) {
					int n = num * 10 + laterDigit[i];

					if (isPrime(n)) {
						stack.push("" + n);
					}
				}

			}
		}

		for(String s : list){
			System.out.println(s);
			pw.println(s);
		}
		
		pw.close();
		System.exit(0);

	}

	private static boolean isPrime(int pal) {

		for (int i = 2; i <= Math.sqrt(pal); i++) {
			if (pal % i == 0) {
				return false;
			}
		}
		return true;
	}

}
