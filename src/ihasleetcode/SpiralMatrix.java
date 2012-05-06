package ihasleetcode;

import java.util.ArrayList;

public class SpiralMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[][] in = { { 1, 2, 3, 4 }, {5, 6, 7, 8},  { 1, 2, 3, 4 }, {5, 6, 7, 8},  };

		System.out.println(spiralOrder(in));
	}

	static public ArrayList<Integer> spiralOrder(int[][] matrix) {

		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 } };

		ArrayList<Integer> list = new ArrayList<Integer>();
		int i = 0, j = 0;
		final int M = matrix.length;
		final int N = (M > 0) ? matrix[0].length : 0;
		final int L = Math.min(M, N) / 2;

		for (int k = 0; k < L; k++) {
			for (int l = 0; l < 4; l++) {

				int[] limit = { N - 1 - 2 * k, M - 1 - 2 * k, N - 1 - 2 * k, M - 1 - 2 * k };

				for (int n = 0; n < limit[l]; n++) {
					list.add(matrix[i][j]);
					i += dirs[l][0];
					j += dirs[l][1];
				}
			}

			i += dirs[4][0];
			j += dirs[4][1];
		}

		if (Math.min(M, N) % 2 == 1) {

			if (N >= M) {
				for (int k = 0; k <= N - M; k++) {
					list.add(matrix[i][j]);
					j++;
				}
			} else {

				for (int k = 0; k <= M - N; k++) {
					list.add(matrix[i][j]);
					i++;
				}
			}
		}

		return list;
	}

}
