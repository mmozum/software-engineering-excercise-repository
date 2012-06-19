package common;

import java.util.Arrays;

public class PerfectShuffle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6,7,8,9};
		shuffle(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	

    private static void reverse(int A[], int left, int right)
    {
        while (left < right) {
            int tmp = A[left];
            A[left++] = A[right];
            A[right--] = tmp;
        }
    }
    
    static void shuffle(int[] arr, int start, int end){
    	
    	int len = end - start + 1;
    	if(len <= 2){
    		return;
    	}
    	if(len <= 4){
    		int tmp = arr[start+1];
    		arr[start+1] = arr[start+2];
    		arr[start+2] = tmp;
    		return;
    	}
    	
    	int halfLen = len >>> 1;
        int mid = start + halfLen;
        int left = start + (halfLen >>> 1);
        // rotate
        reverse(arr, left, mid - 1);
        reverse(arr, mid, left + halfLen - 1 );
        reverse(arr, left, left + halfLen - 1 );
        
        shuffle(arr, start, start + (left - start) * 2 - 1);
        shuffle(arr, start + (left - start) * 2, end);
    }

}
