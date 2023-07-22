import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[101][101];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = BigInteger.ONE;
                } else if (i == j) {
                    dp[i][j] = BigInteger.ONE;
                } else {
                    BigInteger numOne = dp[i - 1][j - 1];
                    BigInteger numTwo = dp[i - 1][j];
                    dp[i][j] = numOne.add(numTwo);
                }
            }
        }

        System.out.println(dp[N][M]);
        
    }
}