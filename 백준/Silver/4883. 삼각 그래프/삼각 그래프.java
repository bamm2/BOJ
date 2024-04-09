import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = 0;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][3];
            if (N==0) break;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N][3];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], 987654321);
            }

            dp[0][1] = arr[0][1];
            dp[0][2] = arr[0][1] + arr[0][2];

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][0];
                dp[i][1] = Math.min(Math.min(Math.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]), dp[i][0]) + arr[i][1];
                dp[i][2] = Math.min(Math.min(dp[i - 1][1], dp[i - 1][2]), dp[i][1]) + arr[i][2];
            }

            sb.append(++tc).append(". ").append(dp[N - 1][1]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

}