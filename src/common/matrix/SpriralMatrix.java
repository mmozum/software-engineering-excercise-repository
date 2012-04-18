package common.matrix;

import java.util.Arrays;


public class SpriralMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] arr = gen(5);
		
		System.out.println(Arrays.deepToString(arr));
		print(arr);
	}
	
	/*
	 * Print a matrix in spiral way
	 */
	
	static void print(int[][] matrix){
		
		int N = matrix.length;
		
		
		int[][] dirs = {
				{0, 1},
				{1, 0},
				{0, -1},
				{-1, 0},
				{1, 1},
		};
		
		int i = 0, j = 0;
		
		for(int len = N - 1; len>0; len-=2){
			
			int d = 0;
			for(; d < 4; d ++){
				
				
				for(int k = 0; k < len; k++){
					System.out.print(matrix[i][j] + " ");
					i += dirs[d][0];
					j += dirs[d][1];
				}
				
			}

			i += dirs[d][0];
			j += dirs[d][1];
		}

		if(N%2 == 1){
			System.out.println(matrix[i][j]);
		} else {
			System.out.println();
		}

	}

	/*
	 Write a function that accepts a non-negative integer n as input and generates a n x n matrix:

	 For example:
	 n = 3 generates:
	 1 2 3
	 8 9 4
	 7 6 5

	 n = 4 generates:
	 1 2 3 4
	 12 13 14 5
	 11 16 15 6
	 10 9 8 7
	 */
	private static int[][] gen(int n) {
		
		int[][] matrix = new int[n][n];
		
		for(int i = 0; i < n; i ++){
			for(int j = 0; j < n; j ++){

				int mUp = i;
				int mDown = n-1 - i;
				int mLeft = j;
				int mRight = n-1 -j;
				
				int hMargin = Math.min(mLeft,  mRight);
				int vMargin = Math.min(mUp,  mDown);
				
				int m = Math.min(hMargin, vMargin);
				
				int count = n * n - (n- 2 * m) * (n -2 * m);
				
				if(mUp <= mDown && mRight > m && (mLeft != m || mUp == mLeft)){
					count += (j - m);
				} else if(mRight <= mLeft && mDown > m && (mUp != m || mUp == mRight)){
					count += (i - m) + (n - 2 * m - 1);
				} else if(mDown < mUp && mLeft > m && (mRight != m || mRight == mDown) ){
					count += 3 * (n - 2 * m - 1) - (j - m);
				} else {
					count += 4 * (n - 2 * m - 1) - (i - m);
				}
				
				count ++;
				
				matrix[i][j] = count;
				//System.out.print( count + " ");
				
			}
			
			//System.out.println();
		}
		
		return matrix;
		
	}

}
