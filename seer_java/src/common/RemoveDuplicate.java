package common;

public class RemoveDuplicate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] arr = { 1, 1, 1, 2, 2, 2, 2, 10, 11, 42, 42 };
		int newSize = removeDuplicate(arr);
		for (int i = 0; i < newSize; i++) {
			System.out.print(arr[i] + " ");
		}

	}

	/**
	 * Given a sorted array, remove duplicate. return number of distinct
	 * elements.
	 */
	static int removeDuplicate(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != arr[idx]) {
				arr[++idx] = arr[i];
			}
		}
		return idx + 1;
	}
}
