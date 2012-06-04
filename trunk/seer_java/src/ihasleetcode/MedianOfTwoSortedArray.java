package ihasleetcode;

public class MedianOfTwoSortedArray {

	/*
	 There are two sorted arrays A and B of size m and n respectively. 
	 Find the median of the two sorted arrays. 
	 The overall run time complexity should be O(log (m+n)).
	 */
	
	public static void main(String[] args) {
		
		int[] A = {1};
		int[] B = {1};
		System.out.println(findMedianSortedArrays(A,B));
		System.out.println(findMedianSortedArrays(B,A));

	}

	static int half;
    static public double findMedianSortedArrays(int A[], int B[]) {
    	
    	half = (A.length + B.length) >> 1;
    	
    	return find(A, B, Math.max(0, half - B.length), Math.min(A.length - 1, half));
    }

	private static double find(int[] aArr, int[] bArr, int left, int right) {
		
		if(left > right){
			return find(bArr, aArr, Math.max(0, half- aArr.length), Math.min(bArr.length - 1, half));
		}
		
		int i = (left + right) >> 1;
		int j = half - i - 1;
		
		if((j < 0 || bArr[j] <= aArr[i]) && (j >= bArr.length - 1 || aArr[i] <= bArr[j+1])){
			
			if((aArr.length + bArr.length) % 2 == 1){
				return aArr[i];
			} else {
				
				int nextMin = -1;
				if(i >= 1){
					nextMin = Math.max(nextMin, aArr[i-1]);
				}
				if(j >= 0){
					nextMin = Math.max(nextMin, bArr[j]);
				}
				
				return (nextMin + aArr[i])/2.0;
			}
		}
		
		if(j < bArr.length - 1 && aArr[i] > bArr[j + 1]){
			return find(aArr, bArr, left, i - 1);
		}
		return find(aArr, bArr, i + 1, right);
	}
}
