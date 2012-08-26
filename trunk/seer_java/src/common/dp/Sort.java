package common.dp;

import j.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	final static int V = 100;

	public static void main(String[] args) {

//		int[] in = {4, 5, 2, 1, 3};
//
//		System.out.println(sort(in));
//		System.out.println(sort2(in));

		for (int i = 0; i < 100; i++) {
			int[] arr = Util.randomeArray(100, V);
			int a = sort(arr);
			int b = sort2(arr);
			
			if(a!=b){
				System.out.println(Arrays.toString(arr));
				System.out.printf("me = %d, he = %d",  a, b);
				break;
			}
		}
		
		System.out.println("done~");
	}

	/**
	 * Naive DP. Time and space complexity are both O(MN)
	 * where M is the max input element, N is the size of 
	 * input array.
	 * @param arr
	 * @return
	 */
	static int sort(int[] arr) {

		int[][] dp = new int[arr.length + 1][V + 1];

		for (int j = 0; j <= V; j++) {
			dp[0][j] = (j > arr[0]) ? arr[0] : arr[0] - j;
		}

		for (int i = 1; i < arr.length; i++) {

			int min = dp[i - 1][0];
			dp[i][0] = arr[i] + dp[i - 1][0];

			for (int j = 1; j <= V; j++) {

				min = Math.min(min, dp[i - 1][j]);

				int cost = (j <= arr[i]) ? arr[i] - j : arr[i];
				dp[i][j] = cost + min;
			}
		}

		int ans = dp[arr.length - 1][0];

		for (int j = 1; j <= V; j++) {
			ans = Math.min(ans, dp[arr.length - 1][j]);
		}

		return ans;
	}


	/**
	 * Improved DP: only consider value that appeared in the input array.
	 * Time complexity is O(MN) where M is the number of unique element,
	 * and N is the size of array. Note we always have M <= N.
	 * Space complexity is O(M).
	 * @param arr
	 * @return
	 */
	static int sort2(int[] arr) {
		
		Set<Integer> set = new HashSet<Integer>();
		for(int i : arr){
			set.add(i);
		}
		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		list.add(Integer.MAX_VALUE);
		
		final int M = list.size();
		int[] dp = new int [M];
		
		for(int i = 0; i < arr.length; i ++){
			
			int[] dp2 = Arrays.copyOf(dp, M);
			for(int j = 0; j < dp.length; j ++){
				dp2[j] += arr[i];
			}
			
			int best = Integer.MAX_VALUE;
			for(int j = 0; list.get(j) <= arr[i]; j++){
				best = Math.min(best, dp[j]);
				dp2[j] = Math.min(dp2[j], best + arr[i] - list.get(j));
			}
			
			dp = dp2;
		}
		
		int ret = dp[0];
		for(int i : dp){
			ret = Math.min(ret, i);
		}
		return ret;
	}
	
	/**
	 * This is another intersting idea.
	 * By philodoxos & darksteel
	 * 
	 * 对每个i，求把A[1..i]调整为 有序并且以A[i]结尾的最优值，具体做法就是枚举前一个“阶梯”的结尾j，
	 * 并把A[j+1..i]调整为跟A[i]一样，相当于调整到同一级“阶梯”上。 最后的结果应该是
	 * min{dp[i]+把A[i+1..n]都调整到A[i]的代价}，这里调整指的都是 大的往下减小的减为0。
	 * 
	 * dp[j] = min(dp[i] + cost[i][j]) for i < j and arr[i] <= arr[j]
	 * 
	 * Time and space complexity are O(N2) where N is the size of array.
	 */
	static int sort3(int[] arr0) {

		final int N = arr0.length + 1;
		int[] arr = Arrays.copyOf(arr0, N);
		arr[N-1] = Integer.MAX_VALUE;
		
		int[][] cost = new int[N][N];
		for(int j = 0; j < N; j ++){
			for(int i = j - 1; i >= 0; i --){
				if(arr[i] >= arr[j]){
					// reduce arr[i] to arr[j]
					cost[i][j] = cost[i+1][j] + arr[i] - arr[j];
				} else {
					// remove arr[i] completely
					cost[i][j] = cost[i+1][j] + arr[i];
				}
			}
		}
		
		int[] dp = new int[N];
		
		for(int j = 1; j < N; j ++){
			dp[j] = cost[0][j];
			
			for(int i = 0; i < j; i ++){
				if(arr[i] > arr[j]){
					continue;
				}
				dp[j] = Math.min(dp[j], dp[i] + cost[i+1][j]);
			}
		}
		
		return dp[N-1];
	}

}
