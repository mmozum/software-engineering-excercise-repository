package ihasleetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {

	public static void main(String[] args) {

		int[] in = {2,1,2};
		System.out.println(largestRectangleArea(in));

	}
	
	static class Item{
		int index, height;

		public Item(int index, int height) {
			super();
			this.index = index;
			this.height = height;
		}
		
	}
	
	static public int largestRectangleArea(int[] height) {
		
		Stack<Item> stack = new Stack<Item>();
		
		int maxArea = 0;
		for(int i = 0; i < height.length; i ++){
			
			if(stack.isEmpty() || height[i] > stack.peek().height){
				stack.push(new Item(i, height[i]));
				continue;
			}
			
			Item item = null;
			while(!stack.isEmpty() && height[i] < stack.peek().height){
				item = stack.pop();
				int area = item.height * (i - item.index);
				if(area > maxArea){
					maxArea = area;
				}
			}
			
			if(item != null){
				item.height = height[i];
				stack.push(item);
			}
		}
		
		while(!stack.isEmpty()){
			Item item = stack.pop();
			int area = (height.length - item.index) * item.height;
			if(area > maxArea){
				maxArea = area;
			}
		}
		
		return maxArea;
	}

	
//    // this is the first version that i've written
//    static public int largestRectangleArea(int[] height) {
//    	
//    	if(height == null || height.length == 0){
//    		return 0;
//    	}
//    	
//    	Stack<Integer> stack = new Stack<Integer>();
//    	
//    	int maxArea = 0;
//    	
//    	for(int i = 0; i < height.length; i ++){
//    		
//    		if(stack.empty() || height[i] > height[stack.peek()]){
//    			stack.push(i);
//    		} else if(height[i] < height[stack.peek()]){
//    			
//    			int index = 0;
//    			while(!stack.empty() && height[i] < height[stack.peek()]){
//    				index = stack.pop();
//    				int area = (i - index) * height[index];
//    				if(area > maxArea){
//    					maxArea = area;
//    				}
//    			}
//    			
//    			height[index] = height[i];
//    			stack.push(index);
//    		}
//    		
//    	}
//    	
//    	while(!stack.empty()){
//    		int index = stack.pop();
//    		int area = (height.length - index) * height[index];
//    		if(area > maxArea){
//    			maxArea = area;
//    		}
//    	}
//    	
//    	return maxArea;
//
//    }

}
