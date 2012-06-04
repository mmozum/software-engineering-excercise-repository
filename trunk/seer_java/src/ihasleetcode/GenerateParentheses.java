package ihasleetcode;

import java.util.ArrayList;

public class GenerateParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(generateParenthesis(3));

	}

	static public ArrayList<String> generateParenthesis(int n) {
		
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		gen(n, n, sb, list);
		
		return list;
		
	}

	private static void gen(int n1, int n2, StringBuilder sb, ArrayList<String> list) {
		
		if(n1 > 0){
			sb.append('(');
			gen(n1-1, n2, sb, list);
			sb.deleteCharAt(sb.length() -1);
		}
		
		if(n2 > 0 && n2 > n1){
			sb.append(')');
			gen(n1, n2-1, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
		
		if(n2 == 0){
			list.add(sb.toString());
		}
	}
}
