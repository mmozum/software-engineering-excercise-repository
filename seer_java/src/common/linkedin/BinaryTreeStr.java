package common.linkedin;

/**
 * http://www.careercup.com/question?id=13262681
 * 
 * Consider this string representation for binary trees. Each node is of the
 * form (lr), where l represents the left child and r represents the right
 * child. If l is the character 0, then there is no left child. Similarly, if r
 * is the character 0, then there is no right child. Otherwise, the child can be
 * a node of the form (lr), and the representation continues recursively. For
 * example: (00) is a tree that consists of one node. ((00)0) is a two-node tree
 * in which the root has a left child, and the left child is a leaf. And
 * ((00)(00)) is a three-node tree, with a root, a left and a right child.
 * 
 * Write a function that takes as input such a string, and returns -1 if the
 * string is malformed, and the depth of the tree if the string is well-formed.
 * 
 * For instance:
 * 
 * - find_depth('(00)') -> 0
 * - find_depth('((00)0)') -> 1
 * - find_depth('((00)(00))') -> 1
 * - find_depth('((00)(0(00)))') -> 2
 * - find_depth('((00)(0(0(00))))') -> 3
 * - find_depth('x') -> -1
 * - find_depth('0') -> -1
 * - find_depth('()') -> -1
 * - find_depth('(0)') -> -1
 * - find_depth('(00)x') -> -1
 * - find_depth('(0p)') -> -1
 */
public class BinaryTreeStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(find_depth("(00)"));
				System.out.println(find_depth("((00)0)"));
				System.out.println(find_depth("((00)(00))"));
				System.out.println(find_depth("((00)(0(00)))"));
				System.out.println(find_depth("((00)(0(0(00))))"));
				System.out.println(find_depth("x"));
				System.out.println(find_depth("0") );
				System.out.println(find_depth("()"));
				System.out.println(find_depth("(0)"));
				System.out.println(find_depth("(00)x"));
				System.out.println(find_depth("(0p)"));

	}
	
	static int find_depth(String str){
		index = 0;
		int ret = find_depth_recursive(str.toCharArray(), false);
		
		if(ret == -2 || index != str.length()){
			return -1;
		}
		
		assert(ret != -1);
		
		return ret;
	}

	static int index;
	
	static private int find_depth_recursive(char[] arr, boolean allowZero) {
		if(index >= arr.length){
			return -2;
		}
		
		if(arr[index] == '0'){
			index ++;
			return allowZero ? -1 : -2;
		}
		
		if(arr[index] == '('){
			index ++;
			int left = find_depth_recursive(arr, true);
			int right = find_depth_recursive(arr, true);
			if(left == -2 || right == -2){
				return -2;
			}
			
			index ++;
			return Math.max(left, right) + 1;
		}
		
		return -2;
	}

}
