import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] answer = new int[10];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= 5; i++) {
            solve(i, 0, -1, 0);
        }

        System.out.println(ans);

    }

    private static void solve(int number, int idx, int prev, int correctCnt) {
        if (answer[idx]==number) correctCnt++;
        if (idx==9) {
            if (correctCnt >= 5) ans++;
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (number==prev && i==number) continue;
            solve(i, idx + 1, number, correctCnt);
        }
    }
}