package ihasleetcode;

import java.util.LinkedList;

public class JumpGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1, 2};
		
		System.out.println(jump(arr));

	}
	
	static class Item{
		int index;
		int count;
		
		Item(int i, int c){
			index = i;
			count = c;
		}
	}
	// Jump Game II
    static public int jump(int[] A) {

        if(A == null || A.length < 2){
            return 0;
        }
        
        LinkedList<Item> list = new LinkedList<Item>();
        list.add(new Item(A[0], 1));
        
        for(int i = 0; i < A.length; i ++){
        	
        	Item last = list.getLast();
        	Item top = list.removeFirst();
        	while(!list.isEmpty() && top.index < i){
        		top = list.removeFirst();
        	}
        	
        	
        	if(i + A[i] > last.index){
        		Item newItem = new Item(i + A[i], top.count + 1);
        		list.addLast(newItem);
        	}
        	
        	list.addFirst(top);
        }

        while(!list.isEmpty()){
        	
        	Item item = list.removeFirst();
        	
        	if(item.index >= A.length - 1){
        		return item.count;
        	}
        }
        return -1;
        
    }
	
	// Jump Game I
    public boolean canJump(int[] A) {
        if(A == null || A.length < 2){
            return true;
        }
        
        int current = 0;
        int max = A[current];
        
        while(current <= max && max < A.length - 1){
            
            int newMax = 0;
            for(; current <= max; current ++){
                int tmp = current + A[current];
                if(tmp > newMax){
                    newMax = tmp;
                }
            }
            
            if(newMax > max){
                max = newMax;
            }
        }
        
        
        return max >= A.length - 1;
    }
}
