package ihasleetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class SpiralMatrixII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] in = { { 1, 2, 3, 4 }, {5, 6, 7, 8},  { 1, 2, 3, 4 }, {5, 6, 7, 8},  };

		System.out.println(Arrays.deepToString(generateMatrix(3)));
	}

	static public int[][] generateMatrix(int n) {
		
		if(n <= 0){
			return new int[][]{};
		}
		
		int i = 0, j = 0;
		int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		
		int counter = 1;
		
		int[][] rslt = new int[n][n];
		
		for(int k = 0; k < n / 2; k ++){
			
			for(int l = 0; l < 4; l ++){
				
				for(int m = 0; m < n - 1 - 2 * k; m ++){
					
					rslt[i][j] = counter ++;
					i += dir[l][0];
					j += dir[l][1];
				}
			}
			
			i += 1;
			j += 1;
			
		}
		
		if(n % 2 == 1){
			rslt[i][j] = counter;
		}
		
		return rslt;
		
	}

}
