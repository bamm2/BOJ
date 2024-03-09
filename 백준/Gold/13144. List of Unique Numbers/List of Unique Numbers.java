import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, idxArr;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        idxArr = new int[100_001];
        Arrays.fill(idxArr, -1);
        dp = new long[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        for (int i = 1; i <= N; ++i) {
            int curr = arr[i];
            if (idxArr[curr]==-1) {
                idxArr[curr] = i;
                dp[i] = dp[i - 1] + 1;
            } else {
                int prevIdx = idxArr[curr];
                dp[i] = Math.min(i - prevIdx, dp[i - 1] + 1);
                idxArr[curr] = i;
            }
            sum += dp[i];
        }

        System.out.println(sum);
        br.close();
    }
}