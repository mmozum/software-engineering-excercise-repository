import java.util.Arrays;


public class Tmp {

	
	public static void main(String[] args){
		
		int[][] tests = {
				{0},
				{1},
				{2},
				{1, 0},
				{0, 1},
				{0, 2},
				{2, 0},
				{1, 2},
				{2, 1},
				{1, 1, 2, 2, 0},
				{1, 2, 0, 0, 2, 1, 2, 2, 1},
		};
		
		for(int i = 0; i < tests.length; i ++){
			System.out.println("=========");
			System.out.println(Arrays.toString(tests[i]));
			dutchFlag(tests[i], tests[i].length);
			System.out.println(Arrays.toString(tests[i]));
		}
	}
	
	static void dutchFlag (int A[], int n)
	{
	    int red = -1;
	    int blue = n;
	    int white = 0;
	    while (white < blue) {
	        if (is_red (A[white])) {
	        	int tmp = A[white];
	        	A[white] = A[red + 1];
	        	A[red + 1] = tmp;
	           white ++;
	           red ++;
	        } else if (is_blue (A[white])) {
	           int tmp = A[white];
	           A[white] = A[blue - 1];
	           A[blue - 1] = tmp;
	           blue --;
	        } else {
	           white ++;
	        }
	    }
	}

	static private boolean is_blue(int i) {
		return i == 2;
	}

	static private boolean is_red(int i) {
		return i == 0;
	}
	
	
}
