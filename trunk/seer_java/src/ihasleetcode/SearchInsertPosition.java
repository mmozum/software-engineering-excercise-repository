package ihasleetcode;

import java.util.LinkedList;

public class SearchInsertPosition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1,3};
		
		System.out.println(searchInsert(in, 1));
	}
	

    static public int searchInsert(int[] A, int target) {
    	
    	if(A.length == 0){
    		return 0;
    	}
        
    	int hi = A.length - 1;
    	int lo = 0;
    	
    	while(lo < hi){
    		int mi = lo + ((hi - lo) >> 1);
    		
    		if(A[mi] >= target){
    			hi = mi;
    		} else {
    			lo = mi + 1;
    		}
    	}
    	
    	return A[lo] >= target ? lo : lo + 1;
    }


    

}
