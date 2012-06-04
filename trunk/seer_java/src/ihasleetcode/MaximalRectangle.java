package ihasleetcode;

import java.util.*;

public class MaximalRectangle {


	public static void main(String[] args) {
		
		String[] strs = { "0001010","0100000","0101001","0011001","1111110","1001011","0100101","1101110","1010101","1110000"
						//  "000"
		};
		
		char[][] matrix = new char[strs.length][];
		
		for(int i = 0; i < strs.length; i ++){
			matrix[i] = strs[i].toCharArray();
		}
		
		System.out.println(maximalRectangle(matrix));

	}
	
	/**
	 * O(N2) version
	 * @param matrix
	 * @return
	 */
	static public int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		
		final int M = matrix.length;
		final int N = matrix[0].length;
		
		int[][] mx = new int[M][N];
		
		for(int col = 0; col < N; col ++){
			
			mx[M-1][col] = matrix[M-1][col] - '0';
			for(int row = M - 2; row >= 0; row --){
				if(matrix[row][col] == '1'){
					mx[row][col] = mx[row+1][col] + 1;
				} else {
					mx[row][col] = 0;
				}
			}
		}
		
		int maxArea = 0;
		for(int[] height : mx){
			// Max rectangle in a histogram problem
			Stack<Integer> stack = new Stack<Integer>();
			for(int i = 0; i < N; i ++){
				
				if(stack.isEmpty() || height[i] > height[stack.peek()]){
					stack.push(i);
					continue;
				} 

				int lastIdx = -1;
				while(!stack.isEmpty() && height[i] < height[stack.peek()]){
					lastIdx = stack.pop();
					int area = (i - lastIdx) * height[lastIdx];
					if(area > maxArea){
						maxArea = area;
					}
				}
				
				if(lastIdx >= 0){
					height[lastIdx] = height[i];
					stack.push(lastIdx);
				}
			}
			
			while(!stack.isEmpty()){
				int idx = stack.pop();
				int area = (N - idx) * height[idx];
				if(area > maxArea){
					maxArea = area;
				}
			}
		}
		
		return maxArea;
	}
	
	
//	/**
//	 * O(N3) version
//	 * @param matrix
//	 * @return
//	 */
//    static public int maximalRectangle(char[][] matrix) {
//    	
//    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
//    		return 0;
//    	}
//
//    	final int M = matrix.length;
//    	final int N = matrix[0].length;
//    	
//    	int[][] hTable = new int[M][N];
//    	
//    	for(int j = 0; j < N; j ++){
//    		
//    		int sum = 0;
//    		for(int i = M - 1;  i >= 0; i --){
//    			sum = (matrix[i][j] == '0') ? 0 : sum + 1;
//    			hTable[i][j] = sum;
//    		}
//    	}
//    	
//    	int area = 0;
//    	for(int i = 0; i < M; i ++){
//    		for(int j = 0; j < N; j ++){
//    			
//    			int h = hTable[i][j];
//    			for(int w = 0; w + j< N && matrix[i][w + j] == '1'; w++ ){
//    				h = Math.min(hTable[i][j + w],h);
//    				area = Math.max(h * (w + 1), area);
//    			}
//    		}
//    	}
//        
//    	return area;
//    }

}
