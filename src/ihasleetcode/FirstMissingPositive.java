package ihasleetcode;

public class FirstMissingPositive {

	public static void main(String[] args) {
		
		int[] in = {1,3,2};
		System.out.println(firstMissingPositive(in));

	}

	static  public int firstMissingPositive(int[] A) {

		for(int i = 0; i < A.length; i ++){
			
			while(A[i] > 0 && A[i] <= A.length && A[i] != (i+1) && A[A[i] - 1] != A[i]){
				int tmp = A[A[i] - 1];
				A[A[i] - 1] = A[i];
				A[i] = tmp;
			}
		}
		
		for(int i = 0; i < A.length; i ++){
			if(A[i] != i + 1){
				return i + 1;
			}
		}
        
		return A.length+1;
    }
}
