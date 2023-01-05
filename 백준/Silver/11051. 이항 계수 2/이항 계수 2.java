import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        // nCk = n-1Ck + n-1Ck-1
        long[][] dp=new long[N+1][K+1];
        for(int i=0;i<=N;i++){
            int end =Math.min(i,K);
            for(int j=0;j<=end;j++){
                if( j==0 || j==i ) dp[i][j]=1;
                else dp[i][j]=((dp[i-1][j]%10007)+(dp[i-1][j-1]%10007))%10007;
            }
        }
        System.out.println(dp[N][K]);
    }
}