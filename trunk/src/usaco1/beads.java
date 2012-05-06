package usaco1;

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
		BufferedReader br = new BufferedReader(new FileReader("beads.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				"beads.out")));

		int N = Integer.parseInt(br.readLine());
		String beadsStr = br.readLine();

		String beadsStr2 = beadsStr + beadsStr;

		Hashtable<Integer, Integer> forward = new Hashtable<Integer, Integer>();
		Hashtable<Integer, Integer> backward = new Hashtable<Integer, Integer>();

		int start = 0;

		while (start < beadsStr2.length()) {
			char color = beadsStr2.charAt(start);
			int current = start;

			while (current < beadsStr2.length() && current - start < N) {

				if (beadsStr2.charAt(current) == color
						|| beadsStr2.charAt(current) == 'w') {
					current++;
				} else if (color == 'w') {
					color = beadsStr2.charAt(current);
					current++;
				} else {
					break;
				}
			}

			if (!forward.containsKey(start % N)
					|| forward.get(start % N) < (current - start)) {
				forward.put(start % N, current - start);
			}
			if (!backward.containsKey(current % N)
					|| backward.get(current % N) < (current - start)) {
				backward.put(current % N, current - start);
			}

			// look for next segment
			color = beadsStr2.charAt(start);
			current = start;
			while (current < beadsStr2.length()
					&& beadsStr2.charAt(current) == color) {
				current++;
			}
			start = current;
		}

		int result = 0;
		for (Integer k : forward.keySet()) {

			if (backward.containsKey(k)) {

				System.out.println(k + ":" + forward.get(k) + " - "
						+ backward.get(k));
				int sum = forward.get(k) + backward.get(k);
				if (sum > result) {
					result = sum;
				}
			}
		}

		if (result > N) {
			result = N;
		}
		System.out.println(result);

		pw.println(result);

		pw.close();
		System.exit(0);

	}

}
