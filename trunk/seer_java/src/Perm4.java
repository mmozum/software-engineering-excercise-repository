
public class Perm4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "asdf";
		
		final int N = 4;
		
		for(int i = 0; i < N; i ++){
			for(int j = 0; j < N; j ++){
				if(j == i) continue;
				for(int k = 0; k < N; k ++){
					if(k == i || k == j) continue;
					for(int l = 0; l < N; l ++){
						if(l == i || l == j || l == k) continue;
						
						System.out.format("%c%c%c%c\n", s.charAt(i), s.charAt(j), s.charAt(k), s.charAt(l));
					}
				}
			}
		}

	}

}
