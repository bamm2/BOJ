import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = 1;
        int e = 1;
        int s = 1;
        int m = 1;
        while (true) {
            if (e==E && s==S && m==M) break;
            e = (e + 1) % 15==0 ? 15:(e + 1) % 15;
            s = (s + 1) % 28==0 ? 28:(s + 1) % 28;
            m = (m + 1) % 19==0 ? 19:(m + 1) % 19;
            ans++;
        }
        System.out.println(ans);
    }
}