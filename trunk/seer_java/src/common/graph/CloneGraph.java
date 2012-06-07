package common.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Node> graphs = Util.getGraphs();
		
		for(Node g : graphs){
			System.out.println("Original graph:");
			Util.printGraph(g);
			System.out.println("Copied graph:");
			Node copy = cloneGraph_queue(g);
			Util.printGraph(copy);
			System.out.println("Copied graph 2:");
			copy = cloneGraph_recursive(g);
			Util.printGraph(copy);
		}

	}

	public static Node cloneGraph_queue(Node originalGraph){
		
		if(originalGraph == null){
			return null;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		
		Node copy = new Node(originalGraph.getLabel());
		queue.add(originalGraph);
		map.put(originalGraph, copy);
		
		while(!queue.isEmpty()){
			Node cur = queue.poll();
			
			for(Node nei : cur.getAllNeighbors()){
				if(!map.containsKey(nei)){
					copy = new Node(nei.getLabel());
					map.put(nei, copy);
					assert(!queue.contains(nei));
					queue.add(nei);
				}
				
				map.get(cur).addNeighbor(
						map.get(nei));
			}
		}
		
		return map.get(originalGraph);
	}
	
	public static Node cloneGraph_recursive(Node originalGraph){
		
		if(originalGraph == null){
			return null;
		}
		
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Set<Node> set = new HashSet<Node>();
		
		Node copy = new Node(originalGraph.getLabel());
		
		map.put(originalGraph, copy);
		
		cloneGraph_dfs(originalGraph, set, map);
		
		return map.get(originalGraph);
	}

	private static void cloneGraph_dfs(Node cur, Set<Node> set,
			HashMap<Node, Node> map) {
		
		for(Node nei : cur.getAllNeighbors()){
			if(!map.containsKey(nei)){
				Node copy = new Node(nei.getLabel());
				map.put(nei, copy);
			}
			
			map.get(cur).addNeighbor(map.get(nei));
			
		}

		assert(!set.contains(cur));
		set.add(cur);
		
		for(Node nei : cur.getAllNeighbors()){
			if(!set.contains(nei) && !cur.equals(nei)){
				cloneGraph_dfs(nei, set, map);
			}
		}
	}
}
