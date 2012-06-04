package ihasleetcode;

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

		int[] in = {1,3};
		
		System.out.println(searchStart(in, 3));
	}
	

    static public int[] searchRange(int[] A, int target) {

    	int start = searchStart(A, target);
    	int end = searchEnd(A, target);
    	
    	return new int[] {start, end};
        
    }


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
