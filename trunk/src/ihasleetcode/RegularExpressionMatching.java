package ihasleetcode;

public class RegularExpressionMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String [][] testcases = {
				{"", "c*", "true"},
				{"", "c*d*e*", "true"},
				{"", ".*", "true"},
				{"a", "ab*", "true"},
				{"aa", "a", "false"},
				{"aa", "aa", "true"},
				{"aaa", "aa", "false"},
				{"aa", "a*", "true"},
				{"aa", ".*", "true"},
				{"ab", ".*", "true"},
				{"aab", "c*a*b", "true"},
				{"aab", "c*a*b", "true"},
				{"aaa", "aaa", "true"},
				{"aaa", "aa", "false"},
				{"aaa", "aaaa", "false"},
				{"aaa", "a.a", "true"},
				{"aaa", ".a", "false"},
				{"aaa", "a*a", "true"},
				{"aaa", "ab*a", "false"},
				{"aaa", "ab*ac*a", "true"},
				{"aaa", "ab*a*c*a", "true"},
				{"aaca", "ab*a*c*a", "true"},
				{"aaba", "ab*a*c*a", "false"},
				{"aaa", ".*", "true"},
				{"aa", ".", "false"},
				{"a", ".", "true"},
				{"a", "ab*", "true"},
				{"a", "ab*a", "false"},
				{"bbbba", ".*a*a", "true"},
				{"ab", ".*", "true"},
				{"a", ".*", "true"},
				{"", ".*", "true"},
				{"", ".", "false"},
				{"a", ".", "true"},
				{"", "c*", "true"},
				{"a", "a.", "false"},
				{"a", "", "false"},
				{"", "", "true"},
				{"aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s", "true"},
				{"abbbcd", "ab*bbbcd", "true"},
				{"abcdede", "ab.*de", "true"},
		};
		
		for(String[] arr : testcases){
			
			String ans = "" + isMatch(arr[0], arr[1]);
			if(ans.equals(arr[2])){
				System.out.format("%s %s Pass\n", arr[0], arr[1]);
			} else {
				System.out.format("%s %s Fail. Expect %s Got %s\n", arr[0], arr[1], arr[2], ans);
			}
			
		}
		
		System.out.println("done~");

	}

	//Implement regular expression matching with support for '.' and '*'.
	//'.' Matches any single character.
	//'*' Matches zero or more of the preceding element.
	//The matching should cover the entire input string (not partial).
    static public boolean isMatch(String s, String p) {
    	
    	char[] strArr = s.toCharArray();
    	char[] patternArr = p.toCharArray();
    	int pStr = 0, pPattern = 0;
    	
    	return isMatch0(strArr, pStr, patternArr, pPattern);
        
    }

	static private boolean isMatch0(char[] strArr, int pStr, char[] patternArr,
			int pPattern) {
		
		if(pPattern == patternArr.length && pStr == strArr.length){
			return true;
		}
		
		if(pPattern == patternArr.length && pStr < strArr.length){
			return false;
		}
		
		char c = patternArr[pPattern];
		
		if(pPattern < patternArr.length - 1 && patternArr[pPattern + 1] == '*'){
			
			boolean match = isMatch0(strArr, pStr, patternArr, pPattern + 2); // use * as 0
			
			while(!match && pStr < strArr.length && (strArr[pStr] == c || c == '.')){
				match = isMatch0(strArr, ++pStr, patternArr, pPattern + 2);
			}
			
			return match;
		}
		
		if(pStr == strArr.length){
			return false;
		}
		
		if(c == '.' || c == strArr[pStr]){
			return isMatch0(strArr, pStr + 1, patternArr, pPattern + 1);
		}
		
		return false;
	}
}
