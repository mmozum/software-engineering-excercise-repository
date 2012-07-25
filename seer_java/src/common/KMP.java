package common;


public class KMP {

	public static void main(String[] args) {
		System.out.println(countOccurrence("gcagagagcagagag", "gcagagag"));

	}
	
	public static int countOccurrence(String haystack, String needle){
		
		char[] str = haystack.toCharArray();
		char[] pattern = (needle + '\0').toCharArray();
		int n = needle.length();
		
		int[] table = buildFailureTable(pattern);
		
		int count = 0;
		int i = 0, j = 0;
		while(i < str.length){
			while(j > 0 && pattern[j] != str[i]){
				j = table[j];
			}
			
			i ++;
			j ++;
			
			if(j == n){
				count ++;
				j = table[j];
			}
		}
		
		return count;
	}

	private static int[] buildFailureTable(char[] pattern) {
		int[] arr = new int[pattern.length];
		
		int i = 0;
		int j = -1;
		arr[0] = -1;
		
		while(i < arr.length - 1){
			
			while(j >= 0 && pattern[i] != pattern[j]){
				j = arr[j];
			}
			
			i++;
			j++;
			
			if(pattern[i] == pattern[j]){
				arr[i] = arr[j];
			} else {
				arr[i] = j;
			}
		}
		return arr;
	}

}
