package ihasleetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1};
		System.out.println(largestRectangleArea(in));

	}
	

    static public int largestRectangleArea(int[] height) {
    	
    	if(height == null || height.length == 0){
    		return 0;
    	}
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	int maxArea = 0;
    	
    	for(int i = 0; i < height.length; i ++){
    		
    		if(stack.empty() || height[i] > height[stack.peek()]){
    			stack.push(i);
    		} else if(height[i] < height[stack.peek()]){
    			
    			int index = 0;
    			while(!stack.empty() && height[i] < height[stack.peek()]){
    				index = stack.pop();
    				int area = (i - index) * height[index];
    				if(area > maxArea){
    					maxArea = area;
    				}
    			}
    			
    			height[index] = height[i];
    			stack.push(index);
    		}
    		
    	}
    	
    	while(!stack.empty()){
    		int index = stack.pop();
    		int area = (height.length - index) * height[index];
    		if(area > maxArea){
    			maxArea = area;
    		}
    	}
    	
    	return maxArea;

    }

}
