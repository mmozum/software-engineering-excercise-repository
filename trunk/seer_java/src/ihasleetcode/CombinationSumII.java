package ihasleetcode;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of candidate numbers (C) and a target number (T), 
 * find all *unique* combinations in C where the candidate numbers sums 
 * to T. Each number in C may only be used once in the combination. 
 */

/**
 * @author Jason Huang
 * 
 */
public class CombinationSumII {

	/**
	 * 
	 */
	public static void main(String[] args) {

		int[] num = { 10, 1, 2, 7, 6, 1, 5 };

		ArrayList<ArrayList<Integer>> result = combinationSum2_recursive(
				num, 8);

		System.out.println(result);

	}

	/**
	 * Non-Recursive Version
	 * 
	 * @param num
	 *            - Input integer array
	 * @param target
	 *            - the target sum
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum2_nonrecursive(
			int[] num, int target) {

		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> fullResult = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> resultIndex = new ArrayList<Integer>();

		int targetToGo = target;
		int lastVisit = -1;

		int currentIndex = 0;

		while (currentIndex < num.length) {
			if (num[currentIndex] >= targetToGo) {

				if (num[currentIndex] == targetToGo) {
					result.add(num[currentIndex]);
					fullResult.add( new ArrayList<Integer>(result));
					//System.out.println(result);
					result.remove(result.size() - 1);
				}
				// prune
				if (result.size() == 0) {
					break;
				}
				lastVisit = result.remove(result.size() - 1);
				currentIndex = resultIndex.remove(resultIndex.size() - 1) + 1;
				targetToGo += lastVisit;
			} else {

				if (num[currentIndex] != lastVisit) {
					result.add(num[currentIndex]);
					resultIndex.add(currentIndex);
					targetToGo -= num[currentIndex];
				}
				currentIndex++;
			}

		}

		return fullResult;
	}

	/**
	 * Recursive Version
	 * 
	 * @param num
	 *            - Input integer array
	 * @param target
	 *            - the target sum
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum2_recursive(
			int[] num, int target) {

		Arrays.sort(num);
		return combinationSum2_recursive0(num, target, 0);
	}

	private static ArrayList<ArrayList<Integer>> combinationSum2_recursive0(
			int[] num, int target, int start) {

		ArrayList<ArrayList<Integer>> fullResult = new ArrayList<ArrayList<Integer>>();

		int lastVisit = -1;
		for (int i = start; i < num.length; i++) {
			if (num[i] == lastVisit) {
				continue;
			}

			lastVisit = num[i];

			if (num[i] > target) {
				break; // prune
			}

			if (num[i] == target) {
				ArrayList<Integer> result = new ArrayList<Integer>();
				result.add(num[i]);
				fullResult.add(result);
			} else {
				// num[i] < target
				ArrayList<ArrayList<Integer>> result = combinationSum2_recursive0(
						num, target - num[i], i + 1);

				for (ArrayList<Integer> r : result) {
					r.add(num[i]);
					// Collections.sort(r);
					fullResult.add(r);
				}

			}

		}
		return fullResult;
	}
}
