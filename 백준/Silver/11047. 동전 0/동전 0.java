import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N =Integer.parseInt(st.nextToken()); //동전의 종류개수 
		int K =Integer.parseInt(st.nextToken()); //동전의 합 
		
		int[] coinprice =new int[N];
		int[] cnt= new int[N];
		int ans=0;
		for(int i=0;i<N;i++) {
			coinprice[i]=Integer.parseInt(br.readLine());
		}
		for(int i=N-1;i>=0;i--) {
				cnt[i]=K/coinprice[i];
				K-=cnt[i]*coinprice[i];
				ans+=cnt[i];
			
			if(K==0) break;	
		}
	System.out.println(ans);	
	}
}