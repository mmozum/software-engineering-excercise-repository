package common.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructTreeFromPreOderPrint {

	
	public static void main(String[] args){
		
		String in = "IILILILLILL";
		
		Node<Character> tree = toTree(in);
		BTreePrinter.printNode(tree);
	}


	static int index = 0;
	
	public static Node<Character> toTree(String in) {
		
		index = 0;
		char c = in.charAt(index);
		Node<Character> root = new Node<Character>(c);
		
		return toTreeImpl(in, root);
		
	}

	private static Node<Character> toTreeImpl(String in, Node<Character> root) {

		if(root.data == 'L'){
			return root;
		}
		
		char c = in.charAt(++index);
		root.left = new Node<Character>(c);
		toTreeImpl(in, root.left);

		c = in.charAt(++index);
		root.right = new Node<Character>(c);
		toTreeImpl(in, root.right);
		
		return root;
	}

}
