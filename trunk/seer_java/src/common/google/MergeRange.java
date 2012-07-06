package common.google;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 1:
 * 
Insert Interval
Given a set of non-overlapping intervals, insert a new interval into the
intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their
start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[
3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * 
 * 
 * Description 2:
 * 
 * Given a span array: {(3,5),(7,16), (23,78)...}
 * and a span: (10£¬19)
 * Merge it.
 * 
 * @author Jason Huang
 *
 */

class Range {
	int start;
	int end;
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return String.format("[%d, %d]", start, end);
	}
	
}

public class MergeRange {

	public static void main(String[] args) {
		List<Range> list = new ArrayList<Range>();
		list.add(new Range(1,2));
		list.add(new Range(3,5));
		list.add(new Range(6,7));
		list.add(new Range(8,10));
		list.add(new Range(12,16));
		Range range = new Range(4, 9);
		System.out.println(merge(list, range));

		list = new ArrayList<Range>();
		list.add(new Range(1,3));
		list.add(new Range(6,9));
		range = new Range(2, 6);
		System.out.println(merge(list, range));
		
		list = new ArrayList<Range>();
		list.add(new Range(6,9));
		list.add(new Range(12,13));
		range = new Range(1, 3);
		System.out.println(merge(list, range));
		
		list = new ArrayList<Range>();
		list.add(new Range(6,9));
		list.add(new Range(12,13));
		range = new Range(16, 20);
		System.out.println(merge(list, range));
		
		list = new ArrayList<Range>();
		list.add(new Range(6,9));
		list.add(new Range(12,13));
		range = new Range(7, 8);
		System.out.println(merge(list, range));
		
	}

	/**
	 * Insert a new range. Remove all ranges that're merged with the new one.
	 * @param list
	 * @param range
	 */
	static List<Range> merge(List<Range> list, Range range) {
		
		List<Range> result = new ArrayList<Range>();
		boolean search = true;
		int idx = 0;
		for(; idx < list.size(); idx ++){
			Range r = list.get(idx);
			if(search){
				if(r.end < range.start){
					result.add(r);
				} else if(range.end < r.start){
					break;
				} else {
					range.start = Math.min(range.start,r.start);
					range.end = Math.max(range.end,r.end);
					search = false;
				}
			} else {
				if(range.end < r.start){
					break;
				} else {
					range.end = Math.max(range.end, r.end);
				}
			}
		}
		
		result.add(range);
		for(; idx < list.size(); idx++){
			result.add(list.get(idx));
		}
		return result;
	}

}
