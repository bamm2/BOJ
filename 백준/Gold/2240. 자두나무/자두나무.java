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

        st = new StringTokenizer(br.readLine(), " ");
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[W + 1][T][2]; // W-> 이동 횟수 , T -> T초 , 현재 위치
        int first = Integer.parseInt(br.readLine());
        if (first==1) { // 최초 1초
            dp[0][0][0] = 1; // 이동 안하고 + 1
        } else {
            dp[1][0][1] = 1; // 이동하고 + 1
        }

        for (int t = 1; t < T; t++) { // 2 ~ T-1 초
            int dir = Integer.parseInt(br.readLine());
            if (dir==1) { // 1 일 경우
                dp[0][t][0]=dp[0][t-1][0]+1; // 이동 0회
                for (int w = 1; w <= W; w++) {
                    dp[w][t][0] = Math.max(dp[w - 1][t - 1][1], dp[w][t - 1][0]) + 1; // 현재 위치 vs 2 위치에서 이동
                    dp[w][t][1] = Math.max(dp[w][t - 1][1], dp[w - 1][t - 1][0]);
                }
            } else { // 2 일 경우
                for (int w = 1; w <= W; w++) {
                    dp[w][t][1] = Math.max(dp[w - 1][t - 1][0], dp[w][t - 1][1]) + 1;
                    dp[w][t][0] = Math.max(dp[w][t - 1][0], dp[w - 1][t - 1][1]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < 2; j++) {
                max = Math.max(max, dp[i][T - 1][j]);
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}