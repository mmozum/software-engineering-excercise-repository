package ihasleetcode;

import java.util.*;

public class MaximalRectangle {


	public static void main(String[] args) {
		
		String[] strs = { "010",
						  "000"
		};
		
		char[][] matrix = new char[strs.length][];
		
		for(int i = 0; i < strs.length; i ++){
			matrix[i] = strs[i].toCharArray();
		}
		
		System.out.println(maximalRectangle(matrix));

	}
	
    static public int maximalRectangle(char[][] matrix) {
    	
    	if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
    		return 0;
    	}

    	final int M = matrix.length;
    	final int N = matrix[0].length;
    	
//    	int[][] wTable = new int[M][N];
    	int[][] hTable = new int[M][N];
    	
//    	for(int i = 0; i < M; i ++){
//    		
//    		int sum = 0;
//    		for(int j = N - 1;  j >= 0; j --){
//    			sum = (matrix[i][j] == '0') ? 0 : sum + 1;
//    			wTable[i][j] = sum;
//    		}
//    	}
//    	
    	
    	for(int j = 0; j < N; j ++){
    		
    		int sum = 0;
    		for(int i = M - 1;  i >= 0; i --){
    			sum = (matrix[i][j] == '0') ? 0 : sum + 1;
    			hTable[i][j] = sum;
    		}
    	}
    	
    	int area = 0;
    	for(int i = 0; i < M; i ++){
    		for(int j = 0; j < N; j ++){
    			
    			int h = hTable[i][j];
    			for(int w = 0; w + j< N && matrix[i][w + j] == '1'; w++ ){
    				h = Math.min(hTable[i][j + w],h);
    				area = Math.max(h * (w + 1), area);
    			}
    		}
    	}
        
    	return area;
    }

}
