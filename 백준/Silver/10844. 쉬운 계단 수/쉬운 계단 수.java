import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        long[][] dp =new long[101][10];
        for(int i=1;i<10;i++){
            dp[1][i]=1;
        }

        for(int i=2;i<=N;i++){
            for(int j=0;j<10;j++){
                if(j==0) dp[i][j]=dp[i-1][1] % MOD ;
                else if(j==9) dp[i][j]=dp[i-1][8] % MOD ;
                else dp[i][j]=(dp[i-1][j-1]%MOD+dp[i-1][j+1]%MOD)%MOD;
            }
        }

        long sum  =0;
        for(int i=0;i<10;i++){
            sum=(sum%MOD+dp[N][i]%MOD)%MOD;
        }

        System.out.println(sum);

    }
}