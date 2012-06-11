package common.tree;

import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Write a tree to an string and read back from it.
 * @author Jason Huang
 *
 */
public class SerializeTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("Original Tree");
			BTreePrinter.printNode(root);
			System.out.println();
			
			System.out.println("Serialzed form");
			String str = serialize(root);
			System.out.println(str);
			
			System.out.println("Deserialized recursively");
			BTreePrinter.printNode(deserialize_recursively(str));
			System.out.println();

			System.out.println("Deserialized using a stack");
			BTreePrinter.printNode(deserialize(str));
			System.out.println();
		}

	}
	
	static String serialize(Node<Integer> tree){
		
		if(tree == null){
			return "null";
		}
		
		StringBuilder sb = new StringBuilder();
		Stack<Node<Integer>> stk = new Stack<Node<Integer>>();
		
		sb.append(tree.data);
		stk.push(tree.right);
		stk.push(tree.left);
		
		while(!stk.isEmpty()){
			Node<Integer> node = stk.pop();
			
			if(node == null){
				sb.append(" null");
			} else {
				sb.append(" ").append(node.data);
				stk.push(node.right);
				stk.push(node.left);
			}
		}
		
		return sb.toString();
	}


	static Node<Integer> deserialize_recursively(String str){
		
		if(str == null || str.length() == 0){
			throw new IllegalArgumentException("Cannot deserialize empty string");
		}
		
		StringTokenizer tokenizer = new StringTokenizer(str);
		
		return deserialize_recursively_impl(tokenizer);
		
	}
	
	private static Node<Integer> deserialize_recursively_impl(
			StringTokenizer tokenizer) {
		if(!tokenizer.hasMoreTokens()){
			return null;
		}
		
		String tok = tokenizer.nextToken();
		
		if(tok.equals("null")){
			return null;
		}
		
		Node<Integer> root = new Node<Integer>(Integer.parseInt(tok));
		
		root.left = deserialize_recursively_impl(tokenizer);
		root.right = deserialize_recursively_impl(tokenizer);
		return root;
	}
	
	
	static Node<Integer> deserialize(String str){
		
		if(str == null || str.length() == 0){
			throw new IllegalArgumentException("Cannot deserialize empty string");
		}
		
		StringTokenizer tokenizer = new StringTokenizer(str);
		
		String tok = tokenizer.nextToken();
		if(tok.equals("null")){
			return null;
		}
		
		Node<Integer> root = new Node<Integer>(Integer.parseInt(tok));
		root.left = root.right = root;
		Stack<Node<Integer>> stk = new Stack<Node<Integer>>();
		stk.push(root);
		
		while(tokenizer.hasMoreTokens()){
			Node<Integer> node = stk.pop();
			tok = tokenizer.nextToken();
			
			Node<Integer> child = null;
			if(!tok.equals("null")){
				child = new Node<Integer>(Integer.parseInt(tok));
				child.left = child.right = child;
			}

			if(node.left == node){
				stk.push(node);
				node.left = child;
				if(child != null){
					stk.push(child);
				}
			} else {
				assert(node.right == node);
				node.right = child;
				if(child != null){
					stk.push(child);
				}
			}
		}
		
		return root;
	}


}
