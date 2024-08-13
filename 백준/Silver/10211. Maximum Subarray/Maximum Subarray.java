import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int result = arr[0];
            int[] dp = new int[N];
            dp[0] = arr[0];
            for (int i = 1; i < N; i++) {
                dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
                result = Math.max(result, dp[i]);
            }
            sb.append(result).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

}