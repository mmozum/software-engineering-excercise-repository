package common.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given pre-order and post-order sequences of {string node_value, bool is_leaf
 * } of a GENERAL tree, reconstruct the tree, HOWEVER, "node_value" CANNOT be
 * used to identify a node (i.e., there could be duplicate node values).
 * 
 */

public class ConstructTreeFromPreorderPostorder {

	static class Node {
		// data fields
		String nodeValue;
		boolean isLeaf;

		// tree related fields
		Node left, right;
		int total;

		public Node(String nodeValue, boolean isLeaf) {
			this.nodeValue = nodeValue;
			this.isLeaf = isLeaf;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result + (isLeaf ? 1 : 0);
			result = 31 * result + nodeValue.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Node)){
				return false;
			}
			Node n = (Node)obj;
			return n.nodeValue.equals(nodeValue) && n.isLeaf == isLeaf;
		}

		@Override
		public String toString() {
			return String.format("(%s, l: %s, r: %s)", 
					nodeValue, 
					(left == null) ? "null" : left.nodeValue,
					(right == null) ? "null" : right.nodeValue	);
		}

	}
 

	/*
	 *               1 
	 *              / \ 
	 *             /   \
	 *            /     \ 
	 *           /       \
	 *          2         7 
	 *         / \         \
	 *        /   \         \ 
	 *       3     4         8 
	 *      / \             /
	 *     5   6           9
	 */
	public static void main(String[] args) {
		/*
		Node n1 = new Node("1", false);
		Node n2 = new Node("1", false);
		Node n3 = new Node("1", false);
		Node n4 = new Node("2", true);
		Node n5 = new Node("2", true);
		Node n6 = new Node("2", true);
		Node n7 = new Node("1", false);
		Node n8 = new Node("1", false);
		Node n9 = new Node("2", true);
//		Node n1 = new Node("1", false);
//		Node n2 = new Node("2", false);
//		Node n3 = new Node("3", false);
//		Node n4 = new Node("4", true);
//		Node n5 = new Node("5", true);
//		Node n6 = new Node("6", true);
//		Node n7 = new Node("7", false);
//		Node n8 = new Node("8", false);
//		Node n9 = new Node("9", true);
		
		ArrayList<Node> preorder = new ArrayList<Node>();
		ArrayList<Node> postorder = new ArrayList<Node>();
		
		preorder.add(n1);
		preorder.add(n2);
		preorder.add(n3);
		preorder.add(n5);
		preorder.add(n6);
		preorder.add(n4);
		preorder.add(n7);
		preorder.add(n8);
		preorder.add(n9);
		postorder.add(n5);
		postorder.add(n6);
		postorder.add(n3);
		postorder.add(n4);
		postorder.add(n2);
		postorder.add(n9);
		postorder.add(n8);
		postorder.add(n7);
		postorder.add(n1);
*/		
		Node n1 = new Node("1", false);
		Node n2 = new Node("1", false);
		Node n3 = new Node("1", false);
		Node n4 = new Node("2", true);
		
		ArrayList<Node> preorder = new ArrayList<Node>();
		ArrayList<Node> postorder = new ArrayList<Node>();
		
		preorder.add(n1);
		preorder.add(n2);
		preorder.add(n3);
		preorder.add(n4);
		postorder.add(n4);
		postorder.add(n3);
		postorder.add(n2);
		postorder.add(n1);
		reconstruct(preorder, postorder);

	}

	static void reconstruct(ArrayList<Node> preorder,
			ArrayList<Node> postorder) {
		assert(preorder != null && preorder.size() > 0);
		assert(postorder != null && postorder.size() > 0);
		assert(postorder.size() == postorder.size());
		
		List<Node> list = reconstruct(preorder, 0, preorder.size(),
				postorder, 0, postorder.size());
		
		for(Node tree : list){
			System.out.println("=================");
			printTree(tree);
		}
	}

	private static void printTree(Node tree) {
		
		if(tree == null){
			return;
		}
		
		System.out.println(tree);
		printTree(tree.left);
		printTree(tree.right);
		
	}

	private static List<Node> reconstruct(ArrayList<Node> preorder, int s1,
			int e1, ArrayList<Node> postorder, int s2, int e2) {
		assert (e1 - s1 == e2 - s2);

		int len = e1 - s1;

		List<Node> result = new ArrayList<Node>();

		if (len == 0 || !preorder.get(s1).equals(postorder.get(e2-1))) {
			return result;
		}

		Node root = preorder.get(s1);
		if (root.isLeaf == true) {
			if (len == 1) {
				Node n = new Node(root.nodeValue, root.isLeaf);
				n.total = 1;
				result.add(n);
			}

			return result;
		}

		s1++;
		e2--;

		for (int l = 0; l < len; l++) {
			
			if (!hasSameNodes(preorder, s1, s1 + l, postorder, s2, s2 + l)) {
				continue;
			}

			List<Node> leftList = reconstruct(preorder, s1, s1 + l, postorder,
					s2, s2 + l);
			List<Node> rightList = reconstruct(preorder, s1 + l, e1, postorder,
					s2 + l, e2);

			if (l == 0) {
				for (Node n : rightList) {

					if (n.total != len-1) {
						continue;
					}
					Node rootCopy = new Node(root.nodeValue, root.isLeaf);
					rootCopy.total = n.total + 1;
					rootCopy.right = n;
					result.add(rootCopy);
				}
			} else if (l == len - 1) {
				for (Node n : leftList) {

					if (n.total != l) {
						continue;
					}
					Node rootCopy = new Node(root.nodeValue, root.isLeaf);
					rootCopy.total = n.total + 1;
					rootCopy.left = n;
					result.add(rootCopy);
				}
			} else {
				for (Node lc : leftList) {

					if (lc.total != l) {
						continue;
					}
					for (Node rc : rightList) {

						if (rc.total != len - l - 1) {
							continue;
						}
						Node rootCopy = new Node(root.nodeValue, root.isLeaf);
						rootCopy.total = lc.total + rc.total + 1;
						rootCopy.left = lc;
						rootCopy.right = rc;
						result.add(rootCopy);
					}

				}
			}

		}
		
		return result;

	}

	// Can use memoization to speed up
	private static boolean hasSameNodes(ArrayList<Node> preorder, int s1,
			int e1, ArrayList<Node> postorder, int s2, int e2) {
		
		System.out.println(String.format("%d_%d_%d_%d", s1,e1,s2,e2));
		
		HashMap<Node, Integer> map1 = new HashMap<Node, Integer>();
		
		for(int i = s1; i < e1; i ++){
			Node n = preorder.get(i);
			if(!map1.containsKey(n)){
				map1.put(n, 0);
			}
			
			map1.put(n, map1.get(n) + 1);
		}
		
		HashMap<Node, Integer> map2 = new HashMap<Node, Integer>();
		
		for(int i = s2; i < e2; i ++){
			Node n = postorder.get(i);
			if(!map2.containsKey(n)){
				map2.put(n, 0);
			}
			
			map2.put(n, map2.get(n) + 1);
		}
		return map1.equals(map2);
	}

}
