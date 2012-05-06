package usaco1;

import java.util.LinkedList;
import java.util.List;


public class Tmp {

	
	public static void main(String[] args){
		
		int H = 200;
		int C = 2;
		int L = 9000;
		
		long t0 = System.currentTimeMillis();
		for(int i = 0; i < H; i ++){
			for(int j = 0; j <= i; j++){
				getAmount(C, L, H, j); // version 1
				//System.out.format("%.1f\t", getAmount(1, 90000000, i, j));
			}
			//System.out.println();
		}
		
		long t1 = System.currentTimeMillis();
		for(int i = 0; i < H; i ++){
			for(int j = 0; j <= i; j++){
				getAmount2(C, L, H, j); // version 2
				//System.out.format("%.1f\t", getAmount(1, 90000000, i, j));
			}
			//System.out.println();
		}
		long t2 = System.currentTimeMillis();
		System.out.println( "version 1: " + (t1 - t0) + "ms");
		System.out.println( "version 2: " + (t2 - t1) + "ms");
	}
	
	static double getAmount(int C, int L, int h, int cup){
		
		double[] buff = new double[h + 1];
		buff[0] = L;
		int level = 1;
		
		boolean overflow = (C < L);
		
		while(level <= h && overflow){
			
			overflow = false;
			for(int i = level; i >= 0; i --){
				buff[i] = getOverflow(buff, level, i, C) + 
						getOverflow(buff, level, i - 1, C);
				
				if(buff[i] > C){
					overflow = true;
				}
			}
			
			level ++;
		}
		
		if(level <= h){
			return 0;
		}
		
		return Math.min(buff[cup], C);
		
	}

	private static double getOverflow(double[] buff, int len, int i, int C) {

		if(i < 0 || i >= len || buff[i] <= C){
			return 0;
		}
		
		return (buff[i] - C) * 0.5;
	}

	static double getAmount2(int C, int L, int h, int cup){
		
		double[] buff = new double[h + 1];
		buff[0] = L;
		int level = 1;
		
		boolean overflow = (C < L);
		
		while(level <= h && overflow){
			
			overflow = false;
			
			buff[level] = (buff[level-1] > C) ? (buff[level-1] - C) * 0.5 : 0.0;
			
			for(int i = level - 1; i > 0; i --){
				buff[i] = (buff[i] > C) ? (buff[i] - C) * 0.5 : 0.0;
				buff[i] += (buff[i-1] > C) ? (buff[i-1] - C) * 0.5 : 0.0;
			}
			
			buff[0] = (buff[0] > C) ? (buff[0] - C) * 0.5 : 0.0;
			
			for(int i = 0; i <= level; i ++){
				if(buff[i] > C){
					overflow = true;
					break;
				}
			}
			
			level ++;
		}
		
		if(level <= h){
			return 0;
		}
		
		return Math.min(buff[cup], C);
		
	}
	
}


