package common.datastructure;

import java.util.Arrays;

/**
 * Given an unsorted array. For each element, output the number
 * of elements that are less than it on the left/right side.
 *  
 * @author Jason Huang
 *
 */
public class BinaryIndexedTree {

	
	public static void main(String[] args) {
		
		int[] inArr = {1,3,2,4,5,4,2};
		int[] outArr = new int[inArr.length];
		
		int max = 0;
		for(int i : inArr){
			max = Math.max(max, i);
		}
		
		int[] tree = new int[max+1];
		
		for(int j = inArr.length - 1; j >= 0; --j){
			int ele = inArr[j];
			int sum = 0;
			while(ele > 0){
				sum += tree[ele];
				ele -= ele & -ele;
			}
			outArr[j] = sum;
			
			ele = inArr[j];
			while(ele <= max){
				++tree[ele];
				ele += ele & -ele;
			}
		}
		
		System.out.println(Arrays.toString(outArr));

	}

}
