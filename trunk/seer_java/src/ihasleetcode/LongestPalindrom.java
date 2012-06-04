package ihasleetcode;

public class LongestPalindrom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "cabcbabcbabcba";
		
		System.out.println(longestPalindrome(s));
	}

	static public String longestPalindrome(String s) {
		char[] arr = preprocess(s).toCharArray();
		
		int c = 1;
		int[] p = new int[arr.length];
		
		int maxD = -1;
		int maxI = 0;
		
		for(int i = 2; i < arr.length - 2; i ++){
			int rp = 2 * c - i;
			int d = Math.min(p[rp], c + p[c] - i + 1);
			
			
			for(; arr[i+d] == arr[i-d];d++);
			
			d--;
			
			p[i] = d;
			
			if(i + d >= c + p[c]){
				c = i;
			}
			
			if(d > maxD){
				maxD = d;
				maxI = i;
			}
			
		}
		
		return s.substring((maxI - maxD - 1)/2, (maxI + maxD - 1)/2);
	}

	private static String preprocess(String s) {

		if(s.length() == 0){
			return "^$";
		}
		
		StringBuilder sb = new StringBuilder("^#");
		
		for(char c : s.toCharArray()){
			sb.append(c).append('#');
		}
		
		sb.append("$");
		return sb.toString();
	}
}
