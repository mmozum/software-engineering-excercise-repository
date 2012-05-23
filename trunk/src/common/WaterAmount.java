package common;

public class WaterAmount {

	
	public static void main(String[] args){
		
		int H = 200;
		int C = 2;
		int L = 9000000;
		
		long t0 = System.currentTimeMillis();
		for(int i = 0; i < H; i ++){
			for(int j = 0; j <= i; j++){
				getAmount(C, L, H, j); // version 1, original version
				//System.out.format("%.1f\t", getAmount(1, 90000000, i, j));
			}
			//System.out.println();
		}
		
		long t1 = System.currentTimeMillis();
		for(int i = 0; i < H; i ++){
			for(int j = 0; j <= i; j++){
				getAmount2(C, L, H, j); // version 2, viisa
				//System.out.format("%.1f\t", getAmount(C, L, i, j));
			}
			//System.out.println();
		}
		long t2 = System.currentTimeMillis();
		for(int i = 0; i < H; i ++){
			for(int j = 0; j <= i; j++){
				getAmount3(C, L, H, j); // version 3, viisa+microz
				//System.out.format("%.1f\t", getAmount(C, L, i, j));
			}
			//System.out.println();
		}
		long t3 = System.currentTimeMillis();
		for(int i = 1; i <= H; i ++){
			for(int j = 1; j <= i; j++){
				 run(C, L, i, j);
				//System.out.format("%.1f\t", run(C, L, i, j));
			}
			//System.out.println();
		}
		long t4 = System.currentTimeMillis();
		System.out.println( "version 1 ZhangBZ original: " + (t1 - t0) + "ms");
		System.out.println( "version 2 improved by viisa: " + (t2 - t1) + "ms");
		System.out.println( "version 3 further improved by microz and viisa: " + (t3 - t2) + "ms");
		System.out.println( "version 4 peking2: " + (t4 - t3) + "ms");
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
	
	
	static double getAmount3(int C, int L, int h, int cup){
		
		double[] buff = new double[(h >> 1) + 1];
		
		if(cup > h >> 1){
			cup = h - cup;
		}
		
		buff[0] = L;
		int level = 1;
		
		boolean overflow = (C < L);
		
		while(level < h && overflow){
			
			overflow = false;
			int len = (level >> 1) + 1; // for each level, compute first half of cups
			double pre = 0.0;
			double cur = 0.0;
			
			for(int i = 0; i < len; i ++){
				cur = (buff[i] > C) ? (buff[i] - C) * 0.5 : 0.0;
				buff[i] = pre + cur;
				pre = cur;
				
				if(buff[i] > C){
					overflow = true;
				}
			}
			
			level ++;
		}
		
		if(level != h){
			// no water reached level h
			return 0;
		}
		
		return (buff[cup] <= C ) ? buff[cup] : C;
		
	}
	

	// by peking2
	    static double run(int C, int L, int h, int cup)
	    {
	        int cc = 0;
	        for (int i = 1; i <= h; i++)
	            cc += i;

	        double[] w = new double[cc];

	        w[0] = L;

	        int layer = 1;
	        int count = layer;

	        int i = 0;

	        while (i + layer < cc)
	        {
	            if (w[i] > C)
	            {
	                double half = (w[i] - C) / 2;
	                w[i + layer] += half;
	                w[i + layer + 1] += half;
	                w[i] = C;
	            }

	            i++;
	            count--;
	            if (count == 0)
	            {
	                layer++;
	                count = layer;
	            }
	        }

	        return Math.min(C, w[cc - h + cup - 1]);
	    }





}


