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
        dp[1] = arr[1];
        for (int i = 2; i <= N; i++) {
            int min = arr[i];
            for (int j = i - 1; j >= 0 ; j--) {
                min = Math.min(min, dp[j] + arr[i-j]);
            }
            dp[i] = min;
        }

        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
        br.close();

    }
}