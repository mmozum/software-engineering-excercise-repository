package ihasleetcode;

import java.util.Arrays;

/**
 * Given a sorted array of integers, find the starting and ending 
 * position of a given target value.
 * 
 * @author Jason Huang
 */

public class SearchForARange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1};
		
		System.out.println(Arrays.toString(searchRange(in, 3)));
	}
	
	// Best Impl so far
    static public int[] searchRange(int[] A, int target) {
        int[] ans = {-1, -1};
        
        int lo = 0;
        int hi = A.length-1;
        
        while(lo <= hi){
            int mi = (lo + hi) >>> 1;
            
            if(A[mi] >= target){
                hi = mi - 1;
            } else {
                lo = mi + 1;
            }
        }
        
        if(lo < A.length && A[lo] == target){
            ans[0] = lo;
        }
        
        lo = 0;
        hi = A.length - 1;
        while(lo <= hi){
            int mi = (lo + hi) >>> 1;
            if(A[mi] <= target){
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        

        lo --;
        if(lo >= 0 && A[lo] == target){
            ans[1] = lo;
        }
        
        
        return ans;
        
    }

//    static public int[] searchRange(int[] A, int target) {
//
//    	int start = searchStart(A, target);
//    	int end = searchEnd(A, target);
//    	
//    	return new int[] {start, end};
//        
//    }


	private static int searchEnd(int[] arr, int target) {
		
		if(arr.length == 0){
			return -1;
		}
		
		int hi = arr.length - 1;
		int lo = 0;
		
		while(lo < hi){
			
			int mi = lo + ((hi - lo + 1) >> 1);
			
			if(arr[mi] <= target){
				lo = mi;
			} else {
				hi = mi - 1;
			}
		}
		return arr[hi] == target ? hi : -1;
	}


	private static int searchStart(int[] arr, int target) {

		if(arr.length == 0){
			return -1;
		}
		
		int hi = arr.length - 1;
		int lo = 0;
		
		while(lo < hi){
			
			int mi = lo + ((hi - lo) >> 1);
		
			if(arr[mi] >= target){
				hi = mi;
			} else {
				lo = mi + 1;
			}
		}
		
		return arr[lo] == target ? lo : -1;
	}
    

}
