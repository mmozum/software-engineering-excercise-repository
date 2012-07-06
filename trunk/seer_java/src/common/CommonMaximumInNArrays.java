package common;

/**
 * Given N sorted integer arrays, find the smallest integers common in all of
 * them.
 * 
 * @author Jason Huang
 * 
 */
public class CommonMaximumInNArrays {

	static int findCommon(int[][] intArrs){
		
		if(intArrs == null || intArrs.length == 0){
			return -1;
		}
		
		for(int[] arr : intArrs){
			if(arr == null || arr.length == 0){
				return -1;
			}
		}
		
		int[] pointers = new int[intArrs.length];
		
		int ans = intArrs[0][0];
		
		boolean found = false;
		while(!found){
			
			found = true;
			for(int i = 0; i < intArrs.length; i ++){
				int p = pointers[i];
				while(p < intArrs[i].length && intArrs[i][p] < ans){
					p ++;
				}
				pointers[i] = p;
				if(p >= intArrs[i].length){
					return -1; // no such element
				}
				if(intArrs[i][p] > ans){
					ans = intArrs[i][p];
					found = false;
				}
			}
			
		}
		
		return ans;
		
	}

	public static void main(String[] args) {
		
		int[][] intArrs = {
				{1, 3, 5, 7, 9},
				{2, 4, 6, 7, 10},
				{1, 2, 3, 5, 7, 9},
		};
		
		System.out.println(findCommon(intArrs));

	}

}
