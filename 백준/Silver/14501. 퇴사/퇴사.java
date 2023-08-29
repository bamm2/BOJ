import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] dp =new int[N+2];
        int max = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if(max < dp[i]) max =dp[i];

            if (i + t > N+1) continue;

            dp[i+t] = Math.max( dp[i+t] , max + p );

        }
        int ans =0;
        for(int i=1;i<=N+1;i++){
            ans= Math.max(ans,dp[i]);
        }

        System.out.println(ans);
    }
}