import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int abs = Math.abs(N);
        int[] dp = new int[abs + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= abs; i++) {
            dp[i] = (dp[i - 1] % 1_000_000_000 + dp[i - 2] % 1_000_000_000) % 1_000_000_000;
        }
        if(N<0 && N%2==0) sb.append(-1).append('\n');
        else if (N==0) sb.append(0).append('\n');
        else sb.append(1).append('\n');

        sb.append(dp[abs]);

        System.out.println(sb);
    }
}
