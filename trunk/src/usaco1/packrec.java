package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: packrec
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class packrec {

	

	static final int N = 4;

	static int[][] rectList = new int[N][2];

	
	static int [] rectIndex = new int[N];
	static int [] rectRotateIndex = new int[N];
	
	static int smallest = Integer.MAX_VALUE;
	static Set<Rect> results = new HashSet<Rect>();
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
				"packrec.out")));


		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rectList[i][0] = Integer.parseInt(st.nextToken());
			rectList[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		// calculate the perm of 0..N-1
		
		int p1 = 0;
		
		next:
		while(p1 >= 0){

			for(int i = 0; i < p1 && rectIndex[p1] < N; i ++){
				
				if(rectIndex[i] == rectIndex[p1]){
						rectIndex[p1] ++;
						continue next;
				}
			}
			
			if(rectIndex[p1] >= N){
				rectIndex[p1--] = 0;
				if(p1 >= 0){
					rectIndex[p1] ++;
				}
			} else if(p1 == N - 1){
				rotate();
				rectIndex[p1] ++;
			} else {
				p1 ++;
			}
			
		}
		
		
		List<Rect> list = new ArrayList<Rect>(results);
		Collections.sort(list);
		
		System.out.println(smallest);
		pw.println(smallest);
		
		for(Rect r : list){
			System.out.println(r.a + " " + r.b);
			pw.println(r.a + " " + r.b);
		}
		
		pw.close();
		System.exit(0);

	}

	private static void rotate() {
		
		int p2 = 0;
		
		while(p2 >= 0){
			
			if(rectRotateIndex[p2] > 1){
				rectRotateIndex[p2--] = 0;
				if(p2 >= 0){
					rectRotateIndex[p2] ++;
				}
			} else if(p2 == N - 1) {
				//System.out.println(Arrays.toString(rectRotateIndex));
				arrange();
				rectRotateIndex[p2] ++;
			} else {
				p2 ++;
			}
		}
		
		
	}

	private static void arrange() {

		int[] xx = new int[6];
		int[] yy = new int[6];
		
		// arrange 0
		int x = 0, y = 0;
		int index = 0;
		
		for(int i = 0; i < N; i ++){
			x+= getx(i);
			y = Math.max(y, gety(i));
		}
		
		xx[index] = x;
		yy[index] = y;
		
		// arrange 1
		x = 0;
		y = 0;
		index = 1;
		for(int i = 1; i < N; i ++){
			x+= getx(i);
			y = Math.max(y, gety(i));
		}
		x = Math.max(x, getx(0));
		y += gety(0);
		
		xx[index] = x;
		yy[index] = y;
		
		// arrange 2
		index = 2;
		x = getx(0) + getx(1);
		y = Math.max(gety(0), gety(1));
		
		y += gety(2);
		x = Math.max(x, getx(2));
		
		x += getx(3);
		y = Math.max(y, gety(3));
		
		xx[index] = x;
		yy[index] = y;
		
		// arrange 3,4
		index = 3;
		
		x = Math.max(getx(0), getx(1));
		y = gety(0) + gety(1);
		x += getx(2);
		y = Math.max(y,  gety(2));
		x += getx(3);
		y = Math.max(y,  gety(3));
		
		xx[index] = x;
		yy[index] = y;
		
		// arrange 5, placed in 4
		index = 4;
		x = getx(0) + getx(1);
		y = Math.max(gety(0), gety(1));
		
		x = Math.max(x, getx(2));
		y += gety(2);
		
		int x0 = Math.max(getx(0), getx(2));
		int y0 = Math.max(gety(0), gety(1));
		if(getx(2) > getx(0) && gety(1) < gety(0)){
			y0 = Math.min(gety(0), gety(1));
		}
		
		x = Math.max(x, x0 + getx(3));
		y = Math.max(y, y0 + gety(3));
		
		xx[index] = x;
		yy[index] = y;
		
		for(index = 0; index < 5; index++){

			int area = xx[index] * yy[index];
			
			if(area < smallest){
				smallest = area;
				results.clear();
			}
			
			if(area == smallest){
				results.add(new Rect(xx[index], yy[index]));
			}
		}
		
		
		
	}
	
	private static int getx(int i){
		int p = rectIndex[i];
		int r = rectRotateIndex[p];
		
		return rectList[p][r];
		
	}

	private static int gety(int i){
		int p = rectIndex[i];
		int r = rectRotateIndex[p];
		
		return rectList[p][1 - r];
	}
	
	static class Rect implements Comparable<Rect>{
		
		int a,b;
		
		Rect(int a, int b){
			if(a < b){
				this.a = a;
				this.b = b;
			} else {
				this.a = b;
				this.b = a;
			}
		}

		@Override
		public int compareTo(Rect r) {
			if(a != r.a){
				return a - r.a;
			}
			return b - r.b;
		}

		@Override
		public boolean equals(Object arg0) {
			
			if(arg0 instanceof Rect){
				Rect r = (Rect) arg0;
				
				return compareTo(r) == 0;
			}
			
			return super.equals(arg0);
		}

		@Override
		public int hashCode() {
			return a * 50 + b;
		}
		
		
	}
}
