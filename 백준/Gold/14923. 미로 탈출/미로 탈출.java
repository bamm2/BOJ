import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][], exitR, exitC;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][][] visited;

    static class Point {
        int r, c, cnt, broken;

        public Point(int r, int c, int cnt, int broken) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.broken = broken;
        }
    }

    static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int startR = Integer.parseInt(st.nextToken()) - 1;
        int startC = Integer.parseInt(st.nextToken()) - 1;
        q.offer(new Point(startR, startC, 0, 0));

        st = new StringTokenizer(br.readLine(), " ");
        exitR = Integer.parseInt(st.nextToken()) - 1;
        exitC = Integer.parseInt(st.nextToken()) - 1;

        visited = new boolean[2][R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.r==exitR && curr.c==exitC) {
                return curr.cnt;
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc)) continue;
                if (map[nr][nc]==0) {
                    if (visited[curr.broken][nr][nc]) continue;
                    visited[curr.broken][nr][nc] = true;
                    q.offer(new Point(nr, nc, curr.cnt + 1, curr.broken));
                } else {
                    if (curr.broken==1 || visited[1][nr][nc]) continue;
                    visited[1][nr][nc] = true;
                    q.offer(new Point(nr, nc, curr.cnt + 1, 1));
                }
            }
        }
        return -1;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}