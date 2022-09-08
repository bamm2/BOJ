import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str=br.readLine();
			if(str.equals("END")) break;
			
			for(int i=str.length()-1;i>=0;i--) {
				System.out.print(str.charAt(i));
			}
			System.out.println();
		}
	}// main
}