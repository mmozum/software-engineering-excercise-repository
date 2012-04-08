/*
ID: jastina1
LANG: JAVA
TASK: beads
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class beads {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// BufferedReader br = new BufferedReader(new FileReader("beads.in"));
		// PrintWriter pw = new PrintWriter(new BufferedWriter(new
		// FileWriter("beads.out")));

		// int N = Integer.parseInt(br.readLine());
		// String beadsStr = br.readLine();
		String beadsStr = "wwwbbrwrbrbrrbrbrwrwwrbwrwrrb";

		beadsStr += beadsStr;

		Hashtable<Integer, Integer> forward = new Hashtable<Integer, Integer>();
		Hashtable<Integer, Integer> backward = new Hashtable<Integer, Integer>();

		int start = 0;
		int nextStart = 0;
		int current = 0;
		char color = beadsStr.charAt(start);

		while (current < beadsStr.length()) {

			start = nextStart;
			nextStart = -1;
			current = start;
			color = beadsStr.charAt(start);

			while (current < beadsStr.length()) {

				char c = beadsStr.charAt(current);

				if (c == color) {
					current++;
				} else {

					if (color == 'w') {
						color = c;
						nextStart = (nextStart == -1) ? current : nextStart;
						current ++;
						continue;
					}

					if (c == 'w') {
						nextStart = (nextStart == -1) ? current : nextStart;
						current++;
					} else {
						nextStart = (nextStart == -1) ? current : nextStart;
						break;
					}
				}
			}

			System.out.println(start + " # " + current + " * "
					+ (current - start));
			forward.put(start, current - start);
			backward.put(current, current - start);
		}

		int result = 0;
		for (Integer k : forward.keySet()) {

			if (backward.containsKey(k)) {

				System.out.println(forward.get(k) + " - " + backward.get(k));
				int sum = forward.get(k) + backward.get(k);
				if (sum > result) {
					result = sum;
				}
			}
		}

		System.out.println(result);
		// pw.println(result);
		// pw.close();
		System.exit(0);

	}

}
