package j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Use cases to show pitfalls in Java
 * 
 * @author Jason Huang
 * 
 */
public final class JavaPitfalls {

	public static void main(String[] args) {
		autoboxing1();
		autoboxing2();

	}

	/**
	 * If the value p being boxed is true, false, a byte, a char in the range
	 * \u0000 to \u007f, or an int or short number between -128 and 127, then
	 * let r1 and r2 be the results of any two boxing conversions of p. It is
	 * always the case that r1 == r2.
	 */
	static void autoboxing1() {
		
		System.out.println("=============================================");
		System.out.println("Pitfall [autoboxing1]");
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		map1.put("key1", 1);
		map2.put("key1", 1);
		map1.put("key2", 128);
		map2.put("key2", 128);
		System.out.println(map1.get("key1") == map2.get("key1"));
		System.out.println(map1.get("key2") == map2.get("key2")); // return false!
		
		// equals should be used instead of ==
	}
	
	static void autoboxing2() {
		System.out.println("=============================================");
		System.out.println("Pitfall [autoboxing2]");
		Set<Character> set = new HashSet<Character>();
		int c = 'a';
		set.add((char)c);
		
		System.out.println(set.contains(c)); // return false!
		
		Integer i = (int)'a';
		Character ch = 'a';
		System.out.println(ch.equals(i)); // false
		System.out.println(i.equals(ch)); // false
		
	}
	
	@SuppressWarnings("unused")
	static void typeCast() {
		System.out.println("=============================================");
		System.out.println("Pitfall [typecast 1]");
		
		//Type mismatch: cannot convert from int[] to Object
		//Object obj = {1};
		Object obj2 = new int[]{1};
		Object[][][] obj3 = {null,null};
		
	}

}
