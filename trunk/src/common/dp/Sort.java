package common.dp;

/**
 * http://www.careercup.com/question?id=373367
 * 
 * Given n elements, sort the elements. Here, only one operation is permitted
 * decreaseValue.. Note that you cannot swap the values.. output should be a
 * sorted list.. if input is 4 5 2 1 3 output is 3 3 3.. There can be many
 * answers.. Give the optimum solution with minimum cost. where as cost is the
 * sum of decreased amounts.. for this example, 4,5 are decreased by 1 and 2.. 2
 * is decreased by 2.. 1 is decreased by 1..
 * 
 * @author Jason Huang
 * 
 */
public class Sort {
	
	final static int V = 6;

	public static void main(String[] args){
		
		int[] in = {4, 5, 2, 1, 3};
		
		System.out.println(sort(in));
		
		for(int i = 0; i >= 0; i ++);
	}

	static int sort(int[] arr) {
		
		int[][] dp = new int[arr.length + 1][V + 1];
		
		for(int j = 0; j <= V; j ++){
			dp[0][j] = (j > arr[0]) ? arr[0] : arr[0] - j;
		}
		
		for(int i = 1; i < arr.length; i++){
			
			int min = dp[i-1][0];
			dp[i][0] = arr[i] + dp[i-1][0];
			
			for(int j = 1; j <= V; j ++){
				
				min = Math.min(min, dp[i-1][j]);
				
				int cost = (j <= arr[i]) ? arr[i] - j : arr[i];
				dp[i][j] = cost + min;
			}
		}
		
		int ans = dp[arr.length - 1][0];
		
		for(int j = 1; j <= V; j ++){
			ans = Math.min(ans, dp[arr.length - 1][j]);
		}
		
		return ans;
	}
}
