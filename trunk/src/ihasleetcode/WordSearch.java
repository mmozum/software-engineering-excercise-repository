package ihasleetcode;

public class WordSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public boolean exist(char[][] board, String word) {

		if(word == null || word.length() == 0){
			return true;
		}
		
		char c = word.charAt(0);
		
		for(int i = 0; i < board.length; i ++){
			for(int j = 0; j < board[i].length; j ++){
				if(board[i][j] ==c){
					
					board[i][j] = 0;
					if(dfs(board, i, j, word, 1)){
						return true;
					}
					board[i][j] = c;
					
				}
			}
		}
		
		return false;
    }

	private boolean dfs(char[][] board, int row, int col, String word, int k) {

		if(k == word.length()){
			return true;
		}
		
//		int[][] dir = { {-1,-1}, {-1,0}, {-1,1}, {0,1}, // for 8 directions
//				{1,1},{1,0},{1,-1},{0,-1}};
		int[][] dir = { {-1,0},  {0,1},	{1,0},{0,-1}};
		char c = word.charAt(k);
		int M = board.length;
		int N = board[0].length;
		
		for(int[] d : dir){
			int i = row + d[0];
			int j = col + d[1];
			
			if(0 <= i && i < M && 0 <= j && j < N && board[i][j] == c){
				board[i][j] = 0;
				if(dfs(board, i, j, word, k+1)){
					return true;
				}
				board[i][j] = c;
			}
		}
		return false;
	}


}
