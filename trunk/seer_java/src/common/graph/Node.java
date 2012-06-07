package common.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic graph node class
 * @author Jason Huang
 */
public class Node {

	protected String label;
	protected List<Node> neighbors = new ArrayList<Node>();
	
	public Node(String label) {
		this.label = label;
	}
	
	public List<Node> getAllNeighbors(){
		return neighbors;
	}
	
	public void addNeighbor(Node ... nodes){
		
		for(Node node : nodes){
			
			if(neighbors.contains(node)){
				throw new IllegalArgumentException("Cannot add an existing neighbor: " + node);
			}
			
			neighbors.add(node);
		}
	}
	
	public String getLabel(){
		return label;
	}
	
	@Override public String toString(){
		return String.format("%s (%s)", label, super.toString());
	}
	
}
