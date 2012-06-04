package common.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructBSTFromLeveledPrint {

	
	public static void main(String[] args){
		
		Integer[][] in = {
				{5},
				{4,8},
				{2,7,9},
				{3,10},
		};
		
		Node<Integer> tree = toBST(in);
		BTreePrinter.printNode(tree);
	}

	static class Range{
		int hi,lo;
		Node<Integer> node;
		public Range(Node<Integer> node) {
			this.node = node;
		}
	}
	
	public static Node<Integer> toBST(Integer[][] inputArr) {
		
		if(inputArr == null || inputArr.length == 0 || inputArr[0].length != 1){
			return null;
		}
		
		Node<Integer> root = new Node<Integer>(inputArr[0][0]);
		Range range = new Range(root);
		range.hi = Integer.MAX_VALUE;
		range.lo = Integer.MIN_VALUE;
		
		Queue<Range> queue = new LinkedList<Range>();
		queue.add(range);
		
		int level = 1;
		int index = 0;
		
		while(level < inputArr.length && index < inputArr[level].length && !queue.isEmpty()){
			
			Range r = queue.element();
			int val = inputArr[level][index];
			
			if(val < r.lo || val > r.hi){
				queue.remove();
				continue;
			}
			
			Node<Integer> node = new Node<Integer>(val);
			if(val <= r.node.data){
				r.node.left = node;
				Range rr = new Range(node);
				rr.hi = r.node.data;
				rr.lo = r.lo;
				queue.offer(rr);
			} else {
				r.node.right = node;
				Range rr = new Range(node);
				rr.lo = r.node.data;
				rr.hi = r.hi;
				queue.offer(rr);
			}
			
			if(++index >= inputArr[level].length){
				level ++;
				index = 0;
				queue.remove();
			}
		}
		
		
		return root;
		
	}

}
