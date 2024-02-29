import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long diffN = N - M;

        long nTwoCnt = solve(N, 2);
        long nFiveCnt = solve(N, 5);
        long diffNTwoCnt = solve(diffN, 2);
        long diffNFiveCnt = solve(diffN, 5);
        long mTwoCnt = solve(M, 2);
        long mFiveCnt = solve(M, 5);

        System.out.println(Math.min(nTwoCnt - mTwoCnt - diffNTwoCnt, nFiveCnt - mFiveCnt - diffNFiveCnt));

        br.close();
    }

    private static long solve(long number, long range) {
        long cnt = 0;
        for (long i = range; i <= number; i *= range) {
            cnt += number / i;
        }
        return cnt;
    }

}