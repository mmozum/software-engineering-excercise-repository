package common.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 打印二叉树的所有从root到叶节点的所有路径，不能用递归。
 * 
 * @author Jason Huang
 */
public class PrintAllPaths {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Node<Integer>> list = Util.getTrees();

		for (Node<Integer> root : list) {

			System.out.println("-------------------------------");
			BTreePrinter.printNode(root);
			printAllPaths(root);
			System.out.println();

		}

	}

	static void printAllPaths(Node<?> root) {

		if (root == null) {
			return;
		}
		Stack<Node<?>> stack = new Stack<Node<?>>();
		LinkedList<Node<?>> list = new LinkedList<Node<?>>();

		stack.push(root);

		while (!stack.isEmpty()) {

			Node<?> n = stack.pop();
			list.add(n);

			if (n.left != null || n.right != null) {

				if (n.right != null) {
					stack.push(n.right);
				}

				if (n.left != null) {
					stack.push(n.left);
				}
				
			} else {
				
				System.out.println(list);

				if(!stack.isEmpty()){
					while(!list.isEmpty()){
						Node<?> p = list.getLast();
						if(p.left != stack.peek() && p.right != stack.peek()){
							list.removeLast();
						} else {
							break;
						}
					}
				}
				
			}
		}

	}

}
