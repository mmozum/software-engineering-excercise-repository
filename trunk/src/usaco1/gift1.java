/*
ID: jastina1
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class gift1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		
		int NP = Integer.parseInt(br.readLine());
		System.out.println("NP = " + NP);
		
		Hashtable<String, Integer> nameTable = new Hashtable<String, Integer>();
		Item[] items = new Item[NP];
		
		for(int i = 0; i < NP; i ++){
			items[i] = new Item();
			items[i].name = br.readLine();
			nameTable.put(items[i].name, i);
			
			System.out.println("get name: " + items[i].name);
		}
		
		
		for(int i = 0; i < NP; i ++){
			String name = br.readLine();
			int index = nameTable.get(name);
			Item item = items[index];
			StringTokenizer st = new StringTokenizer(br.readLine());
			item.num_init_money = Integer.parseInt(st.nextToken());
			item.num_final_money = 0;
			item.friends = new int[Integer.parseInt(st.nextToken())];
			System.out.println("for " + name + " init money = " + item.num_init_money
				+ "num_friends = " + item.friends.length);
			
			for(int j = 0; j < item.friends.length; j ++){
				item.friends[j] = nameTable.get(br.readLine());
			}
		}
		br.close();
		
		for(int i = 0; i < NP; i ++){
			Item item = items[i];
			if(item.friends.length == 0){
				continue;
			}
			
			int toGive = item.num_init_money / item.friends.length;
			item.num_final_money += item.num_init_money % item.friends.length;
			
			for(int j = 0; j < item.friends.length; j ++){
				items[ item.friends[j] ].num_final_money += toGive;
			}
		}
		
		for(int i = 0; i < NP; i ++){
			pw.println(items[i].name + " " + (items[i].num_final_money - items[i].num_init_money));
		}
		pw.close();
		System.exit(0);

	}
	
	static class Item{
		String name;
		int num_init_money;
		int num_final_money;
		int[] friends;

	}

}
