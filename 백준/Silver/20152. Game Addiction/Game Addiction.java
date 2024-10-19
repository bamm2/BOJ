import java.io.IOException;
import java.io.StreamTokenizer;

public class Main {

    private static StreamTokenizer st = new StreamTokenizer(System.in);

    public static void main(String[] args) throws IOException {
        int H = input() + 1;
        int N = input() + 1;
        int min = Math.min(H, N);
        int max = Math.max(H, N);
        long[][] dp = new long[32][32];
        for (int r = min; r <= max; r++) {
            dp[r][min] = 1;
        }
        for (int r = min+1; r <= max; r++) {
            for (int c = min+1; c <= r; c++) {
                dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
            }
        }
        System.out.println(dp[max][max]);
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}