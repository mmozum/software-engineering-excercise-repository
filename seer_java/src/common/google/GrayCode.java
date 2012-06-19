package common.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目其实很简单，写一个方程生成gray code并按顺序输出相应的数字。 比如grayCode(3) = {0,1,3,2,6,7,5,4}
 */
public class GrayCode {

	public static void main(String[] args){
		List<Integer> list = grayCode(3);
		System.out.println(list);
	}

	private static List<Integer> grayCode(int n) {
		if(n < 0){
			return null;
		}
		
		List<Integer> list = new ArrayList<Integer>();
		if(n == 0){
			return list;
		}
		
		assert(n >= 1);
		list.add(0);
		list.add(1);
		
		for(int i = 1; i < n; i ++){
			for(int j = list.size() - 1; j >= 0; j --){
				int code = list.get(j);
				list.add( code | (1 << i) );
			}
		}
		return list;
	}
}
