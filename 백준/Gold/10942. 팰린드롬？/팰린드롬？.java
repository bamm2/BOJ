import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[][] dp = new int[N][N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSatisfied(i, j, arr)) dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[from][to]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static boolean isSatisfied(int start, int end, int[] arr) {
        while (start < end) {
            if (arr[start]!=arr[end]) return false;
            start++;
            end--;
        }
        return true;
    }
}