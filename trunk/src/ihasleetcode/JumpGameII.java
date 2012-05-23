package ihasleetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class JumpGameII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1, 2};
		
		System.out.println(jump(arr));

	}
	
	static class Item{
		int index;
		int count;
		
		Item(int i, int c){
			index = i;
			count = c;
		}
	}
	
	// Jump Game II
    static public int jump(int[] A) {

        if(A == null || A.length < 2){
            return 0;
        }
        
        final int N = A.length;
        int[] d = new int[N+1];
        int currentHop = 0;
        
        for(int i = 0; i < N; i ++){
        	
        	int newFar = i + A[i];
        	
        	if(newFar >= d[currentHop + 1]){
        		d[currentHop + 1] = newFar;
        	}
        	
        	if(i == d[currentHop]){
        		currentHop ++;
        	}
        }
        
        for(int i = 0; i < N + 1; i ++){
        	if(d[i] >= N - 1){
        		return i;
        	}
        }
        
        return -1;
    }
	

}
