package ihasleetcode;

public class StrStr {

	/*
		Implement strStr().
		
		Returns a pointer to the first occurrence of needle 
		in haystack, or null if needle is not part of haystack. 
	 */
	
	public static void main(String[] args) {
		System.out.println(strStr("mississippi", "issip"));
		
	}

    static public String strStr(String haystack, String needle) {
        return strStrKMP2(haystack, needle);
        
    }
    
    static String strStrKMP(String haystack, String needle) {
    	
    	if(needle == null || haystack == null){
    		return null;
    	}
    	
    	if(needle.isEmpty()){
    		return haystack;
    	}
    	
    	if(haystack.isEmpty()){
    		return null;
    	}

    	int[] failureTable = kmpPreprocess(needle); 
    	
    	int m = haystack.length();
    	int n = needle.length();
    	
    	int i = 0, j = 0;
    	while(true){
    		
    		if(j == n || i == m){
    			break;
    		}
    		    		
    		if(haystack.charAt(i) == needle.charAt(j)){
    			j ++;
    			i ++;
    		} else if (j > 0){
    			j = failureTable[j];
    		} else {
    			i ++;
    		}
    	}
        
    	return (j==n) ? haystack.substring(i - n) : null;
    }

	private static int[] kmpPreprocess(String needle) {
		
		char[] arr = needle.toCharArray();

		int[] table = new int[arr.length+1];
		table[0] = table[1] = 0;
		
		int k = -1;
		int i = 0;
		
		for(i = 2; i < table.length; i ++){
			
			k = table[i - 1];
			
			while(true){
				if(arr[k] == arr[i - 1]){
					table[i] = k + 1;
					break;
				}
				
				if(k == 0){
					table[k] = 0;
					break;
				}
				
				k = table[k];
			}
		}
		
		return table;
	}
	
	
    static String strStrKMP2(String haystack, String needle) {
    	
    	if(needle == null || haystack == null){
    		return null;
    	}
    	
    	if(needle.isEmpty()){
    		return haystack;
    	}
    	
    	if(haystack.isEmpty()){
    		return null;
    	}

    	int[] failureTable = kmpPreprocess2(needle); 
    	
    	int m = haystack.length();
    	int n = needle.length();
    	
    	int i = 0, j = 0;
    	
    	while(i < m){
    		
    		while(j >= 0 && haystack.charAt(i) != needle.charAt(j)){
    			j = failureTable[j];
    		}
    		
    		i ++;
    		j ++;
    		
    		if(j == n){
    			return haystack.substring(i - n);
    		}
    	}
    	
    	return null;

    }
    
	private static int[] kmpPreprocess2(String needle) {
		
		char[] arr = needle.toCharArray();

		int[] table = new int[arr.length+1];
		table[0] = -1;
		
		int k = -1;
		int i = 0;
		
		while(i < arr.length){
			
			while(k >= 0 && arr[k] != arr[i]){
				k = table[k];
			}
			i++;
			k++;
			table[i] = k;
		}
		
		return table;
	}
}
