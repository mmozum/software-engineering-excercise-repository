package common;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 
Given an array and an integer k , find the maximum for each and every contiguous sub array of size k.

Sample Input :
1 2 3 1 4 5 2 3 6
3 [ value of k ]
Sample Output :
3
3
4
5
5
5
6

 */
public class SlidingWindowMaximum {

	
	public static void main(String[] args){
		
//		int[] inArr2 = {5, 0, 2, 1, 7, 3};
//		System.out.println( Arrays.toString(getSlidingWindowMaximum(inArr2, 3) ));
		
		int size = 100;
		int range = 50;
		int k = 10;
		int[] inArr = j.Util.randomeArray(size, range);
		int[] outArr1 = getSlidingWindowMaximum(inArr, k);
		int[] outArr2 = getSlidingWindowMaximumHeap(inArr, k);

		if(!j.Util.arrayEquals(outArr1, outArr2)){
			System.out.println(Arrays.toString(inArr));
			System.out.println(Arrays.toString(outArr1));
			System.out.println(Arrays.toString(outArr2));
		}
	}

	
	/**
	 * The descending maxima algorithm
	 * @param inArr
	 * @param k
	 * @return
	 */
	static int[] getSlidingWindowMaximum(int[] inArr, int k) {

		final int N = inArr.length;
		int[] outArr = new int[N - k + 1];
		
		int[] buff = new int[k];
		int head = 0, tail = 0;
		buff[0] = 0;
		
		for(int i = 1; i < k - 1; i ++){
			while(head >= tail && inArr[i] >= inArr[ buff[head] ]){
				head --;
			}
			
			buff[++head] = i;
		}
		
		for(int i = k - 1; i < N; i ++){
			
			if(i - buff[tail] >= k){
				tail = (++tail) % k;
			}
			
			while((head+1) % k != tail && inArr[ buff[head] ] <= inArr[i]){
				head = (head + k - 1) % k;
			}
			
			head = (++head) % k;
			buff[head] = i;
			
			outArr[i - k + 1] = inArr[ buff[tail] ];
		}
		
		return outArr;
	}

	/**
	 * Use a heap
	 * @param inArr
	 * @param k
	 * @return
	 */
	static int[] getSlidingWindowMaximumHeap(int[] inArr, int k) {
		
		final int N = inArr.length;
		int[] outArr = new int[ N - k + 1 ];
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, Collections.reverseOrder());
		
		for(int i = 0; i < k; i ++){
			pq.offer(inArr[i]);
		}
		
		outArr[0] = pq.element();
		
		for(int i = k; i < N; i ++){
			
			pq.remove(inArr[i-k]);
			pq.offer(inArr[i]);
			
			outArr[i - k + 1] = pq.element();
		}
		
		return outArr;
	}

}


