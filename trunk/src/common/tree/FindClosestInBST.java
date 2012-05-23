package common.tree;

import java.util.Arrays;

public class FindClosestInBST {

	public static void main(String[] args){
		
		final int n = 20;
		Integer[] arr = Util.generateRandomArray(n);
		Arrays.sort(arr);
		Node<Integer> treeFromArray = ConvertToBST.array2bst(arr);
		
		Integer[] resultArr = new Integer[n * 4 + 2];
		
		System.out.println(Arrays.toString(arr));
		
		for(int i = 0; i < resultArr.length; i ++){
			System.out.format("Looking for %d, Found: %d\n", i, findClosest(treeFromArray, i));
		}

	}
	
	public static Integer findClosest(Node<Integer> tree, Integer target){
		
		assert(tree != null);
		
		if(tree.data == target){
			return tree.data;
		}
		
		int result = tree.data;
		int result2 = 0;
		boolean result2Valid = false;
		
		if(tree.data > target && tree.left != null){
			
			result2 = findClosest(tree.left, target);
			result2Valid = true;
			
		} else if(tree.data < target && tree.right != null){
			
			result2 = findClosest(tree.right, target);
			result2Valid = true;
		}
		
		if(result2Valid && Math.abs(result2 - target) < Math.abs(result - target)){
			result = result2;
		}
		
		return result;
	}



}
