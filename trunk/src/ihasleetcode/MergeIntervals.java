package ihasleetcode;

public class MergeIntervals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// l[i][j] = min{ l[i-1][j-1] + 1, l[i][j-1] + 1, l[i-1][j] + 1 }
    static public int minDistance(String word1, String word2) {
    	
    	final int M = word1.length();
    	final int N = word2.length();

    	int[][] table = new int[M+1][N+1];
    	
    	for(int i = 0; i <= M; i ++){
    		table[i][0] = i;
    	}
        for(int j=0; j <= N; j ++){
        	table[0][j] = j;
        }
        
        for(int i = 1; i <= M; i ++){
        	for(int j = 1; j <= N; j ++){
        		
        		if(word1.charAt(i-1) == word2.charAt(j-1)){
        			table[i][j] = table[i-1][j-1];
        		} else {
        			table[i][j] = Math.min(table[i-1][j-1], Math.min(table[i][j-1], table[i-1][j]))+1;
        		}
        	}
        }
        
        return table[M][N];
    }
}
