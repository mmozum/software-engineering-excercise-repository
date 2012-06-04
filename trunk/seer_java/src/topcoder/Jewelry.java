package topcoder;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

	
public class Jewelry {


	/**
	 * SRM 150
	 */
	public static void main(String[] args) {

		String str = "BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD";
		System.out.println(minStrokes(str));
	}
	
	static Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	static Set<Character> set = new HashSet<Character>();
	
	public static int minStrokes(String stripes){
		
		char[] arr = stripes.toCharArray();
		
		for(char c : arr){
			set.add(c);
		}
		
		return min(arr, 0, arr.length, '?');
	}

	private static int min(char[] arr, int left, int length, char c) {
		
		if(length == 0){
			return 0;
		}
		
		String key = "" + left + "_" + length + "_" + c;

		if(table.containsKey(key)){
			return table.get(key);
		}
			
		int best = Integer.MAX_VALUE;

		if(arr[left] == c){
			best = min(arr, left + 1, length - 1, c);
		} else {
			
			for(int i = 1; i <= length; i ++){
				
				int tmp = 1 + min(arr, left + 1, i - 1, arr[left]) + min(arr, left + i, length - i, c);
				
				if(tmp < best){
					best = tmp;
				}
			}
		}
		
		table.put(key, best);
		return best;
	}
}
