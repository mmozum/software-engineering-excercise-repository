import java.util.Arrays;


public class Tmp {

	
	public static void main(String[] args){
		
		System.out.println(Math.atan(1.2));
		System.out.println(Math.toDegrees(Math.atan(1.2)));
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
