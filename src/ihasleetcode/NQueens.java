package ihasleetcode;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The n-queens puzzle is the problem of placing n 
 * queens on an n×n chessboard such that no two queens 
 * attack each other.
 */

/**
 * @author Jason Huang
 * 
 */
public class NQueens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		solveNQueens(5);

	}

	public static ArrayList<String[]> solveNQueens(int n) {

		int[] board = new int[n];
		
		board[0] = 0;
		
		int current = 0;
		
		while(board[0] < n){
			
			for(int i = 0; i < current && board[current] < n;){
				
				if(board[i] == board[current] || Math.abs(current - i) == Math.abs(board[current] -board[i])){
					board[current] ++;
					i = 0;
				} else {
					i ++;
				}
			}
			
			if(board[current] >= n){
				board[--current]++;
				continue;
			} else if(current == n - 1){
				System.out.println(Arrays.toString(board));
				board[current] ++;
				continue;
			} else if(board[current] < n){
				board[++current] = 0;
			}

		}
		
		return null;

	}
}
