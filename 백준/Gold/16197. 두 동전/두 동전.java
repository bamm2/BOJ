import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int R, C, ans;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static List<Pos> coins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j]=='o') coins.add(new Pos(i, j));
            }
        }

        ans = Integer.MAX_VALUE;

        solve(0, coins.get(0).r, coins.get(0).c, coins.get(1).r, coins.get(1).c);

        if (ans==Integer.MAX_VALUE) ans = -1;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }


    private static void solve(int cnt, int r1, int c1, int r2, int c2) {
        if (cnt+1 > 10 || ans <= cnt + 1) return;
        if (r1==r2 && c1==c2) return;

        for (int d = 0; d < 4; d++) {
            int nr1 = r1 + dir[d][0];
            int nc1 = c1 + dir[d][1];
            int nr2 = r2 + dir[d][0];
            int nc2 = c2 + dir[d][1];
            if (isOut(nr1, nc1) && !isOut(nr2, nc2) || !isOut(nr1, nc1) && isOut(nr2, nc2)) {
                ans = Math.min(ans, cnt + 1);
                return;
            } else if (isOut(nr1, nc1) && isOut(nr2, nc2)) continue;

            if (map[nr1][nc1]=='#' && map[nr2][nc2]=='#') {
                solve(cnt + 1, r1, c1, r2, c2);
            } else if (map[nr1][nc1]=='#') {
                solve(cnt + 1, r1, c1, nr2, nc2);
            } else if (map[nr2][nc2]=='#') {
                solve(cnt + 1, nr1, nc1, r2, c2);
            } else {
                solve(cnt + 1, nr1, nc1, nr2, nc2);
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}