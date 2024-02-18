import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int[][] dp = new int[A.length + 1][B.length + 1];

        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            char compA = A[i - 1];
            for (int j = 1; j <= B.length; j++) {
                char compB = B[j - 1];
                if (compA==compB) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                max=Math.max(max,dp[i][j]);
            }
        }

        System.out.println(max);
        br.close();

    }
}