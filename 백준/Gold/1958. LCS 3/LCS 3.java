import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();
        char[] arrayA = convertCharArray(A);
        char[] arrayB = convertCharArray(B);
        char[] arrayC = convertCharArray(C);

        int[][][] dp = new int[arrayA.length][arrayB.length][arrayC.length];
        int max = 0;

        for (int i = 1; i < arrayA.length; i++) {
            for (int j = 1; j < arrayB.length; j++) {
                for (int k = 1; k < arrayC.length; k++) {
                    if (arrayA[i]==arrayB[j] && arrayB[j]==arrayC[k]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                        max= Math.max(max,dp[i][j][k]);
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                    }
                }
            }
        }

        System.out.println(max);

        br.close();
    }

    private static char[] convertCharArray(String str) {
        char[] result = new char[str.length() + 1];
        for (int i = 1; i <= str.length(); i++) {
            result[i] = str.charAt(i - 1);
        }
        return result;
    }
}