package common;

public class ReverseWord {

	public static void main(String[] args) {
		String[] strs = {
				"", " ", "  ", " abcasd", " rise,  sun", "hello, world",
				"abc jiujiu ",
		};
		
		for(String s : strs){
			System.out.format("[%s]<=>[%s]\n", s, reverse(s));
		}

	}
	
	public static String reverse(String word){
		
		if(word == null){
			return null;
		}
		
		int start = -1, end = -1;
		
		char[] buff = word.toCharArray();
		
		reverseHelper(buff, 0, buff.length - 1);
		
		while(true){
			start = end + 1;
			while(start < buff.length && buff[start] == ' '){
				start ++;
			}
			
			end = start + 1;
			while(end < buff.length && buff[end] != ' '){
				end += 1;
			}
			
			if(start >= buff.length){
				break;
			}
			
			reverseHelper(buff, start, end-1);
		}
		
		return new String(buff);
	}
	
	static void reverseHelper(char[] arr, int low, int high){
		char tmp;
		for(int i = low, j = high; i < j; i++, j--){
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
	}

}
