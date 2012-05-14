package ihasleetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {

	public static void main(String[] args) {

		//int[] in = {2,1,2};
		int size = Integer.MAX_VALUE / 100;
		
		int[] in = new int[size];
		Math.ceil(1.5);
		System.out.println(largestRectangleArea(in));

	}
	
	
	// this is the second version that i've written
	static public int largestRectangleArea(int[] height) {
		
		if(height == null || height.length == 0){
			return 0;
		}
		
		int max = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < height.length; i ++){
			
			int lastIndex = i;
			while(!stack.isEmpty() && height[i] < height[stack.peek()]){
				lastIndex = stack.pop();
				int area = height[lastIndex] * (i - lastIndex);
				if(area > max){
					max = area;
				}
				
				height[lastIndex] = height[i];
			}
			
			if(stack.isEmpty() || height[lastIndex] > height[stack.peek()]){
				stack.push(lastIndex);
				continue;
			}
		}
		
		while(!stack.isEmpty()){
			int index = stack.pop();
			int area = height[index] * (height.length - index);
			if(area > max){
				max = area;
			}
		}
		
		return max;
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
