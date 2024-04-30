import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int R, C;
    static boolean flag;
    static final int LEFT = 1;
    static final int RIGHT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = RIGHT;
            map[r][c + 1] = LEFT;
        }

        for (int i = 0; i <= 3; i++) {
            solve(i, 0);
            if (flag) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
        br.close();
    }

    private static void solve(int cnt, int start) {
        if (flag) return;

        if (cnt==0) {
            for (int i = 0; i < C; i++) {
                if (!isSatisfied(i)) return;
            }
            flag = true;
            return;
        }

        for (int i = start; i < R; i++) {
            for (int j = 0; j < C - 1; j++) {
                if (map[i][j]==0 && map[i][j + 1]==0) {
                    map[i][j] = RIGHT;
                    map[i][j + 1] = LEFT;
                    solve(cnt - 1, i);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean isSatisfied(int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, c, -1});

        int nr, nc;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[2]==1 || map[curr[0]][curr[1]]==0) {
                nr = curr[0] + 1;
                if (nr==R) return c==curr[1]; // 목적지 도달
                q.offer(new int[]{nr, curr[1], -1});
            } else if (map[curr[0]][curr[1]]==LEFT) {
                nc = curr[1] - 1;
                q.offer(new int[]{curr[0], nc, 1});
            } else if (map[curr[0]][curr[1]]==RIGHT) {
                nc = curr[1] + 1;
                q.offer(new int[]{curr[0], nc, 1});
            }
        }
        return false;
    }
}
