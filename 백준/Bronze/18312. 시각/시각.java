import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans=0;
		
		for(int i=0;i<=N ;i++) {
			for(int j=0;j<=59;j++) {
				for(int k=0;k<=59;k++) {
					if( i/10==K||i%10==K ||j/10==K||j%10==K||k%10==K||k/10==K) {
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
	}// main
}
