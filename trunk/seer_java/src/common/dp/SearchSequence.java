package common.dp;
public class SearchSequence {

	static String min(String str, String word) {
		int n = str.length();
		int m = word.length();

		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++)
			dp[i][m] = 0;

		for (int j = 0; j < m; j++)
			dp[n][j] = n + 1;

		for (int i = n - 1; i >= 0; i--)
			for (int j = m - 1; j >= 0; j--) {
				if (str.charAt(i) == word.charAt(j)) {
					dp[i][j] = 1 + dp[i + 1][j + 1];
				} else {
					dp[i][j] = 1 + dp[i + 1][j];
				}
			}

		String ans = "none";
		int min = n + 1;

		for (int i = 0; i < n; i++) {
			if (dp[i][0] < min) {
				min = dp[i][0];
				ans = str.substring(i, i + dp[i][0]);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		String str = "992134923034";
		String word = "234";
		System.out.println(min(str, word));

		str = "12314102312";
		word = "12312";
		System.out.println(min(str, word));
	}

}
