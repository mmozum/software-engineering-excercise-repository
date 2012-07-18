package ihasleetcode;

import java.util.ArrayList;

public class PermutationSequence {

	public static void main(String[] args){
		System.out.println(getPermutation(5, 120));
		
	}
	
    static public String getPermutation(int n, int k) {
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= n; i ++){
        	list.add(i);
        }
        
        int total = 1;
        for(int i = 1; i <= n; i ++){
        	total *= i;
        }
        
        StringBuilder sb = new StringBuilder();
        k --;
        
        for(int i = n; i > 0; i --){
        	
        	total /= i;
        	int idx = k / total;
        	k %= total;
        	sb.append(list.remove(idx));
        }
        
        return sb.toString();
    }
}
