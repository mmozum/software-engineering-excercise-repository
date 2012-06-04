package ihasleetcode;

import java.util.ArrayList;
import java.util.Hashtable;

public class StringWithConcatenationOfAllWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "a";
		String[] L = {"a", };
		
		System.out.println(findSubstring(s, L));

	}

	static public ArrayList<Integer> findSubstring(String S, String[] L) {
	       
	       int lenL = L[0].length();
	       Hashtable<String, Integer> in = new Hashtable<String, Integer>();
	       
	       for(String s : L){
	           if(in.containsKey(s)){
	               in.put(s, in.get(s) + 1);
	           } else {
	               in.put(s, 1);
	           }
	       }
	       
	       Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	       
	       ArrayList<Integer> result = new ArrayList<Integer>();

	        next:
	       for(int i = 0; i <= S.length() - L.length * lenL; i ++){
	           
	            table.clear();
	            
	            for(String s : in.keySet()){
	                table.put(s, 0);
	            }
	            
	            int current = i;
	            
	           for(int j = 0; j < L.length; j ++){
	               
	                String s = S.substring(current, current + lenL);
	                if(!table.containsKey(s) || table.get(s) >= in.get(s)){
	                    continue next;
	                }
	                
	                table.put(s, table.get(s) + 1);
	                current += lenL;
	           }
	           
	           result.add(i);
	       }
	       
	       return result;
	        
	    }

}
