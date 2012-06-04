package ihasleetcode;

public class AddBinary {

	/*
	 Given two binary strings, return their sum (also a binary string).
		
		For example,
		a = "11"
		b = "1"
		Return "100". 
	 */
	
	public static void main(String[] args) {
		System.out.println(addBinary("1", "11"));

	}

    static public String addBinary(String a, String b) {

    	String longer = (a.length() >= b.length()) ? a : b;
    	String shorter= (a.length() >= b.length()) ? b : a;
    	
    	char[] buff = longer.toCharArray();
    	int i = longer.length() - 1;
    	int j = shorter.length() - 1;
    	int carry = 0;
    	
    	for(; j >= 0; i --, j --){
    		int sum = buff[i] + shorter.charAt(j) - 2 * '0' + carry;
    		buff[i] = (sum % 2 == 0) ? '0' : '1';
    		carry = sum / 2;
    	}
    	
    	for(; i >= 0; i --){
    		int sum = buff[i] - '0' + carry;
    		buff[i] = (sum % 2 == 0) ? '0' : '1';
    		carry = sum / 2;
    	}
    	
    	if(carry > 0){
    		return "1" + new String(buff);
    	} else {
    		return new String(buff);
    	}
    }
}
