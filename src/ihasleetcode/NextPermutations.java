package ihasleetcode;

public class NextPermutations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1,1};
		
		nextPermutation(in);

	}
	
	
	static public void nextPermutation(int[] num) {
		
		int start = num.length - 2;
		
		while(start >= 0 && num[start] >= num[start + 1]){
			start --;
		}
		
		if(start >= 0){
			for(int i = num.length - 1; ; i --){
				if(num[i] > num[start]){
					num[i] += num[start];
					num[start] = num[i] - num[start];
					num[i] -= num[start];
					break;
				}
			}
		}
		
		for(int i = start + 1, j = num.length - 1; i < j; i ++, j --){
			num[i] += num[j];
			num[j] = num[i] - num[j];
			num[i] -= num[j];
		}
	}
	
//    static public void nextPermutation(int[] num) {
//        
//        boolean done = false;
//        
//        for(int i = num.length - 2; i >= 0; i --){
//            
//            if(num[i] < num[i+1]){
//                
//                // reorder from i to N -1
//                for(int j = i + 1, k = num.length - 1; j < k; j ++, k --){
//                    int tmp = num[j];
//                    num[j] = num[k];
//                    num[k] = tmp;
//                }
//                
//                for(int j = i + 1; j < num.length; j++){
//                    
//                    if(num[j] > num[i]){
//                        int tmp = num[i];
//                        num[i] = num[j];
//                        num[j] = tmp;
//                        break;
//                    }
//                }
//                
//                
//                done = true;
//                break;
//            }
//        }
//        
//        if(!done){
//            for(int i = 0, j = num.length - 1; i < j; i ++, j --){
//                int tmp = num[i];
//                num[i] = num[j];
//                num[j] = tmp;
//            }
//        }
//        
//    }
}
