import java.util.NoSuchElementException;

interface Iterator<A> {
	A next();
	boolean hasNext();
}

interface Predicate<S> {
	boolean accept(S t);
}

	
public class Tmp {
	
	<T> Iterator<T> conditionIterator(final Iterator<T> input, final Predicate<T> pred) {
		
		return new Iterator<T>(){
			
			T nextEle = null;

			@Override
			public T next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				T tmp = nextEle;
				nextEle = null;
				return tmp;
			}

			@Override
			public boolean hasNext() {

				if(nextEle != null){
					return true;
				}
				
				while(input.hasNext()){
					T tmp = input.next();
					if(pred.accept(tmp)){
						nextEle = tmp;
						break;
					}
				}
				
				return nextEle != null;
			}
			
		};
	}

	public static void main(String[] args) {

		double[] a = {2.0, -300 ,0.01 , -2, 2, -1, 0.5, 10};
		
		System.out.println(maxProduct(a));
	}

	static double maxProduct(double a[])
	{
		final int N = a.length;
	    if(N==0) return 0.0;
	    double[] big = new double[N];
	    double[] small = new double[N];

	    big[0] = small[0] = a[0];
	    double res = a[0];

	    for(int i=1;i<N;i++)
	    {
	        big[i] = Math.max(a[i], big[i-1]*a[i]);
	        big[i] = Math.max(big[i], small[i-1]*a[i]);
	        res = Math.max(res,big[i]);

	        small[i] = Math.min(a[i], big[i-1]*a[i]);
	        small[i] = Math.min(small[i], small[i-1]*a[i]);
	    }
	    return res;
	}


	
	public static int[] minizeDistance(int[] a, int[] b, int[] c) {
		int[] minIndexes = null;
		Integer minVal = null;
		int i=0, j=0, k=0;
		while (true) {
			int curMin = Math.max(a[i] - b[j], 
				Math.max(b[j] - c[k], c[k] - a[i]));
			if (minVal == null || curMin < minVal) {
				minVal = curMin;
				minIndexes = new int[]{i, j, k};
			}
			boolean canIncI = i != a.length-1;
			boolean canIncJ = j != b.length-1;
			boolean canIncK = k != c.length-1;
			if (!canIncI && !canIncJ && !canIncK)
				break;	
			if (canIncI && (!canIncJ || a[i] < b[j]) 
				&& (!canIncK || a[i] < c[k]))
				++i;
			else if (canIncJ && (!canIncK || b[j] < c[k]))
				++j;
			else
				++k;
				
		}
		
		System.out.println("minVal = " + minVal);
		return minIndexes;
	}
}
