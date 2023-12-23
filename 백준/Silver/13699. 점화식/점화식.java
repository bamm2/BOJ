import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[36];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            int idx = i;
            int count = i / 2 ;
            while (count-- > 0) {
                dp[i] += 2 * (dp[idx - 1] * dp[i - idx]);
                idx--;
            }
            if (i % 2!=0) dp[i] += dp[i/2] * dp[i/2];
        }

        System.out.println(dp[N]);

    }
}
