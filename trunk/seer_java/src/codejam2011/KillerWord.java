package codejam2011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class KillerWord {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");
		
		int T = scanner.nextInt();scanner.nextLine();

		for (int t = 1; t <= T; t++) {
			
			int N = scanner.nextInt();
			int M = scanner.nextInt();scanner.nextLine();
			
			List<String> dictList = new ArrayList<String>(N);
			Hashtable<Integer, Set<String>> table = new Hashtable<Integer, Set<String>>();

			for(int i = 0; i < N; i++){
				String word = scanner.nextLine();
				dictList.add(word);
				
				Set<String> list = null;
				if(table.containsKey(word.length())){
					list = table.get(word.length());
				} else {
					list = new HashSet<String>();
					table.put(word.length(), list);
				}
				
				list.add(word);
			}
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < M; i ++){

				String guessStr = scanner.nextLine();
				
				int maxLost = -1;
				String maxCandidate = null;
				
				for(String target : dictList){
					
					Set<Character> answerSet = new HashSet<Character>();
					for(char c : target.toCharArray()){
						answerSet.add(c);
					}
					int toGo = answerSet.size();
					int lost = 0;

					Set<String> candidateWordSet = new HashSet<String>(table.get(target.length()));
					Set<Character> candidateCharSet = toCharSet(candidateWordSet);
					
					char [] guess = new char[target.length()];
					Arrays.fill(guess, '?');
					
					for(int j = 0; toGo > 0 && j <guessStr.length(); j ++){
						
						char c = guessStr.charAt(j);
						
						if(!candidateCharSet.contains(c)){
							continue;
						}
						
						Set<String> workingSet = new HashSet<String>(candidateWordSet);
						
						if(answerSet.contains(c)){
							
							toGo --;
							
							for(int k = 0; k < target.length(); k ++){
								if(target.charAt(k) == c){
									guess[k] = c;
								}
							}
							
							for(String s : workingSet){
								
								if(!matches(s, guess)){
									candidateWordSet.remove(s);
								}
							}
							candidateCharSet = toCharSet(candidateWordSet);
							
							
							continue;
						}
						
						lost ++;
						
						for(String s : workingSet){
							
							if(s.indexOf(c) >= 0){
								candidateWordSet.remove(s);
							}
						}
						
						candidateCharSet = toCharSet(candidateWordSet);
					}
					
					if(lost > maxLost){
						maxLost = lost;
						maxCandidate = target;
					} else if(lost == maxLost && target.compareTo(maxCandidate) < 0){
						maxLost = lost;
						maxCandidate = target;
					}
				}
				
				sb.append(maxCandidate + " ");
				
			}// for each guess strategy
			
			sb.deleteCharAt(sb.length() - 1);
			
			out.format("Case #%d: %s\n", t,  sb.toString());
		}

		scanner.close();
		out.close();
	}

	private static Set<Character> toCharSet(Set<String> sSet) {


		Set<Character> set = new HashSet<Character>();
		
		for(String s : sSet){
			for(char c : s.toCharArray()){
				set.add(c);
			}
		}
		return set;
	}

	private static boolean matches(String s, char[] guess) {


		for(int i = 0; i < guess.length; i ++){
			if(guess[i] != '?' && guess[i] != s.charAt(i)){
				return false;
			}
		}
		return true;
	}


	static class Output {

		PrintWriter pw;

		public Output(String filename) throws IOException {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		}

		public void print(String s) {
			pw.print(s);
			System.out.print(s);
		}

		public void println(String s) {
			pw.println(s);
			System.out.println(s);
		}
		
		public void format(String format, Object...args){
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
