package common.perm;

import java.util.Arrays;

public class Permutation {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4 };
		allPermSwap(arr, 0);

	}

	/**
	 * Generate all permutations. Will generate duplicates when there's
	 * duplicate elements in in put array
	 * 
	 * @param arr
	 * @param index
	 */
	public static void allPermSwap(int[] arr, int index) {
		if (index == arr.length) {
			System.out.println(Arrays.toString(arr));
		}

		for (int i = index; i < arr.length; i++) {
			swap(arr, index, i);
			allPermSwap(arr, index + 1);
			swap(arr, index, i);
		}
	}

	static void swap(int[] arr, int i, int j) {
		if (i == j) {
			return;
		}
		arr[i] ^= arr[j];
		arr[j] ^= arr[i];
		arr[i] ^= arr[j];
	}
}
