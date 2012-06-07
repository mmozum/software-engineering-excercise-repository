package common.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Util {

	public static void main(String[] args) {
		
		List<Node> list = getGraphs();
		
		for(Node g : list){
			System.out.println("===============");
			printGraph(g);
		}

	}

	/**
	 * Generate graphs for testing purpose
	 * @return
	 */
	static List<Node> getGraphs(){
		
		List<Node> list = new ArrayList<Node>();
		
		// empty graph
		list.add(null);
		
		// single node graph
		list.add(new Node("A"));
		
		list.add(generateLoopGraph(1));
		list.add(generateLoopGraph(2));
		list.add(generateLoopGraph(4));
		
		list.add(getSampleGraph());
		
		return list;
	}
	
	static Node generateLoopGraph(int n){
		
		if(n == 0){
			return null;
		}
		
		int count = 1;
		Node head = new Node("" + count);
		Node cur = head;
		Node next = null;
		
		while(count++ < n){
			next = new Node("" + count);
			cur.addNeighbor(next);
			cur = next;
		}
		
		cur.addNeighbor(head);
		return head;
	}
	
	static Node getSampleGraph(){

		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		Node h = new Node("H");
		Node i = new Node("I");
		Node j = new Node("J");
		
		a.addNeighbor(b, c);
		b.addNeighbor(d);
		c.addNeighbor(h);
		d.addNeighbor(c, e);
		e.addNeighbor(f);
		g.addNeighbor(d, c, h, j);
		h.addNeighbor(i, a);
		
		return a;
	}
	
	/**
	 * Print graph as sorted adjacency list.
	 * @param node
	 */
	static void printGraph(Node node){
		
		if(node == null){
			System.out.println();
			return;
		}
		
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		Stack<Node> stack = new Stack<Node>();
		
		stack.push(node);
		
		while(!stack.isEmpty()){
			
			Node current = stack.pop();
			
			assert(!map.containsKey(current.getLabel()));
			
			List<String> list = new ArrayList<String>();
			
			for(Node n : current.getAllNeighbors()){
				list.add(n.toString());
				
				if(!map.containsKey(n.label) && !stack.contains(n) && !current.equals(n)){
					stack.push(n);
				}
			}
			
			Collections.sort(list);
			map.put(current.label, list);
			
		}
		
		// now print the graph
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		
		for(String s : keys){
			System.out.format("%s: %s\n", s, map.get(s));
		}
	}
}
