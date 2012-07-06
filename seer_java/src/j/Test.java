package j;

import java.util.Arrays;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] i = {1, 1, 1, 1, 1, 1};
		int k;
		do {
		  // do your stuff with i[0] to i[7]
			System.out.println(Arrays.toString(i));
		  for (k = 0; k < i.length && ++i[k] > 4; i[k++] = 1);
		} while (k < i.length);


	}

}
