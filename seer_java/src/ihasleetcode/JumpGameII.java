package ihasleetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class JumpGameII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		
		System.out.println(jump(arr));

	}
	
	
    static public int jump(int[] A) {

    	int idx = 0;
        int cur = 0;
        int next = 1;
        int step = 0;
        
        if(A.length < 1){
        	return 0;
        }
        
        while(cur < next){
        	cur = next;
        	for(; idx < A.length && idx < cur; idx ++){
        		next = Math.max(next, idx + A[idx]);
        	}
        	
        	++ step;
        	
        	if(next >= A.length - 1){
        		return step;
        	}
        }
        
        return -1;
        
    }
//	
//    static public int jump(int[] A) {
//
//        int cur = 0;
//        int step = 0;
//        
//        while(cur < A.length - 1){
//            int max = cur;
//            int next = cur;
//            
//            if(cur + A[cur] >= A.length - 1){
//            	return step + 1;
//            }
//            
//            for(int i = cur; i <= cur + A[cur]; i ++){
//                if(i + A[i] > max){
//                    max = i + A[i];
//                    next = i;
//                }
//            }
//            
//            if(max == cur){
//                return -1;
//            } else {
//                step ++;
//                cur = next;
//            }
//        }
//        
//        return step;
//        
//    }
//	
//    static public int jump(int[] A) {
//
//        int current = 0;
//        int step = 0;
//        
//        while(current < A.length - 1){
//            
//            int next = current;
//            int max = -1;
//            
//            if(A[current] + current >= A.length - 1){
//            	return step + 1;
//            }
//            
//            for(int i = current; i <= Math.min(A[current] + current, A.length-1); i ++){
//                if(A[i] + i > max){
//                    max = A[i] + i;
//                    next = i;
//                }
//            }
//            
//            step ++;
//            
//            if(max >= A.length - 1){
//            	return step;
//            }
//            
//            if(next == current){
//                return step;
//            }
//            current = next;
//        }
//
//        return step;
//    }
	
	static class Item{
		int index;
		int count;
		
		Item(int i, int c){
			index = i;
			count = c;
		}
	}
	
//	// Jump Game II
//    static public int jump(int[] A) {
//
//        if(A == null || A.length < 2){
//            return 0;
//        }
//        
//        final int N = A.length;
//        int[] d = new int[N+1];
//        int currentHop = 0;
//        
//        for(int i = 0; i < N; i ++){
//        	
//        	int newFar = i + A[i];
//        	
//        	if(newFar >= d[currentHop + 1]){
//        		d[currentHop + 1] = newFar;
//        	}
//        	
//        	if(i == d[currentHop]){
//        		currentHop ++;
//        	}
//        }
//        
//        for(int i = 0; i < N + 1; i ++){
//        	if(d[i] >= N - 1){
//        		return i;
//        	}
//        }
//        
//        return -1;
//    }
	

}
