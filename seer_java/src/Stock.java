public class Stock{

	public static void main(String[] args){
	
		int[] arr = {6,1,3,2,4,7};
		System.out.println(stock(arr));
	}
	
	static int stock(int[] ��Ʊ){

		assert(��Ʊ != null && ��Ʊ.length > 0);
		int �ҵ�Ǯ = 0;
		int �͵� = ��Ʊ[0];
		
		for(int i = 0; i < ��Ʊ.length; i ++){
			if(��Ʊ[i] <= �͵�){
				�͵� = ��Ʊ[i];
			} else if(i == ��Ʊ.length - 1 || i < ��Ʊ.length - 1 && ��Ʊ[i+1] < ��Ʊ[i]){
					//Ҫ���ˣ��Ͻ���
					�ҵ�Ǯ += ��Ʊ[i] - �͵�;
					�͵� = ��Ʊ[i];
			}
		}
		
		return �ҵ�Ǯ;
	}

}