package ihasleetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] in = {"abc", "de", "ed"};
		System.out.println(anagrams(in));

	}
	

    static public ArrayList<String> anagrams(String[] strs) {

    	Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	
    	for(String str : strs){
    		
    		// convert str to ana
    		int[] alphabet = new int[256];
    		
    		for(char c : str.toCharArray()){
    			alphabet[c] ++;
    		}
    		
    		StringBuilder sb = new StringBuilder();
    		
    		for(int i = 0; i < alphabet.length; i ++){
    			if(alphabet[i] > 0){
    				sb.append((char) i).append(alphabet[i]);
    			}
    		}
    		
    		String ana = sb.toString();
    		
    		if(map.containsKey(ana)){
    			map.get(ana).add(str);
    		} else {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(str);
    			map.put(ana, list);
    		}
    	}
    	
    	ArrayList<String> result = new ArrayList<String>();
    	
    	for(ArrayList<String> list : map.values()){
    		if(list.size() > 1){
    			result.addAll(list);
    		}
    	}
        
    	return result;
    }

}
