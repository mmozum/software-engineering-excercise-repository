package common.google;

/**
Write a function that takes a list of strings and returns the longest prefix
 common to all of them.  E.g.

    List of strings:
        "abc"
        "abcdef"
        "abcd"

    Output:
        "abc", since "abc" is the longest string that appears at the start
of each string.


    If the string "q" is appended to the input list, the output is then "",
since there is no prefix common to all strings.
 */

public class LongestCommonPrefix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] arr = {
		        "abc",
		        "abcdef",
		        "abcd",
		};
		
		System.out.println(getCommonPrefix(arr));
	}
	
	// it gets a bit tricker if strArr have null/empty strings that needs
	// to be skipped
	static String getCommonPrefix(String[] strArr){
		
		if(strArr == null || strArr.length == 0){
			return "";
		}
		
		String prefix = strArr[0];
		
		for(int i = 1; i < strArr.length; i ++){
			String str = strArr[i];
			
			int idx = 0;
			int len = Math.min(prefix.length(), str.length());
			
			for(; idx < len && prefix.charAt(idx) == str.charAt(idx); idx++){
				
			}
			
			if(idx < prefix.length()){
				prefix = prefix.substring(0, idx);
			}
		}
		
		return prefix;
	}

}
