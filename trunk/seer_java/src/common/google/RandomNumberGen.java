package common.google;

import java.util.Arrays;

/**
Given a list, L, of size k. L contains elements between 1 and n. Also given
a function RND() such that this function returns a number between 1 and INT_
MAX.
Now generate number between 1 and n, using RND(), such that the element
should not be there in the list L. All elements should have a uniform
probability.
 *
 */
public class RandomNumberGen {


	public static void main(String[] args) {
		
		int[] arr = {2, 3, 5, 7};
		int n = 9;

		int[] map = new int[n - arr.length];
		
		int idxMap = 0;
		int idxArr = 0;
		
		for(int i = 1; i <= n; i ++){
			if(idxArr < arr.length && arr[idxArr] == i){
				idxArr ++;
			} else {
				map[idxMap] = i;
				idxMap ++;
			}
		}
		
		System.out.println(Arrays.toString(map));
	}

}
