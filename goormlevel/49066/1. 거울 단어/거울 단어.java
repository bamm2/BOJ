import java.io.*;
import java.util.*;

class Main {
	
	static Map<Character,Character> mirrorAlphabets;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mirrorAlphabets = new HashMap<>();
		initAlphabets();
		
		int N =Integer.parseInt(br.readLine());
		
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<N;i++){
			char[] arr= br.readLine().toCharArray();
			int left = 0;
			int right = arr.length-1;
			boolean flag =false;
			
			while(left<=right){
				 	if(isSatisfied(arr[left],arr[right])){
						++left;
						--right;
					}else{
						flag= true;
						break;
					}
			}
			
			if(flag) {
				sb.append("Normal").append('\n');
			}else{
				sb.append("Mirror").append('\n');
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	private static void initAlphabets(){
		mirrorAlphabets.put('b','d'); mirrorAlphabets.put('d','b');
		mirrorAlphabets.put('i','i'); mirrorAlphabets.put('l','l');
		mirrorAlphabets.put('m','m'); mirrorAlphabets.put('n','n');
		mirrorAlphabets.put('o','o'); mirrorAlphabets.put('p','q');
		mirrorAlphabets.put('q','p'); mirrorAlphabets.put('s','z');
		mirrorAlphabets.put('z','s'); mirrorAlphabets.put('u','u');
		mirrorAlphabets.put('v','v'); mirrorAlphabets.put('w','w');
		mirrorAlphabets.put('x','x'); 
	}
	
	private static boolean isSatisfied(char left, char right){
			if(mirrorAlphabets.get(left) == null || mirrorAlphabets.get(right) == null) return false;
		  return mirrorAlphabets.get(left) == right && mirrorAlphabets.get(right) == left;
	}
	
}