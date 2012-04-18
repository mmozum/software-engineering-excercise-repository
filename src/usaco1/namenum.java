/*
ID: jastina1
LANG: JAVA
TASK: namenum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class namenum {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		
		char[] input = br.readLine().toCharArray();
		br.close();
		
		// load dictionary
		br = new BufferedReader(new FileReader("dict.txt"));
		HashSet<String> dict = new HashSet<String>();
		
		String line = null;
		while( (line = br.readLine()) != null){
			
			if(line.length() == input.length)
				dict.add(line);
		}
		br.close();
		
		HashSet<String> words = new HashSet<String>();
		
		char[][] table= {
				{'A','B','C'},
				{'D','E','F'},
				{'G','H','I'},
				{'J','K','L'},
				{'M','N','O'},
				{'P','R','S'},
				{'T','U','V'},
				{'W','X','Y'},
		};
		
		Character[][] board = new Character[input.length][];
		
		for(int i = 0; i < input.length; i ++){
			
			LinkedList<Character> list = new LinkedList<Character>();
			char [] candidate = table[ input[i] - '2'];
			
			for(char c : candidate){
				for(String s : dict){
					if(s.charAt(i) == c){
						list.add(c);
						break;
					}
				}
			}
			
			board[i] = list.toArray(new Character[0]);
		}
		
		int[] result = new int[ input.length ];
		result[0] = 0;
		
		int p = 0;
		while(p >= 0){
			
			if(result[p] >= board[p].length){
				p --;
				if(p >= 0)
					result[p]++;
				continue;
			}

			if(p == board.length - 1){
				String word = "";
				for(int i = 0; i < board.length; i ++){
					word += board[i][ result[i] ];
				}

				if(dict.contains(word)){
					words.add(word);
				}
				
				result[p] ++;
				
			} else if(p < board.length - 1){
				result[ ++p ] = 0;
			}
		}
		
		//int result = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		//pw.println(result);
		
		List<String> list = new ArrayList<String>(words);
		Collections.sort(list);
		
		for(String s : list){
			System.out.println(s);
			pw.println(s);
		}
		
		if(words.isEmpty()){
			System.out.println("NONE");
			pw.println("NONE");
		}
		pw.close();
		System.exit(0);

	}

}
