/*
ID: jastina1
LANG: JAVA
TASK: friday
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class friday {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("friday.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		
		int N = Integer.parseInt(br.readLine());
		
		int startDateOfAMonth = 2; // 0 represents Saturday
		// 1 Sunday
		// 2 Monday
		
		int[] hit = new int[7];
		
		for(int year = 1900; year < 1900 + N; year ++){
			
			for(int month = 1; month <= 12; month ++){
				hit[ whichDay(startDateOfAMonth, 12)] ++;
				
				startDateOfAMonth = whichDay(startDateOfAMonth, daysOfAMonthInAYear(year, month));
			}

		}

		for(int i = 0; i < 6; i ++){
			pw.print(hit[i] + " ");
		}
		
		pw.println(hit[6]);
		
		pw.close();
		System.exit(0);

	}
	
	private static int whichDay(int startDate, int interval){
		return (startDate + interval) % 7;
	}
	
	private static boolean isLeapYear(int year){
		
		boolean result = false;
		
		if(year % 100 == 0){
			if(year % 400 == 0){
				result = true;
			}
		} else if(year % 4 == 0){
			result = true;
		} 
		
		return result;
	}
	
	private static int daysOfAMonthInAYear(int year, int month){
		int[] set1 = {1, 3, 5, 7, 8, 10, 12};
		int[] set2 = {4, 6, 9, 11};
		
		for(int m : set1){
			if(m == month){
				return 31;
			}
		}
		
		for(int m : set2){
			if(m == month){
				return 30;
			}
		}
		
		if(isLeapYear(year)){
			return 29;
		}
		
		return 28;
	}

}
