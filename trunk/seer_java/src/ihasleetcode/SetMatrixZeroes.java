package ihasleetcode;

import java.util.LinkedList;

public class SetMatrixZeroes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	

    static     public void setZeroes(int[][] matrix) {

    	if(matrix.length == 0){
    		return ;
    	}
    	
    	boolean firstRowZero = false;
    	
    	for(int i = 0; i < matrix[0].length; i ++){
    		if(matrix[0][i] == 0){
    			firstRowZero = true;
    			break;
    		}
    	}
        
    	for(int row = 1; row < matrix.length; row ++){
    		
    		for(int col = 0; col < matrix[0].length; col ++){
    			
    			if(matrix[row][col] == 0){
    				matrix[0][col] = 0;
    				matrix[row][0] = 0;
    			}
    		}
    	}
    	
    	for(int row = 1; row < matrix.length; row ++){
    		for(int col = 1; col < matrix[0].length; col ++){
    			
    			if(matrix[0][col] == 0 || matrix[row][0] == 0){
    				matrix[row][col] = 0;
    			}
    		}
    	}
    	
    	if(matrix[0].length > 0 && matrix[0][0] == 0){
    		for(int row = 0; row < matrix.length; row ++){
    			matrix[row][0] = 0;
    		}
    	}
    	
    	if(firstRowZero){
    		for(int i = 0; i < matrix[0].length; i ++){
    			matrix[0][i] = 0;
    		}
    	}
    	
    }


}
