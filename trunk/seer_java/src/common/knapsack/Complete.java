package common.knapsack;

import java.util.Arrays;

public class Complete {


	public static void main(String[] args) {
		
		//int[] in = {1, 5, 10, 25, 100, 500, 1000, 2000, 5000, 10000};
		int[] in = {1, 5, 10, 25, 100, 500, 1000, 2000};
		//System.out.println(howMany(in, 5));
		//System.out.println(howMany2(in, 55555));
		System.out.println(howMany2(in, 555555));
		System.out.println(howMany2(in, 50000000));
		//System.out.println(howMany2(in, 100000000));
	}

	//How many ways to pack
	
	public static int howMany(int[] arr, int n){
		
		int[] A = new int[arr.length + 1];
		System.arraycopy(arr, 0, A, 1, arr.length);
		
		int[] dp = new int[n + 1];
		
		for(int i = 1; i < A.length; i ++){
			for(int j = A[i]; j <= n; j ++){
				
				dp[j] += (j == A[i]) ? 1 : dp[j - A[i]];
			}
		}
		
		return dp[n];
	}
	
	public static long howMany2(int[] arr, int n){
		long[] dp = new long[n + 1];
		
		for(int i : arr){
			for(int j = i; j <= n; j ++){
				dp[j] += (j==i) ? 1 : dp[j-i];
			}
		}
		
		return dp[n];
	}
	
	
	
	
	
	
	
	
	
	
	
}
