import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int range = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            if (range==0) range = Integer.parseInt(st.nextToken());
            else {
                int num = Integer.parseInt(st.nextToken());
                int gcd = num > range ? gcd(num, range):gcd(range, num);
                sb.append(range / gcd).append('/').append(num / gcd).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b, a % b);
    }
}
