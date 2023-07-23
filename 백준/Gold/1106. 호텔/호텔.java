import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int C = Integer.parseInt(st.nextToken()); // 늘려야 하는 고객의 수 기준
        int N = Integer.parseInt(st.nextToken()); // 홍보 가능 도시 수

        int[] dp =new int[C+101];
        Arrays.fill(dp, INF);
        dp[0]=0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cost = Integer.parseInt(st.nextToken()); // 홍보 비용
            int people = Integer.parseInt(st.nextToken()); // 얻을 수 있는 고객의 수

            for (int j=people ; j<=people+C ; j++){
                if(dp[j-people]!=INF)  dp[j]=Math.min(dp[j],dp[j-people]+cost);
            }
        }
        
        int ans = INF;
        for(int i= C ; i<=C+100 ;i++){
            ans = Math.min(ans,dp[i]);
        }

        System.out.println(ans);

    }
}