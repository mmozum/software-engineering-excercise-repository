import java.util.Arrays;
import java.util.Comparator;


public class AverageOut {

	
	public static void main(String[] args){
		
		int N = 11;
		Integer[] arr = new Integer[N];
		
		for(int i = 0; i < N; i ++){
			arr[i] = i + 1;
		}
		
		reorder(arr);
		
		System.out.println(Arrays.toString(arr));
	}

	private static void reorder(Integer[] arr) {
		
		C cmp = new C();
		cmp.bitMask = 1 << 30;
		
		while(cmp.bitMask > 0){
			Arrays.sort(arr, cmp);
			cmp.bitMask >>= 1;
		}
		
	}
	
	
	static class C implements Comparator<Integer>{
		
		int bitMask = 1;

		@Override
		public int compare(Integer arg0, Integer arg1) {
			int i = arg0;
			int j = arg1;
			
			return (i & bitMask) - (j&bitMask);
		}
		
	}

}


