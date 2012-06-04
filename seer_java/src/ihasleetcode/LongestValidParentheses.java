package ihasleetcode;

import java.util.Stack;

public class LongestValidParentheses {


	public static void main(String[] args) {
		System.out.println(longestValidParentheses("()"));
		
	}

    static    public int longestValidParentheses(String s) {
        
        final int N = s.length();
        final char[] arr = s.toCharArray();
        
        Stack<Integer> stack = new Stack<Integer>();
        
        int[] lenArr = new int[N];
        
        int max = 0;

        for(int i = 0; i < N; i ++){
            
            if(arr[i] == '('){
                stack.push(i);
            } else if(!stack.empty()) {
                
                int start = stack.pop();
                int len = i - start + 1;
                
                if(start > 0){
                    len += lenArr[start - 1];
                }
                
                if(len > max){
                    max = len;
                }
              
                lenArr[i] = len;

            } 
            

        }
        

        return max;
        
    }
}
