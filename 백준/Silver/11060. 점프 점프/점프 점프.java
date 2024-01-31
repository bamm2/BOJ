import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        
        loop:
        for (int i = 1; i <= N; i++) {
            int moveMax = arr[i];
            for (int j = 1; j <= moveMax; j++) {
                if (i + j > N) continue;
                if (dp[i + j]==Integer.MAX_VALUE && dp[i]==Integer.MAX_VALUE) break loop;
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        bw.write(String.valueOf(dp[N]==Integer.MAX_VALUE ? -1:dp[N]));
        bw.flush();
        bw.close();
        br.close();

    }
}