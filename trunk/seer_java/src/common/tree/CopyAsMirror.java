package common.tree;

import java.util.List;
import java.util.Stack;

/**
 * Copy a tree as mirror
 *
 */
public class CopyAsMirror {

	
	public static void main(String[] args) {
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("Original Tree");
			BTreePrinter.printNode(root);
			System.out.println();
			
			System.out.println("copy recursively");
			BTreePrinter.printNode(copy_recursively(root));
			System.out.println();
			
			System.out.println("copy using stack");
			BTreePrinter.printNode(copy_stack(root));
			System.out.println();
		}

	}
	
	static <T extends Comparable<?>> Node<T> copy_recursively(Node<T> root){
		if(root == null){
			return root;
		}
		
		Node<T> rootCopy = new Node<T>(root.data);
		
		rootCopy.left = copy_recursively(root.right);
		rootCopy.right = copy_recursively(root.left);
		
		return rootCopy;
	}
	
	static <T extends Comparable<?>> Node<T> copy_stack(Node<T> root){
		
		if(root == null){
			return null;
		}
		
		Stack<Node<T>> stk1 = new Stack<Node<T>>();
		Stack<Node<T>> stk2 = new Stack<Node<T>>();
		
		Node<T> copy = new Node<T>(root.data);
		
		stk1.push(root);
		stk2.push(copy);
		
		while(!stk1.isEmpty()){
			Node<T> tree1 = stk1.pop();
			Node<T> tree2 = stk2.pop();
			
			if(tree1.right != null){
				tree2.left = new Node<T>(tree1.right.data);
				stk1.push(tree1.right);
				stk2.push(tree2.left);
			}
			
			if(tree1.left != null){
				tree2.right = new Node<T>(tree1.left.data);
				stk1.push(tree1.left);
				stk2.push(tree2.right);
			}
		}
		
		return copy;
	}

}
