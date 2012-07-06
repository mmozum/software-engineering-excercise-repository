package common.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
��һ���ֵ䣬д��������ֵ��ﵥ�ʵ����и��ʣ��������һ���µĵ��ʡ�������
��<ap, abc, app>��a֮�����p�ĸ�����2/3��b֮�����c�ĸ�����100%��
 */
public class GenerateWordAccordingToDict {

	public static void main(String[] args){
		List<String> set = new ArrayList<String>();
		set.add("ap");
		set.add("abc");
		set.add("app");
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int N = 10000;
		for(int i = 0; i < N; i ++){
			String str = gen(set);
			if(!map.containsKey(str)){
				map.put(str, 0);
			}
			
			map.put(str, map.get(str) + 1);
		}
		
		System.out.println("total run = " + N);
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		
	}
	
	static String gen(List<String> list){
		
		if(list.isEmpty()){
			return "";
		}
		
		char c = '\0';
		int sum = 0;
		Random rand = new Random();
		
		HashMap<Character, List<String>> map = new HashMap<Character, List<String>>();
		
		for(String str : list){
			sum ++;
			
			char firstChar = '\0';
			String restStr = "";
			if(str.length() > 0){
				firstChar = str.charAt(0);
				restStr = str.substring(1);
			}
			if(rand.nextInt(sum) < 1){
				c = firstChar;
			}
			
			if(map.containsKey(firstChar)){
				map.get(firstChar).add(restStr);
			} else {
				List<String> l = new ArrayList<String>();
				l.add(restStr);
				map.put(firstChar, l);
			}
			
		}
		
		if(c == '\0'){
			return "";
		}
		
		return c + gen(map.get(c));
	}
}
