package ihasleetcode;

import java.util.LinkedList;

public class SortColors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1,3};
		
		System.out.println();
	}
	

    static  public void sortColors(int[] A) {
    	
    	int a = -1;
    	int c = A.length;

    	for(int current = 0; current < A.length && current < c; current ++){
    		
    		if(A[current] == 0){
    			a ++;
    			int tmp = A[current];
    			A[current] = A[a];
    			A[a] = tmp;
    		} else if(A[current] == 2){
    			c --;
    			int tmp = A[current];
    			A[current] = A[c];
    			A[c] = tmp;
    			current --;
    		}
    	}
    }


    

}
