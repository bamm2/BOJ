import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			int[] cnt =new int[26];
			
			String s =br.readLine();
			
			for(int j=0;j<s.length();j++) {
				if(s.charAt(j)!=' ') cnt[s.charAt(j)-97]++;
			}
			
			int max=0;
			int maxchar=0;
			for(int j=0;j<26;j++) {
				if(max<cnt[j]) {
					max=cnt[j];
					maxchar=j;
				}
			}
			int maxcnt=0;
			for(int j=0;j<26;j++) {
				if(max==cnt[j]) {
					maxcnt++;
				}
			}
			if(maxcnt>=2) System.out.println("?");
			else System.out.println((char)(maxchar+97));
		}
	
	}// main
}