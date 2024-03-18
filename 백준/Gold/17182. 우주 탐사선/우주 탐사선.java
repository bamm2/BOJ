import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int V, start, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        map = new int[V][V];

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < V; j++) {
                int number = Integer.parseInt(st.nextToken());
                map[i][j] = number;
            }
        }

        for (int k = 0; k < V; k++) { // 경
            for (int i = 0; i < V; i++) { // 출
                if (i==k) continue;
                for (int j = 0; j < V; j++) { // 도
                    if (i==j || k==j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        ans = Integer.MAX_VALUE;
        solve(1, start, 0, 1 << start);

        System.out.println(ans);
        br.close();
    }

    private static void solve(int cnt, int v, int sum, int flag) {
        if (cnt==V) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < V; i++) {
            if (v==i) continue;
            if ((flag & (1 << i))!=0) continue;
            solve(cnt + 1, i, sum + map[v][i], flag | (1 << i));
        }
    }
}