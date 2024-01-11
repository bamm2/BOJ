import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 2][2];
        int[][] dp = new int[N + 2][2]; // N번째 칸 / 최적,  가장 큰 점프 사용x, 사용
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken()); // 작은 점프
            arr[i][1] = Integer.parseInt(st.nextToken()); // 큰 점프
        }

        int K = Integer.parseInt(br.readLine());
        dp[1][0] = arr[0][0];
        dp[1][1] = arr[0][0];
        dp[2][0] = Math.min(dp[1][0] + arr[1][0], dp[0][0] + arr[0][1]);
        dp[2][1] = dp[2][0];
        for (int i = 3; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + arr[i - 1][0], dp[i - 2][0] + arr[i - 2][1]);
            dp[i][1] = Math.min(dp[i - 1][1] + arr[i - 1][0], dp[i - 2][1] + arr[i - 2][1]);
            dp[i][1] = Math.min(dp[i - 3][0] + K, dp[i][1]);
        }

        bw.write(String.valueOf(Math.min(dp[N - 1][0], dp[N - 1][1])));
        bw.flush();
        bw.close();
        br.close();
    }
}