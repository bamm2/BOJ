import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, arr[];
    static Set<Integer> hs = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, 0);

        int ans;
        for (int i = 1; ; i++) {
            if (!hs.contains(i)) {
                ans = i;
                break;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }

    private static void solve(int idx, int sum, int flag) {
        hs.add(sum);
        if (idx==N) return;

        if ((flag & 1 << idx)!=0) return;
        solve(idx + 1, sum + arr[idx], flag | (1 << idx));
        solve(idx + 1, sum, flag);
    }
}