package common.dp;

import java.util.Arrays;

/*
Given an input array A of integers of size n, and a query array B of
integers of size m, find the smallest window of input array that
contains all the elements of query array and also in the same order.

例如：
A[] = {1,9,3,4,12,13,9,12,21}
B[] = {9,12,21}
那么应该返回A[6..8] = {9,12,21}
*/
public class SearchSubArray {

	static int[] findMin(int[] A, int[] B){
		
		final int M = A.length;
		final int N = B.length;
		
		int[][] dp = new int[2][N + 1];
		int minLen = M + 1;
		int start = -1;
		
		Arrays.fill(dp[0], M + 1);
		dp[0][0] = 0;
		dp[1][0] = 1;
		
		for(int i = 1; i <= M; i ++){
			
			int a = i % 2;
			int b = (i-1) % 2;

			for(int j = 1; j <= N; j ++){
				
				if(A[i-1] == B[j-1]){
					dp[a][j] = dp[b][j-1] + 1;
				} else {
					dp[a][j] = dp[b][j] + 1;
				}
			}

			if(dp[a][N] < minLen){
				minLen = dp[a][N];
				start = i - minLen;
			}
		}

		
		if(start < 0){
			return new int[]{};
		}
		
		return Arrays.copyOfRange(A, start, start + minLen);
	}
	
	public static void main(String[] args) {
		int A[] = {1,9,3,4,12,13,9,12,21};
		int B[] = {9,12,21};
		System.out.println(Arrays.toString(findMin(A,B)));
	}

}
