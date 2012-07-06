package common.dp;

/**
 * Count how many ways are there to partition an integer.
 * 
 * For example: 4 can be partitioned in 5 ways:
 * 1 + 1 + 1 + 1
 * 1 + 1 + 2
 * 1 + 3
 * 2 + 2
 * 4
 * @author Jason Huang
 *
 */
public class IntegerPartition {
	
	// original version with space and time to be both O(N2)
	static int howMany(int n){
		
		int[][] dp = new int[n + 1][n + 1];
		dp[0][0] = 1;
		
		for(int i = 0; i <= n; i ++){
			for(int j = 1; j <= n; j ++){
				dp[i][j] = dp[i][j-1];
				if(i >= j){
					dp[i][j] += dp[i-j][j];
				}
			}
		}
		
		return dp[n][n];
	}
	
	// space O(N)
	static int howMany2(int n){
		
		int[] dp = new int[n + 1];
		dp[0] = 1;
		
		for(int j = 1; j <= n; j ++){
			for(int i = j; i <= n; i ++){
				dp[i] += dp[i-j];
			}
		}
		
		return dp[n];
	}

	public static void main(String[] args) {
//		System.out.println(howMany(4));
//		System.out.println(howMany2(4));
		
		for(int i = 1; i < 100; i ++){
			assert(howMany(i) == howMany2(i));
		}
		System.out.println();

	}

}
