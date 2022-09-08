import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] point3= {'A','E','F','G','H','K','M','N'};
		char[] point2= {'B','D','P','Q','R','T','W','X','Y'};
		char[] point1= {'C','I','J','L','O','S','U','V','Z'};
		
		
		String s =br.readLine();
		
		int sum=0; 
		
		for(int i=0;i<s.length();i++) {
			int c =s.charAt(i);
			
			for(int j=0;j<point3.length;j++) {
				if(c==point3[j]) {
					sum+=3;
				}
			}
			for(int j=0;j<point2.length;j++) {
				if(c==point2[j]) {
					sum+=2;
				}
			}
			for(int j=0;j<point1.length;j++) {
				if(c==point1[j]) {
					sum+=1;
				}
			}
		}
		int ans=sum%10;
		if(ans==0 || ans%2==0) System.out.println("You're the winner?");
		else System.out.println("I'm a winner!");
	}// main
}