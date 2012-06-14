package common;

import java.util.Arrays;

import j.Util;

/**
 * Find the largest continuous subarray.
 * @author Jason Huang
 *
 */
public class Kadane {
	

	public static void main(String[] args) {
		
		final int size = 10;
		int[] arr = Util.generateRandomArray(size);
		for(int i = 0; i < arr.length; i ++){
			arr[i] -= 2 * size;
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(maximumSubarray(arr));
		System.out.println(maximumSubarray2(arr));

	}
	
	/**
	 * 1D version.
	 * @param arr
	 * @return 0 if all elements are negative
	 */
	public static int maximumSubarray(int[] arr){
		
		int maxSum = 0;
		int curSum = 0;
		
		for(int i : arr){
			curSum += i;
			if(curSum < 0){
				curSum = 0;
			} else if(curSum > maxSum) {
				maxSum = curSum;
			}
		}
		
		return maxSum;
	}
	
	/**
	 * 1D version.
	 * @param arr
	 * @return max(arr) if all elements are negative
	 */
	public static int maximumSubarray2(int[] arr){
		
		int maxSum = Integer.MIN_VALUE;
		int curSum = Integer.MIN_VALUE;
		
		for(int i : arr){
			if(curSum < 0){
				curSum = i;
			} else {
				curSum += i;
			}

			if(curSum > maxSum) {
				maxSum = curSum;
			}
		}
		
		return maxSum;
	}
}
