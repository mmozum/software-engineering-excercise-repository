package common.google;

import java.util.Arrays;

/**
 * Given a sentence, count the word
 * @author Jason Huang
 *
 */
public class CountWord {
	
	public static void main(String[] args){
		
		System.out.println(countWord_regex("  fdsfd  sdfsdfs  "));
		
	}

	static int countWord_regex(String str){
		String[] arr = str.split("\\s+");
		
		System.out.println(Arrays.toString(arr));
		
		int len = arr.length;
		if(arr.length > 0 && arr[0].equals("")){
			len --;
		}
		return len;
	}
}
