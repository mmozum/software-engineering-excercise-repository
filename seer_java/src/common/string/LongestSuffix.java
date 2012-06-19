package common.string;


/*
给两个String, s1和s2，找s1的尾部和s2的头部的最长match

Example:

s1: "1234", s2: "3451", result: "34"
s1: "12", s2: "123", result: "12"
 */
public class LongestSuffix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getCommon("1234", "3451"));
		System.out.println(getCommon("12", "123"));
		System.out.println(getCommon("123", "12"));
		System.out.println(getCommon("12", "12"));

	}
	
	static String getCommon(String s1, String s2) {
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		int[] failure = buildFailureTable(arr2);
		int i = -1, j = -1;
		
		if(arr2.length < arr1.length){
			i = arr1.length - arr2.length;
		}
		
		while(i < arr1.length - 1){
			i ++;
			j ++;
			
			while(j >= 0 && arr1[i] != arr2[j]){
				j = failure[j];
			}
		}
		
		if(j <= 0){
			return "";
		}
		
		return s2.substring(0, j+1);
		
	}

	private static int[] buildFailureTable(char[] arr) {
		
		int[] table = new int[arr.length + 1];
		int i = -1, j = 0;
		table[0] = -1;
		
		while(j < arr.length - 1){
			
			while(i >= 0 && arr[i] != arr[j]){
				i = table[i];
			}
			
			i ++;
			j ++;
			if(arr[i] == arr[j]){
				table[j] = table[i];
			} else {
				table[j] = i;
			}
			
		}
		return table;
	}


}
