package common.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
����һ����άvector��ʵ�� flatten��
class flatten implements iterator{
  public flatten(vector<vector<int>> a);
  boolean hasNext();
  iterator next();
}
 */
public class IteratorV1 implements Iterator<Integer> {
	
	List<List<Integer>> list;
	int row, col;
	boolean dirty = true;
	
	public IteratorV1(List<List<Integer>> list){
		this.list = list;
		hasNext();
	}

	@Override
	public boolean hasNext(){
		
		if(dirty){
		
			col ++;
			while(row < list.size()){
				if(col >= list.get(row).size()){
					row ++;
					col = 0;
				} else {
					break;
				}
			}
			dirty = false;
		}
		
		return row < list.size();
	}
	
	@Override
	public Integer next(){
		
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		
		dirty = true;
		return list.get(row).get(col);
		
	}

	@Override
	public void remove(){
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		List<Integer> l1 = Arrays.asList(new Integer[]{1,3,5, 7});
		List<Integer> l2 = new LinkedList<Integer>();
		list.add(l2);
		list.add(l1);
		list.add(l2);
		list.add(l2);
		list.add(l2);
		list.add(l1);
		list.add(l1);
		
		IteratorV1 i1 = new IteratorV1(list);
		
		while(i1.hasNext()){
			i1.hasNext();
			System.out.println(i1.next());
		}

		i1.next();
	}

}
