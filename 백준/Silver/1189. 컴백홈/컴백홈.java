import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[R - 1][0] = true;
        dfs(R - 1, 0, 1);

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int r, int c, int cnt) {
        if (cnt==K) {
            if (r==0 && c==C - 1) ans++;
            return;
        }

        if (cnt > K) return;

        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]=='T') continue;
            visited[nr][nc] = true;
            dfs(nr, nc, cnt + 1);
            visited[nr][nc] = false;
        }

    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}