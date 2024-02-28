import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int gcd = a > b ? solve(a, b):solve(b, a);
            int ans = a * b / gcd;
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
        br.close();

    }

    private static int solve(int a, int b) {
        if (b==0) return a;

        return solve(b, a % b);
    }
}