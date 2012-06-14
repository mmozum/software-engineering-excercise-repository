package common.linkedin;

import java.util.Arrays;

/**
 * Given a sentense, reverse all the word.
 *
 */
public class ReverseWord {

	public static void main(String[] args) {
		System.out.println(reverseWord_split("TODO Auto-generated    method stub"));

	}
	
	/**
	 * Implementation 1: using pointers
	 * @param str
	 * @return
	 */
	static String reverseWord(String str){
		
		char[] arr = str.toCharArray();
		
		int start = 0;
		int end = -1;
		
		while(start < arr.length){
			
			start = end + 1;
			
			while(start < arr.length && arr[start] == ' '){
				start ++;
			}
			
			if(start >= arr.length){
				break;
			}
			
			end = start + 1;
			
			while(end < arr.length && arr[end] != ' '){
				end ++;
			}
			
			reverse(arr, start, end - 1);
		}
		
		reverse(arr, 0, arr.length - 1);
		return new String(arr);
	}
	
	private static void reverse(char[] arr, int start, int end){
		for(int i = start, j = end; i < j; i ++, j --){
			char c = arr[i];
			arr[i] = arr[j];
			arr[j] = c;
		}
	}
	
	/**
	 * Implementation 1: using pointers
	 * @param str
	 * @return
	 */
	static String reverseWord_split(String str){
		String regex = " ";
		String[] strs = str.split(regex);
		
		System.out.println(Arrays.toString(strs));
		return null;
	}

}
