package ihasleetcode;

public class ContainerWithMostWater {


	public static void main(String[] args) {

		int[] in = {3,2,1,3};
		System.out.println(maxArea(in));

	}
	

    static public int maxArea(int[] height) {

        if(height == null || height.length < 2){
    		return 0;
    	}
        
        int start = 0;
        int end = height.length - 1;
        
        int max = getArea(height, start, end);
        while(start < end - 1){
        	
        	int pStart = start;
        	int pEnd = end;
        	int direction = 1;
        	if(height[start] > height[end]){
        		direction = -1;
        		pStart = end;
        		pEnd = start;
        	}
        	
        	int currentHigh = height[pStart];
        	for(;pStart != pEnd && height[pStart] <= currentHigh; pStart += direction){
        		
        	}
        	
        	if(pStart != pEnd){
        		if(direction == 1){
        			start = pStart;
        		} else {
        			end = pStart;
        		}
        		
        		int tmpMax = getArea(height, start, end);
        		
        		if(tmpMax > max){
        			max = tmpMax;
        		}
        	} else {
        		break;
        	}
        	
        	
        }
        return max;
    }
    
    
    
    static int getArea(int[] height, int i, int j){
        return Math.min(height[i], height[j]) * Math.abs(i-j);
    }

}
