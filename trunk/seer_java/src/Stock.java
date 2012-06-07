public class Stock{

	public static void main(String[] args){
	
		int[] arr = {6,1,3,2,4,7};
		System.out.println(stock(arr));
	}
	
	static int stock(int[] 股票){

		assert(股票 != null && 股票.length > 0);
		int 我的钱 = 0;
		int 低点 = 股票[0];
		
		for(int i = 0; i < 股票.length; i ++){
			if(股票[i] <= 低点){
				低点 = 股票[i];
			} else if(i == 股票.length - 1 || i < 股票.length - 1 && 股票[i+1] < 股票[i]){
					//要跌了，赶紧卖
					我的钱 += 股票[i] - 低点;
					低点 = 股票[i];
			}
		}
		
		return 我的钱;
	}

}