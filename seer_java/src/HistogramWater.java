/**
 * 
 */

/**
 * @author jjhuang
 *
 */
public class HistogramWater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        int[][] q = new int[][] {
	        { 3, 1, 5 },
	        { 3, 1, 0, 5 },
	        { 1, 2, 3 },
	        { 3, 2, 1 },
	        { 1, 2, 3, 2, 1 },
	        { 5, 4, 3, 6, 2, 3 },
	        { 3, 1, 8, 1, 9, 1, 5 } };
        
        int[] a = new int[]
        { 2, 5, 0, 0, 0, 4, 13 };

        for (int i = 0; i < q.length; i++)
        {
            int r = water(q[i]);
            if (a[i] == r)
                System.out.print("[Pass]");
            else
                System.out.print("[Fail]");

            System.out.println("expected:" + a[i] + " actual:" + r);
        }
    }

	/**
	 * Compute the final shape of the histogram when water is full
	 * @param histogram
	 * @return
	 */
	static int water(int[] histogram) {
		
		int[] fromLeft = new int[histogram.length];
		int[] fromRight = new int[histogram.length];
		
		// 1st round, looking from left
		fromLeft[0] = histogram[0];
		for(int i = 1; i < histogram.length; i++){
			fromLeft[i] = Math.max(fromLeft[i-1], histogram[i]);
		}
		
		// 2nd round, looking from right
		fromRight[histogram.length - 1] = histogram[ histogram.length - 1];
		for(int i = histogram.length - 2; i >= 0; i --){
			fromRight[i] = Math.max(fromRight[i+1], histogram[i]);
		}
		
		int area = 0;
		// 3rd round, compute the area
		for(int i = 1; i < histogram.length - 1; i ++){
			area += Math.min(fromLeft[i], fromRight[i]) - histogram[i];
		}
		return area;
	}

}
