package common.matrix;

public class LongestAllZeroRect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//int [][] m = { {0, 0, 0}, {0, 1, 0}, {0, 0, 0} };
		int [][] m = { {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0},};
		System.out.println(countLongestZeroLen(m));

	}
	
	public static int countLongestZeroLen(int [][] matrix){
		
		int M = matrix.length;
		int N = matrix[0].length;
		int [][] A = new int[M][N];
		int [][] B = new int[M][N];
		
		for(int j = 0; j < N; j ++){
			A[0][j] = (matrix[0][j] == 0) ? 0 : -1;
		}
		
		for(int i = 1; i < M; i ++){
			for(int j = 0; j < N; j ++){
				A[i][j] = (matrix[i][j] == 0) ? (A[i-1][j] + 1) : -1;
			}
		}
		
		for(int i = 0; i < M; i ++){
			B[i][0] = (matrix[i][0] == 0) ? 0 : -1;
		}
		
		for(int j = 1; j < N; j ++){
			for(int i = 0; i < M; i ++){
				B[i][j] = (matrix[i][j] == 0) ? (B[i][j-1] + 1) : -1;
			}
		}
		
		int max = 0;
		for(int i = 0; i < M; i ++){
			for(int j = 0; j < N; j ++){
				
				int maxij = 0;
				
				for(int ii = A[i][j]; ii > 0; ii --){
					for(int jj = B[i][j]; jj > 0; jj --){
						if(A[i][j - jj] >= ii && B[i - ii][j] >= jj){
							
							int tmp = 2 * (ii + jj + 2) - 4;
							if(tmp > maxij){
								maxij = tmp;
							}
						}
					}
				}
				
				if(maxij >= max){
					max = maxij;
				}
			}
		}
		
		return max;
	}

}
