import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    private static final Integer MAX = 987_654_321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
        HashSet<Integer> hs = new HashSet<>();

        int[] dp = new int[K + 1];
        Arrays.fill(dp, MAX);

        for (int i = 0; i < N; i++) {
            int curr = coins[i];
            if(K >= curr )dp[curr] = 1;
            if (!hs.add(curr)) continue;
            for (int j = 1; j <= K; j++) {
                if (j - curr < 0) continue;
                dp[j] = Math.min(dp[j], dp[j - curr] + 1);
            }
        }

        System.out.println(dp[K]==MAX ? -1:dp[K]);
        br.close();
    }
}
