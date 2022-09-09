import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s =br.readLine();
		String find =br.readLine();
		
		String str="";
		
		for(int i=0;i<s.length();i++) {
			if(  ('a'<=s.charAt(i) && s.charAt(i)<='z') ||
					'A'<=s.charAt(i) && s.charAt(i)<='Z') {
				str+=s.charAt(i);
			}
		}
		
		if(str.contains(find)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}// main
}