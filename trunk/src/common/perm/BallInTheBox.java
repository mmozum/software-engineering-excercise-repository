package common.perm;

public class BallInTheBox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(ballInBox(7, 4, 1));

	}

	// put M same balls in N same boxes. 
	// there must be at least n balls in each box
	public static long ballInBox(int M, int N, int n){
		if(M <= 0 || M < n){
			return 0;
		}
		
		if(N == 1){
			return 1;
		}
		
		long sum = 0;
		for(int i = n; i < M; i ++){
			sum += ballInBox(M - i, N - 1, i);
		}
		
		return sum;
	}
}
