package common;

import java.util.Arrays;
import java.util.Random;

public class FindTheMissingAndDuplicate {

	static final int N = 101;
	static int dupAnswer, missingAnswer;
	
	static int[] generateTestCase(final int n){
		
		// generate sorted array
		
		int[] arr = new int[n];
		
		for(int i = 0;i < n; i ++){
			arr[i] = i + 1;
		}
		
		// shuffle
		Random rand = new Random();
		for(int i = n - 1; i > 0; i --){
			int idx = rand.nextInt(i + 1);
			int tmp = arr[idx];
			arr[idx] = arr[i];
			arr[i] = tmp;
		}
		
		// replace one with another
		int idx1 = rand.nextInt(n); // duplicate
		int idx2 = rand.nextInt(n); // missing
		
		while(idx2 == idx1){
			idx2 = rand.nextInt(n);
		}
		
		dupAnswer = arr[idx1];
		missingAnswer = arr[idx2];
		System.out.format("%d is replaced with %d\n", arr[idx2], arr[idx1]);
		
		arr[idx2] = arr[idx1];
		
		return arr;
	}

	public static void main(String[] args) {

		for(int i = 0; i < 10; i ++){
			int[] originalTestcase = generateTestCase(N + i);
			
			int[] arr = Arrays.copyOf(originalTestcase, originalTestcase.length);
			int dup = findDuplicate_swap(arr);
			if(dup != dupAnswer){
				System.out.println("findDuplicate_swap failed for test case:\n"
						+ Arrays.toString(arr));
			}

			arr = Arrays.copyOf(originalTestcase, originalTestcase.length);
			dup = findDuplicate_add(arr);
			if(dup != dupAnswer){
				System.out.println("findDuplicate_add failed for test case:\n"
						+ Arrays.toString(arr));
			}
			
			arr = Arrays.copyOf(originalTestcase, originalTestcase.length);
			int mis = findMissing_swap(arr);
			if(mis != missingAnswer){
				System.out.println("findMissing_swap failed for test case:\n"
						+ Arrays.toString(arr));
				System.out.format("expect: %d, found: %d\n", missingAnswer, mis);
			}
			
			arr = Arrays.copyOf(originalTestcase, originalTestcase.length);
			mis = findMissing_add(arr);
			if(mis != missingAnswer){
				System.out.println("findMissing_add failed for test case:\n"
						+ Arrays.toString(arr));
				System.out.format("expect: %d, found: %d\n", missingAnswer, mis);
			}
		}
	}

	static int findDuplicate_swap(int[] arr) {

		for(int i = 0; i < arr.length; i ++){

			while(arr[i] != i + 1 && arr[arr[i] - 1] != arr[i]){
				int tmp = arr[arr[i] - 1];
				arr[arr[i] - 1] = arr[i];
				arr[i] = tmp;
			}
			
			if(arr[i] != i + 1){
				return arr[i];
			}
		}
		
		return -1;
	}
	
	static int findMissing_swap(int[] arr) {
		
		for(int i = 0; i < arr.length; i ++){
			
			while(arr[i] != i + 1 && arr[arr[i] - 1] != arr[i]){
				int tmp = arr[arr[i] - 1];
				arr[arr[i] - 1] = arr[i];
				arr[i] = tmp;
			}
		}
		
		for(int i = 0; i < arr.length; i ++){
			if(arr[i] != i + 1){
				return i + 1;
			}
		}
		
		return -1;
	}
	
	static int findDuplicate_add(int[] arr){
		
		int N = arr.length + 1;
		
		for(int i = 0; i < arr.length; i ++){
			int cur = arr[i] % N;
			
			if(arr[cur - 1] > N){
				return cur;
			}
			
			arr[cur - 1] += cur * N;
			arr[i] -= cur;
		}
		
		return -1;
	}
	
	static int findMissing_add(int[] arr){
		
		int N = arr.length + 1;
		
		for(int i = 0; i < arr.length; i ++){
			int cur = arr[i] % N;
			
			arr[cur - 1] += cur * N;
			arr[i] -= cur;
		}
		
		for(int i = 0; i < arr.length; i ++){
			if(arr[i] == 0){
				return i + 1;
			}
		}
		return -1;
	}

}
