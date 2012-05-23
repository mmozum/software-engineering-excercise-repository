package common.dp;

import java.util.List;

/**
 * http://www.careercup.com/question?id=3655770
 * 
 * Given an input array of integers of size n, and a query array of integers of
 * size k, find the smallest window of input array that contains all the
 * elements of query array and also in the same order.
 * 
 * @author Jason Huang
 * 
 */
public class LCSVariation {

	final static int V = 6;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = { 2, 1, 0, 3, 4, 5, 2, 3, 4 };
		int b[] = { 2, 3, 4 };
		int j = 0;
		int start = 0;
		int i = 0;

		for (; i < a.length; i++) {

			if (a[i] == b[j]) {
				if (j == 0)
					start = i;

				j++;

				if (j == b.length)
					break;
			}

		}
		if (j == b.length) {
			System.out.println("Found longest sequence : start = " + start
					+ " end =" + i);
			int k = b.length - 1;
			int end = i;
			while (k >= 0) {
				if (a[i] == b[k]) {
					i--;
					k--;
				} else {
					i--;
				}
			}
			System.out.println("Found minimum window : start = " + (i + 1)
					+ " end =" + end);
			for (int c = i + 1; c <= end; c++) {
				System.out.print(a[c] + ",");
			}
		}
	}

}
