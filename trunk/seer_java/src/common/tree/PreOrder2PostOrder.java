package common.tree;

import java.util.Stack;
import java.util.StringTokenizer;

/**
    Given:
    Binary operators: +, *
    Operands: postive integers
    Tree1:    *
              |
           -----
          |     |
          +     3
          |
         ---
        |   |
        1   2

       First Array Representation Example:
           PreOrder:   * + 1 2 3
           PostOrder:  1 2 + 3 *

    Input: preorder string
    Output: postorder string
 */

public class PreOrder2PostOrder {


	public static void main(String[] args) {
		
		System.out.println(pre2post_stack("* + 1 2 3"));
		System.out.println(pre2post_recursively("* + 1 2 3"));

	}
	
	public static String pre2post_stack(String preStr){
		StringBuilder sb = new StringBuilder();
		Stack<String> opStk = new Stack<String>();
		Stack<String> numStk = new Stack<String>();
		StringTokenizer st = new StringTokenizer(preStr);
		
		while(st.hasMoreTokens()){
			String tok = st.nextToken();
			
			if(Character.isDigit(tok.charAt(0))){
				if(numStk.isEmpty()){
					numStk.push(tok);
				} else {
					String num1 = numStk.pop();
					if(num1 != null){
						sb.append(num1).append(' ');
					}
					
					sb.append(tok).append(' ');
					sb.append(opStk.pop()).append(' ');
					numStk.push(null);
				}
			} else {
				opStk.push(tok);
			}
		}
		
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	

	public static String pre2post_recursively(String preStr){
		StringTokenizer st = new StringTokenizer(preStr);
		StringBuilder sb = new StringBuilder();
		pre2post_recursively_impl(st, sb);
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	private static void pre2post_recursively_impl(StringTokenizer st,
			StringBuilder sb) {

		if(!st.hasMoreTokens()){
			return;
		}
		
		String tok = st.nextToken();
		if(!Character.isDigit(tok.charAt(0))){
			pre2post_recursively_impl(st, sb);
			pre2post_recursively_impl(st, sb);
		}
		
		sb.append(tok).append(" ");
		
	}

}
