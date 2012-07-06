package common.google;

public class MajorityVote {
	
	
	public static void main(String[] args){
		
		int[] arr = {1, 2, 3, 1, 1, 2, 1, 4, 1, 1, 1, 1, 1, 1, 1, 2, 3};
		System.out.println(findMajority(arr));
	}
	
	static int findMajority(int[] arr){
		
		int sum = 0;
		int maj = -1;
		
		for(int i : arr){
			if(sum == 0){
				maj = i;
				sum = 1;
			} else if(i == maj){
				sum ++;
			} else {
				sum --;
			}
		}
		
		return maj;
	}

}
