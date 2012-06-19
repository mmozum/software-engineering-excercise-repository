package common.google;

public class CheckCyclicString {
	
	static String getCyclicString(String str){
		
		if(str == null || str.length() <= 1){
			return "";
		}
		
		int idx = 0;
		for(int i = 1; i < str.length(); i ++){
			if(str.charAt(i) == str.charAt(idx)){
				idx ++;
			} else {
				idx = 0;
			}
		}
		
		return str.substring(0, idx);
	}

	public static void main(String[] args){
		
		System.out.println(getCyclicString("abca"));
		System.out.println(getCyclicString("abcab"));
		System.out.println(getCyclicString("abcabc"));
		System.out.println(getCyclicString("abcabca"));
		System.out.println(getCyclicString("abcabcab"));
	}
}
