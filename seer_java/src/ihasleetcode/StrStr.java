package ihasleetcode;

import java.util.Arrays;

public class StrStr {

	/*
		Implement strStr().
		
		Returns a pointer to the first occurrence of needle 
		in haystack, or null if needle is not part of haystack. 
	 */
	
	public static void main(String[] args) {
//		System.out.println(Arrays.toString(kmpPreprocess("GCAGAGAG")));
//		System.out.println(Arrays.toString(kmpPreprocess("issip")));
//		System.out.println(strStr("mississippi", "issip"));
		
		System.out.println(Arrays.toString((kmpPreprocess("GCAGAGAG"))));
		
	}

    static public String strStr(String haystack, String needle) {
        return strStrKMP(haystack, needle);
        
    }
    
    static String strStrKMP(String haystack, String needle) {
    	
    	if(needle.isEmpty()){
    		return haystack;
    	}
    	
    	int[] table = kmpPreprocess(needle);
    	
    	int i = 0;
    	int j = 0;
    	while(i < haystack.length()){
    		
    		while(j >= 0 && needle.charAt(j) != haystack.charAt(i)){
    			j = table[j];
    		}
    		
    		i ++;
   			j ++;
   			
    		if(j == needle.length()){
    			return haystack.substring(i - needle.length());
    		}
    	}
    	
    	return null;
    }

	private static int[] kmpPreprocess(String needle) {

		int[] table = new int[needle.length() ];
		
		int i = 0;
		int j = table[0] = -1;
		
		while(i < needle.length() - 1){
			
			while(j >= 0 && needle.charAt(j) != needle.charAt(i)){
				j = table[j];
			}
			
			i ++;
			j ++;
			
			if(needle.charAt(i) == needle.charAt(j)){
				table[i] = table[j];
			} else {
				table[i] = j;
			}
		}
		
		return table;
	}
    
}
