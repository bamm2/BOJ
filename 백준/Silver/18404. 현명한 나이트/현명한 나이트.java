import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    static int[][] map;

    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int curR = Integer.parseInt(st.nextToken()) - 1;
        int curC = Integer.parseInt(st.nextToken()) - 1;

        bfs(curR, curC);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int goalR = Integer.parseInt(st.nextToken()) - 1;
            int goalC = Integer.parseInt(st.nextToken()) - 1;
            sb.append(map[goalR][goalC]).append(" ");
        }

        System.out.println(sb);

    }

    private static void bfs(int curR, int curC) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(curR, curC, 0));
        map[curR][curC] = 0;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 8; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || curr.cnt + 1 >= map[nr][nc]) continue;
                map[nr][nc]=curr.cnt+1;
                q.offer(new Point(nr, nc, curr.cnt + 1));
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
