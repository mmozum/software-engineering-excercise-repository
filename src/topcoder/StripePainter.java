package topcoder;
import java.util.Arrays;

	
public class StripePainter {


	/**
	 * TopCoder problem "Jewelry" used in TCO '03 Round 4 (Division I Level Two)
	 * Simplified version
	 */
	public static void main(String[] args) {

		int[] in = {7,7,8,9,10,11,1,2,2,3,4,5,6};
		System.out.println(howMany(in));
	}
	
	public static long howMany(int[] values){
		
		final int S = 1000 * 30;
		final int N = values.length;
		
		Arrays.sort(values);
		
		int[] arr = new int[N + 1];
		System.arraycopy(values, 0, arr, 1, N);
		
		long[][] below = new long[N+2][S+2];
		long[][] above = new long[N+2][S+2];
		
		below[0][0] = above[N+1][0] = 1;
		
		for(int i = 1; i <= N; i ++){
			for(int s = 0; s <= S; s ++){
				below[i][s] = below[i-1][s] + ((s >= arr[i]) ? below[i-1][s - arr[i]] : 0); 
			}
		}
		
		for(int i = N; i >= 1; i --){
			for(int s = 0; s <= S; s ++){
				above[i][s] = above[i+1][s] + ((s >= arr[i]) ? above[i+1][s - arr[i]] : 0);
			}
		}
		
		long total = 0;
		
		for (int i = 1; i <= N; i++) {

			int n = 1;
			
			while (i + n <= N && arr[i + n] == arr[i]) {
				n++;
			}
			
			for (int k = 1; k <= n; k++) {
				for (int s = arr[i] * k; s < S; s++) {
					total += choose(n, k) * below[i - 1][s - k * arr[i]]
							* above[i + k][s];
				}
			}
			
			i += n -1;
		}
		
		return total;
	}

	private static long choose(int n, int k) {
		
		if(n - k < k){
			return choose(n, n - k);
		}
		
		long r = 1;
		
		for(int i = 1; i <= k; i ++){
			r = r * (i + n - k) / i;
		}
		return r;
	}
}
