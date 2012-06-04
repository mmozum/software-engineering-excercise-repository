package ihasleetcode;

import java.util.LinkedList;

public class SimplifyPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] in = {1,3};
		
		System.out.println(simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
	}
	

    static     public String simplifyPath(String path) {
    	
    	if(path.isEmpty() || path.length() == 1){
    		return "/";
    	}

    	LinkedList<String> list = new LinkedList<String>();
    	
    	int start = 0;
    	int end = start;
    	
    	while(start < path.length()){
    		
    		end = path.indexOf('/', start + 1);
    		if(end < 0){
    			end = path.length();
    		}
    		
    		String s = path.substring(start + 1, end);
    		
    		if(s.equals("..")){
    			if(!list.isEmpty()){
    				list.removeLast();
    			}
    		} else if(!s.equals(".") && !s.equals("")){
    			list.add(s);
    		}
    		
    		start = end;
    	}
    	
        
    	StringBuilder sb = new StringBuilder();
    	
    	for(String s : list){
    		sb.append("/").append(s);
    	}
    	
    	return sb.length() == 0 ? "/" : sb.toString();
    }


    

}
