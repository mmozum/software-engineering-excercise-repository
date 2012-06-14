package common.knapsack;

/**
 * Given an array of strings of 0s and 1s. X and Y are also given. Return the
 * maximum number of elements in a subset of the array elements which will X
 * number of zeroes and Y number of 1s when combined. For eg: if array[] = {"01
 * ", "10", "0", "110"} X=3, Y=2 Answer should be 3 since first 3 strings when
 * combined will give the required number of 0s and 1s.
 */
public class Knapsack2D {

	public static void main(String[] args) {
		String[] array = { "01", "10", "0", "110" };
		int X = 3, Y = 2;
		pack2d(array, X, Y);
	}

	private static void pack2d(String[] arr, final int X, final int Y) {

		final int N = arr.length;

		int[][][] dp = new int[N + 1][X + 1][Y + 1];

		for (int i = 0; i <= X; i++) {
			for (int j = 0; j <= Y; j++) {
				dp[0][i][j] = Integer.MIN_VALUE;
			}
		}
		
		dp[0][0][0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int x = 0; x <= X; x++) {
				for (int y = 0; y <= Y; y++) {
					String str = arr[i - 1];
					int num0 = countOccurrences(str, '0');
					int num1 = countOccurrences(str, '1');
					if (x < num0 || y < num1) {
						continue;
					}
					dp[i][x][y] = Math.max(dp[i - 1][x][y],	dp[i - 1][x - num0][y - num1] + 1);
				}
			}
		}

		int sol = Integer.MIN_VALUE;
		for (int i = 0; i <= N; i++) {
			sol = Math.max(sol, dp[i][X][Y]);
		}

		System.out.println(sol);

	}

	public static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

}
