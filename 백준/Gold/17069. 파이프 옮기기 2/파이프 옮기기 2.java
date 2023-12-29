import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[3][N][N]; // 0 가로 1 대각선 2 세로
        dp[0][0][1] = 1L;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                for (int d = 0; d < 3; d++) {
                    switch (d) {
                        case 0: // 가로
                            if (!(j + 1 >= N || map[i][j + 1]==1)) {
                                dp[0][i][j + 1] += dp[0][i][j] + dp[1][i][j];
                            }
                            break;
                        case 2: // 세로
                            if (!(i + 1 >= N || map[i + 1][j]==1)) {
                                dp[2][i + 1][j] += dp[1][i][j] + dp[2][i][j];
                            }
                            break;
                    }
                    if (j + 1 >= N || i + 1 >= N || map[i + 1][j]==1 || map[i][j + 1]==1 || map[i + 1][j + 1]==1)
                        continue;
                    dp[1][i + 1][j + 1] += dp[d][i][j];
                }
            }
        }

        long ans = 0L;
        for(int i=0;i<3;i++){
            ans+=dp[i][N-1][N-1];
        }

        System.out.println(ans);

    }
}