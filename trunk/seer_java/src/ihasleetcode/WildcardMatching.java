package ihasleetcode;

public class WildcardMatching {

	/*
	    '?' Matches any single character.
        '*' Matches any sequence of characters (including the empty sequence).

        The matching should cover the entire input string (not partial).
	 */
	public static void main(String[] args) {
		String [][] testcases = {
				{"mississippi", "m*si*", "true"},
				{"b", "*?*?*", "false"},
				{"aab", "c*a*b", "false"},
				{"c", "*?*", "true"},
				{"aa", "a", "false"},
				{"aa", "aa", "true"},
				{"aaa", "aa", "false"},
				{"aa", "*", "true"},
				{"aa", "a*", "true"},
				{"ab", "?*", "true"},
				{"a", "a", "true"},
				{"a", "aa", "false"},
				{"aa", "aaa", "false"},
				{"", "", "true"},
				{"", "*", "true"},
				{"", "a", "false"},
				{"", "?", "false"},
				{"a", "", "false"},
				{"a", "a*", "true"},
				{"a", "?*", "true"},
				{"a", "*", "true"},
				{"b", "?", "true"},
				{"b", "??", "false"},
				{"bc", "??", "true"},
				{"bcd", "??", "false"},
				{"b", "?*?", "false"},
				{"b", "*?*?", "false"},
				{"cd", "?", "false"},
				{"de", "??", "true"},
				{"fg", "???", "false"},
				{"hi", "*?", "true"},
				{"ab", "*a", "false"},
				{"aa", "*a", "true"},
				{"cab", "*ab", "true"},
				{"ab", "*ab", "true"},
				{"ac", "*ab", "false"},
				{"abc", "*ab", "false"},
				{"cabab", "*ab", "true"},
				{"ab", "ab", "true"},
				{"ab", "*?*?*", "true"},
				{"ac", "ab", "false"},
		};
		
		for(String[] arr : testcases){
			
			String ans = "" + isMatch(arr[0], arr[1]);
			if(ans.equals(arr[2])){
				System.out.format("%s %s Pass\n", arr[0], arr[1]);
			} else {
				System.out.format("%s %s Fail. Expect %s Got %s\n", arr[0], arr[1], arr[2], ans);
			}
			
		}
		
		isMatch("a", "");

		System.out.println("done~");

	}
	
	static public boolean isMatch(String s, String p) {
		
		char[] sArr = s.toCharArray();
		char[] pArr = p.toCharArray();
		
		boolean seenStar = false;
		int idx1 = 0, idx2 = 0;
		int idx1Save = 0, idx2Save = 0;

		while(idx1 < sArr.length && idx2 < pArr.length){
			
			char c = pArr[idx2];
			if(c == '*'){
				seenStar = true;
				idx1Save = idx1;
				idx2Save = idx2;
				++ idx2;

			} else if (c != '?' && c != sArr[idx1]){
				
				if(!seenStar){
					return false;
				}
				
				idx1 = idx1Save+1;
				++idx1Save;
				idx2 = idx2Save;
			} else {
				++ idx1;
				++ idx2;
				if(idx2 == pArr.length && idx1 < sArr.length){
					if(seenStar){
						idx1 = idx1Save+1;
						++idx1Save;
						idx2 = idx2Save;
					} else {
						return false;
					}
				}
			}
		}
		
		while(idx2 < pArr.length && pArr[idx2] == '*')
			++idx2;
		return idx2 == pArr.length && (idx1 == sArr.length || seenStar);

	}
	
//    static public boolean isMatch(String s, String p) {
//        
//        return impl(s.toCharArray(), 0, p.toCharArray(), 0, false);
//        
//    }
//    
//    static boolean impl(char[] s, int idx1, char[] p, int idx2, boolean hasStar){
//        
//        if(idx2 == p.length){
//            return idx1 == s.length || hasStar;
//        }
//        
//        int i1 = idx1, i2 = idx2;
//        
//        for(;i1 < s.length && i2 < p.length; ++i1, ++i2){
//            
//            char c = p[i2];
//            
//            if(c == '*') {
//                return impl(s, i1, p, i2+1, true);
//            }
//            
//            if(c != '?' && c != s[i1]){
//                return hasStar ? impl(s, idx1+1, p, idx2, hasStar) : false;
//            }
//        }
//        
//        if(i1 < s.length){
//        	return hasStar ? impl(s, idx1+1, p, idx2, hasStar) : false;
//        }
//        
//        while(i2 < p.length && p[i2] == '*'){
//            ++i2;
//        }
//        
//        return i2 == p.length;
//    }
    
    
    
//
//    static public boolean isMatch(String s, String p) {
//
//    	//return isMatch0(s.toCharArray(), 0, p.toCharArray(), 0);
//    	return isMatch1(s.toCharArray(), p.toCharArray());
//    }
//
//    /**
//     * recursive impl
//     */
//    static private  boolean isMatch0(char[] s, int sIndex, char[] p,
//			int pIndex) {
//		
//		if(pIndex == p.length){
//			// no more pattern to search
//			return sIndex == s.length;
//		}
//		
//		char c = p[pIndex];
//		
//		if(c == '*'){
//			
//			int sLen = s.length;
//			boolean match = isMatch0(s, sLen, p, pIndex+1); // * matches nothing
//			
//			while(!match && sIndex <= sLen){
//				match = isMatch0(s, sLen--, p, pIndex+1);
//			}
//			
//			return match;
//		}
//		
//		if(sIndex == s.length){
//			return false;
//		}
//		
//		if(s[sIndex] == c || c == '?'){
//			return isMatch0(s, sIndex + 1, p, pIndex + 1);
//		}
//		
//		return false;
//	}
//
//    /**
//     * iterative impl
//     */
//    static private  boolean isMatch1(char[] s, char[] p) {
//    	
//    	int lastStar = new String(p).lastIndexOf('*');
//    	
//    	if(lastStar == -1){
//    		return regularMatch(s, 0, s.length, p, 0, p.length);
//    	}
//    	
//    	lastStar ++;
//    	int sStart = s.length - (p.length - lastStar);
//    	if(sStart < 0 ||
//    			!regularMatch(s, sStart, s.length, p, lastStar, p.length)){
//    		return false;
//    	}
//    	
//    	int pStart = 0;
//    	int pEnd = lastStar;
//    	int sEnd = sStart;
//    	sStart = 0;
//    	
//    	boolean passMode = false;
//    	
//    	while(pStart < pEnd){
//    		
//    		if(p[pStart] == '*'){
//    			passMode = true;
//    			pStart ++;
//    			continue;
//    		} 
//    		
//    		if(sStart == sEnd){
//				return false;
//			}
//    		
//    		if(passMode){
//    			
//    			if(p[pStart] == '*'){
//    				pStart ++;
//    			} else if(s[sStart] == p[pStart] || p[pStart] == '?'){
//    				sStart ++;
//    				pStart ++;
//    				passMode = false;
//    			} else {
//    				sStart ++;
//    			}
//    		} else if (s[sStart] == p[pStart] || p[pStart] == '?'){
//    			sStart ++;
//    			pStart ++;
//    		} else {
//    			return false;
//    		}
//    		
//    	}
//    	
//    	return true;
//    	
//    }
//
//	private static boolean regularMatch(char[] s, int i, int sLen, char[] p,
//			int j, int pLen) {
//		
//		if(sLen - i != pLen - j){
//			return false;
//		}
//		
//		while(i < sLen){
//			if(s[i] != p[j] && p[j] != '?'){
//				return false;
//			}
//			i ++;
//			j ++;
//		}
//		return true;
//	}
}
